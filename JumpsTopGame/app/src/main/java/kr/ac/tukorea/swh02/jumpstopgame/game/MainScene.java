package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.view.MotionEvent;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private Player playerRed;
    private static MainScene singleton;
    public static MainScene get() {
        if (singleton == null) {
            singleton = new MainScene();
        }
        return singleton;
    }
    public enum Layer {
        BG, PLAYER, GROUND, COUNT, CONTROLLER, enemy
    }
    public float size(float unit) {
        return Metrics.getGameHeight() / 9.5f * unit;
    }
    public MainScene() {
        Metrics.setGameSize(18.0f, 32.0f);
        initLayers(Layer.COUNT);
        playerRed = new Player(
                size(1), size(6),
                size(1*0.9f), size(1 * 0.9f), Player.PlayerType.Red
        );
        add(Layer.BG, new BackGround(R.mipmap.background, 1.0f));
        //add(Layer.GROUND, new Ground(R.mipmap.ground));
        add(Layer.PLAYER, playerRed);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //player.jump();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}