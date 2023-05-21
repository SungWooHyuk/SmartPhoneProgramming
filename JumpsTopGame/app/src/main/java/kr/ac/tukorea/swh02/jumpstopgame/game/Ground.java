package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Sprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class Ground extends Sprite {

    private final float height = 10.f;

    public Ground(int bitmapResId) {
        super(bitmapResId, Metrics.game_width / 2, Metrics.game_height / 8, Metrics.game_width /2 , Metrics.game_height / 8);
        //this.height = (bitmap.getHeight() * Metrics.game_width / bitmap.getWidth()) / 0.6f;
        //setSize(Metrics.game_width, height);
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        dstRect.set(3.f, 32.f - height, 17.f, 28.f );
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public boolean collidesWith(RectF otherRect) {
        return RectF.intersects(dstRect, otherRect);
    }

}
