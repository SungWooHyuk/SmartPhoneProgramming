package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IGameObject;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class FlyGen implements IGameObject {
    private static final String TAG = FlyGen.class.getSimpleName();
    private static final float GEN_INTERVAL = 1.0f;
    private Random rand = new Random();
    private float time;
    private int m_stage;

    public int GetStage()
    {
        return m_stage;
    }

    public void SetStage(int stage)
    {
        m_stage = stage;
    }


    @Override
    public void update() {
        time += BaseScene.frameTime;
        if (time >= GEN_INTERVAL) {
            spawn();
            time -= GEN_INTERVAL;
        }
    }

    private void spawn() {
        float y = rand.nextFloat() * Metrics.game_height;

        while(y > Metrics.game_height / 1.6f)
        {
            y = rand.nextFloat() * Metrics.game_height;
        }

        float size = rand.nextFloat() + 3.f;
        float speed = 0.5f*100.f + 3.0f;
        Log.d(TAG,"Time" + speed);
        Fly fly = Fly.get(Fly.Type.blue, speed, size, 0.f, y , m_stage);
        fly.moveTo(fly.px, fly.py);
        MainScene scene = (MainScene) BaseScene.getTopScene();
        scene.add(MainScene.Layer.ENEMY, fly);
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
