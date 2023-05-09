package kr.ac.tukorea.swh02.jumpstopgame;

import android.graphics.Canvas;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Sprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class Ground extends Sprite {

    private final float height;

    public Ground(int bitmapResId) {
        super(bitmapResId, Metrics.game_width / 8, Metrics.game_height / 8, Metrics.game_width, Metrics.game_height);
        this.height = (bitmap.getHeight() * Metrics.game_width / bitmap.getWidth()) / 0.6f;
        setSize(Metrics.game_width, height);
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        dstRect.set(2, 5, Metrics.game_width , height );
        canvas.drawBitmap(bitmap, null, dstRect, null);

    }

}
