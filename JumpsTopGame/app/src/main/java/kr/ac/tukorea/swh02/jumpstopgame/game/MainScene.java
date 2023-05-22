package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.view.MotionEvent;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Player player;

    public enum Layer {
        BG, PLATFORM, PLAYER, GROUND, COUNT
    }

    public MainScene() {
        Metrics.setGameSize(18.0f, 32.0f);
        initLayers(Layer.COUNT);

        add(Layer.BG, new BackGround(R.mipmap.background, 1.0f));
        add(Layer.GROUND, new Ground(R.mipmap.ground));

        player = new Player();
        add(Layer.PLAYER, player);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //player.jump();
        }
        return super.onTouchEvent(event);
    }
}