package kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects;


import android.graphics.Bitmap;
import android.view.MotionEvent;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.ITouchable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.BitmapPool;
public class Button extends Sprite implements ITouchable {
    private static final String TAG = Button.class.getSimpleName();
    private Callback callback;
    private Calltoback calltoback;
    private boolean pressed;
    public float press_x;
    private Bitmap normalBitmap;
    private Bitmap pressedBitmap;
    public enum Action {
        pressed, released, moveLeft, moveRight
    }
    public interface Callback {
        public boolean onTouch(Action action);
    }
    public interface Calltoback {
        public boolean onTouch(Action action, boolean pressed);
    }
    public Button(int bitmapResId, float cx, float cy, float width, float height, Callback callback) {
        super(bitmapResId, cx, cy, width, height);
        this.callback = callback;
    }
    public Button(float x, float y, float w, float h, int bitmapResId, int pressedResId, Calltoback callback) {
        super(x, y, w, h, bitmapResId);
        normalBitmap = bitmap;
        if (pressedResId != 0) {
            pressedBitmap = BitmapPool.get(pressedResId);
        }
        this.calltoback = callback;
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = Metrics.toGameX(e.getX());
        float y = Metrics.toGameY(e.getY());
        if (!dstRect.contains(x, y)) {
            return false;
        }
        //Log.d(TAG, "Button.onTouch(" + System.identityHashCode(this) + ", " + e.getAction() + ", " + e.getX() + ", " + e.getY());
        int action = e.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            callback.onTouch(Action.pressed);
        } else if (action == MotionEvent.ACTION_UP) {
            callback.onTouch(Action.released);
        }
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                bitmap = pressedBitmap;
                if(press_x > dstRect.centerX() && press_x < dstRect.right) {
                    return calltoback.onTouch(Action.moveRight,pressed);
                }
                if(press_x < dstRect.centerX() && press_x > dstRect.left) {
                    return calltoback.onTouch(Action.moveLeft,pressed);
                }
                break;

            case MotionEvent.ACTION_UP:
                pressed = false;
                bitmap = normalBitmap;
                return calltoback.onTouch(Action.released,pressed);

            case MotionEvent.ACTION_MOVE:
                pressed = true;
                if(press_x > dstRect.centerX() && press_x < dstRect.right) {
                    return calltoback.onTouch(Action.moveRight,pressed);
                }
                if(press_x < dstRect.centerX() && press_x > dstRect.left) {
                    return calltoback.onTouch(Action.moveLeft,pressed);
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                pressed = true;
                bitmap = pressedBitmap;
                if(press_x > dstRect.centerX() && press_x < dstRect.right) {
                    return calltoback.onTouch(Action.moveRight,pressed);
                }
                if(press_x < dstRect.centerX() && press_x > dstRect.left) {
                    return calltoback.onTouch(Action.moveLeft,pressed);
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                pressed = false;
                bitmap = normalBitmap;
                return calltoback.onTouch(Action.released,pressed);
        }

        return true;
    }
}