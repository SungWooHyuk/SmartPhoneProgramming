package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Sprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class SafeBox extends Sprite implements IBoxCollidable {

    private static final float BULLET_WIDTH = 28 * 0.0243f;
    private static final float BULLET_HEIGHT = 40 * 0.0243f;

    private static final String TAG = SafeBox.class.getSimpleName();
    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
    private float height;

    public static SafeBox get(float x, float y, int level) {
        return new SafeBox(x, y, level);
    }
    public SafeBox(float x, float y, int level) {
        super(R.mipmap.square, x , y , Metrics.game_width / 4.f / level, Metrics.game_height / 6.8f / level);
        this.x = x;
        this.y = y;
    }
    @Override
    public void update() {
        if(MainScene.LevelCollisionCheck)
            BaseScene.getTopScene().remove(MainScene.Layer.SBOX, this);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

}
