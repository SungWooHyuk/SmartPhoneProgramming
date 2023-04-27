package kr.ac.tukorea.swh02.jumpstopgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class BackGround {
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, width, height;

    protected void setSize(float width, float height) {
        float half_width = width / 2;
        float half_height = height / 2;
        dstRect.set(x - half_width, y - half_height, x + half_width, y + half_height);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

}
