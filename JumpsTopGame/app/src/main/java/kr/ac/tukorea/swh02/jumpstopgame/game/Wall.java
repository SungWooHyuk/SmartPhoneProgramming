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
    private FlyGen flyGen = new FlyGen();
    private final float height;
    private float scroll;
    private float m_startX = 0.f;
    private float m_startX2 = 0.f;
    private float m_update;
    private Score score = new Score(1);
    private int m_num;

    public Wall(int bitmapResId, float speed, int num) {
        super(bitmapResId, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        this.height = bitmap.getHeight() * Metrics.game_width / bitmap.getWidth();
        setSize(Metrics.game_width, height);
        this.speed = speed;
        this.m_num = num;

        if (num == 0) // left
        {
            this.m_startX = -(Metrics.game_width / 2.f - (Metrics.game_width * 0.1f));
            this.m_startX2 = -(Metrics.game_width / 2.f - (Metrics.game_width * 0.1f));
        } else if (num == 1) // right
        {
            this.m_startX = (Metrics.game_width / 2.f - (Metrics.game_width * 0.1f));
            this.m_startX2 = (Metrics.game_width / 2.f - (Metrics.game_width * 0.1f));
        }
    }

    private boolean leftwallreset = false;
    private boolean rightwallreset = false;
    private int shake = 0;
    private boolean bshake = true;
    private int shake2 = 0;
    private boolean bshake2 = true;

    private boolean centerOk = false;
    private boolean originalOK = false;

    public boolean CenterCheck() {
        return centerOk;
    }

    public boolean OriginalCheck() {
        return originalOK;
    }
    int whiling = 0;
    @Override
    public void update() {
        Log.d(TAG,"stage"+ MainScene.getCurrentLevel());
        if (!MainScene.LevelCollisionCheck) {
            originalOK = false;
            if (m_num == 0) // left
            {
                if (m_startX >= 0.f) {
                    centerOk = true;
                    bshake = true;
                }
                else {
                    if (bshake) {
                        m_startX -= (speed * 0.5 * BaseScene.frameTime);
                        if(m_startX <= -8.2f)
                            bshake = false;
                    }
                    else {

                        m_startX += (speed * MainScene.getCurrentLevel() * BaseScene.frameTime);
                        bshake = false;
                    }
                }

                if (MainScene.b_threetimecheck) {
                    if (m_startX <= m_startX2) {
                        centerOk = false;
                        originalOK = true;
                    } else {
                        m_startX += -(speed * 10 * BaseScene.frameTime);
                        centerOk = false;
                    }
                }
            }

            if (m_num == 1) // right
            {
                if (m_startX <= 0.f) {
                    centerOk = true;
                } else {
                    if(bshake2){
                        m_startX += speed * 0.5 * BaseScene.frameTime;
                        if(m_startX >= 8.2f)
                            bshake2 = false;
                    }
                    else
                    {
                        m_startX -= speed * MainScene.getCurrentLevel() * BaseScene.frameTime;
                        bshake2 = false;
                    }
                }

                if (MainScene.b_threetimecheck) {
                    if (m_startX >= m_startX2) {
                        centerOk = false;
                        originalOK = true;
                    } else {
                        m_startX += (speed * 10 * BaseScene.frameTime);
                        centerOk = false;
                    }
                }
            }
        }
        else {
            bshake = true;
                bshake2 = true;
                scroll += speed * BaseScene.frameTime;
            }
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
