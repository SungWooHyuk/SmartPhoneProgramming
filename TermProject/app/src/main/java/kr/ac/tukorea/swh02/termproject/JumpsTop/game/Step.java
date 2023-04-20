package kr.ac.tukorea.swh02.termproject.framework.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.tukorea.swh02.termproject.framework.view.Metrics;

public class Step extends GameObject {
    private static final String TAG = Step.class.getSimpleName();
    private final Paint paint = new Paint();
    private final RectF floorRect = new RectF();

    public Step() {
        float width = Metrics.getGameWidth() * 0.6f;
        float height = 50;
        float x = (Metrics.getGameWidth() - width) / 2;
        float y = Metrics.getGameHeight() - height - 50;
        setPos(x, y, width, height);
        paint.setColor(Color.GRAY);
    }

    public boolean isOnTop(Sprite sprite) {
        return sprite.dstRect.bottom >= floorRect.top && sprite.dstRect.bottom <= floorRect.bottom
                && sprite.dstRect.left < floorRect.right && sprite.dstRect.right > floorRect.left;
    }

    @Override
    public void draw(Canvas canvas) {
        floorRect.set(dstRect);
        canvas.drawRect(floorRect, paint);
    }
}