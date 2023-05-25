package kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects;

import android.view.MotionEvent;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.ITouchable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class Button extends Sprite implements ITouchable {
    private static final String TAG = Button.class.getSimpleName();
    private final Callback callback;

    public enum Action {
        pressed, released,
    }
    public interface Callback {
        public boolean onTouch(Action action);
    }
    public Button(int bitmapResId, float cx, float cy, float width, float height, Callback callback) {
        super(cx, cy, width, height, bitmapResId);
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
        } else if (action == MotionEvent.ACTION_UP) {
            callback.onTouch(Action.released);
        }
        return true;
    }
}