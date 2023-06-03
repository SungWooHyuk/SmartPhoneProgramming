package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IGameObject;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class FlyGen implements IGameObject {
    private static final float GEN_INTERVAL = 1.0f;
    private Random rand = new Random();
    private float time;

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
        float size = rand.nextFloat() + 2;
        float speed = rand.nextFloat() * 0.5f + 1.0f;
        Fly fly = Fly.get(Fly.Type.blue, speed, size * 5);
        fly.moveTo(Metrics.getGameWidth()/2, Metrics.getGameHeight()/2 );
        MainScene scene = (MainScene) BaseScene.getTopScene();
        scene.add(MainScene.Layer.ENEMY, fly);
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
