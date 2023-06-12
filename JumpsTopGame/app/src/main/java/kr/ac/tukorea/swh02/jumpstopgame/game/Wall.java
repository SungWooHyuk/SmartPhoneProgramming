package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Sprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class Wall extends Sprite {
    private static final String TAG = Wall.class.getSimpleName();

    private final float speed;
    private final float height;
    private float scroll;
    private float m_startX = 0.f;
    private float m_update;
    private int m_num;
    public Wall(int bitmapResId, float speed, int num) {
        super(bitmapResId, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        this.height = bitmap.getHeight() * Metrics.game_width / bitmap.getWidth();
        setSize(Metrics.game_width, height);
        this.speed = speed;
        this.m_num = num;

        if(num == 0) // left
        {
            this.m_startX = - (Metrics.game_width / 2.f - (Metrics.game_width * 0.1f));
        }
        else if(num == 1) // right
        {
            this.m_startX = (Metrics.game_width / 2.f - (Metrics.game_width * 0.1f));
        }
    }

    @Override
    public void update() {
        scroll += speed * BaseScene.frameTime;

    }

    @Override
    public void draw(Canvas canvas) {
        float curr = scroll % height;
        if (curr > 0) curr -= height;
        while (curr < Metrics.game_height) {
            float high = curr+height;
            dstRect.set(m_startX, curr, Metrics.game_width + m_startX, high);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += height;
        }
    }
}
