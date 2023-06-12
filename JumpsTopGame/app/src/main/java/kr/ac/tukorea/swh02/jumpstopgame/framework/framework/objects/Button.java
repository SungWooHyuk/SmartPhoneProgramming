package kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects;


import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.ITouchable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.BitmapPool;
public class Button extends Sprite implements ITouchable {
    private static final String TAG = Button.class.getSimpleName();
    private Callback callback;
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

    public Button(int bitmapResId, float cx, float cy, float width, float height, Callback callback) {
        super(bitmapResId, cx, cy, width, height);
        this.callback = callback;
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
            Log.d(TAG, "hello" );
        } else if (action == MotionEvent.ACTION_UP) {
            callback.onTouch(Action.released);
            Log.d(TAG, "Bye" );
        }
        return true;
    }
}