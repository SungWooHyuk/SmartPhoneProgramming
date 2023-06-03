package kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.BitmapPool;

public class SheetSprite extends Sprite {
    private final float framesPerSecond;
    private final long createdOn;
    protected Rect[] srcRects;
    public boolean flip = false;
    public int flipsize = 0;
    public SheetSprite(int mipmapResId, float framesPerSecond) {
        bitmap = BitmapPool.get(mipmapResId);
        this.framesPerSecond = framesPerSecond;
        createdOn = System.currentTimeMillis();
    }

    public void ChangeBitmap(int mipmapResId)
    {
        bitmap = BitmapPool.get(mipmapResId);
    }
    public void SetBitmapflip(boolean b_flip)
    {
        flip = b_flip;
    }
    public void SetBitmapflipSize(int size){
        flipsize = size;
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        int index = Math.round(time * framesPerSecond) % srcRects.length;
        canvas.drawBitmap(bitmap, srcRects[index], dstRect, null);
    }
}
