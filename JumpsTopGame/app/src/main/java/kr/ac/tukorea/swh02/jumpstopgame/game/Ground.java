package kr.ac.tukorea.swh02.jumpstopgame.game;


import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Sprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class Ground extends Sprite {

    private float height;

    public Ground(int bitmapResId) {
        super(bitmapResId, Metrics.game_width , Metrics.game_height , Metrics.game_width  , Metrics.game_height );
        this.height = (bitmap.getHeight() * Metrics.game_width / bitmap.getWidth());
        setSize(Metrics.game_width, height);
    }
    @Override
    public void update() {

    }
    public RectF getCollisionRect() {
        return dstRect;
    }
    @Override
    public void draw(Canvas canvas) {
        dstRect.set(0.f, Metrics.game_height - height, Metrics.game_width + Metrics.game_width / 6.f, Metrics.game_height - Metrics.game_height/8.f );
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public boolean collidesWith(RectF otherRect) {
        return RectF.intersects(dstRect, otherRect);
    }

}
