package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.view.MotionEvent;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Button;
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
        BG, PLAYER, GROUND, TOUCH, COUNT
    }
    public float size(float unit) {
        return Metrics.getGameHeight() / 9.5f * unit;
    }
    public float pos(float unit) {
        return unit;
    }
    public MainScene() {
        Metrics.setGameSize(18.0f, 32.0f);
        initLayers(Layer.COUNT);
        playerRed = new Player(
              9.f, size(6),
                size(1*0.9f), size(1 * 0.9f), Player.PlayerType.Red
        );
        add(Layer.BG, new BackGround(R.mipmap.background, 1.0f));
        add(Layer.GROUND, new Ground(R.mipmap.ground));
        add(Layer.PLAYER, playerRed);

        add(Layer.TOUCH, new Button(R.mipmap.btn_jump_n, 4.f, 30.f, 3.0f, 3.f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    playerRed.jump();
                }
                return true;
            }
        }));
        add(Layer.TOUCH, new Button(R.mipmap.btn_fall_n, 14.5f, 8.5f, 2.0f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    playerRed.jump();
                }
                //Log.d(TAG, "Button: Fall");
                return true;
            }
        }));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}