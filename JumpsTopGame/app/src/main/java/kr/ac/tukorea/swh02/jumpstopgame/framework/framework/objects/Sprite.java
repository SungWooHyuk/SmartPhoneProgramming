package kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IGameObject;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.BitmapPool;

public class Sprite implements IGameObject {
    private static final String TAG = Sprite.class.getSimpleName();
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, width, height;

    protected float fps;
    protected long createdOn;

    protected Sprite() {} // 상속받은 class 에서 자유롭게 생성자를 만들 수 있도록 default 생성자를 추가한다
    public Sprite(int bitmapResId, float cx, float cy, float width, float height) {
        this.x = cx;
        this.y = cy;
        this.width = width;
        this.height = height;
        if (bitmapResId != 0) {
            setBitmapResource(bitmapResId);
        }
        fixDstRect();
        createdOn = System.currentTimeMillis();
        Log.v(TAG, "Created " + this.getClass().getSimpleName() + "@" + System.identityHashCode(this));
    }

    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getDstWidth() {
        return dstRect.width();
    }

    public float getDstHeight() {
        return dstRect.height();
    }

    protected void setBitmapResource(int bitmapResId) {
        bitmap = BitmapPool.get(bitmapResId);
    }

    protected void fixDstRect() {
        setSize(width, height);
    }

    protected void setSize(float width, float height) {
        float half_width = width / 2;
        float half_height = height / 2;
        dstRect.set(x - half_width, y - half_height, x + half_width, y + half_height);
    }
    protected void setSize(float width, float height, float dx, float dy) {
        float half_width = width / 2;
        float half_height = height / 2;
        dstRect.set(dx - half_width, dy - half_height, dx + half_width, dy + half_height);
    }
    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

}