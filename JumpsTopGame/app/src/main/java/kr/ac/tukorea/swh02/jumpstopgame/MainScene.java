package kr.ac.tukorea.swh02.jumpstopgame;

import android.view.MotionEvent;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class MainScene extends BaseScene {
    private final Player player;

    public enum Layer {
        bg, platform, player, controller, ground, COUNT
    }

    public MainScene() {
        Metrics.setGameSize(16.0f, 15.0f);
        initLayers(Layer.COUNT);

        add(Layer.bg, new BackGround(R.mipmap.background, 1.0f));
        add(Layer.ground, new Ground(R.mipmap.ground));

        player = new Player();
        add(Layer.player, player);
        add(Layer.controller, new MapLoader());
        //add(Layer.controller, new MapLoader());
    }
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.jump();
        }
        return super.onTouchEvent(event);
    }
}
