package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.util.Log;
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
        BG, PLAYER, GROUND, TOUCH, ENEMY, controller, COUNT
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
        add(Layer.controller, new FlyGen());
        add(Layer.TOUCH, new Button(R.mipmap.jumpbutton, 4.f, 29.f, 3.5f, 3.5f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    playerRed.jump();
                }
                return true;
            }
        }));
        add(Layer.TOUCH, new Button(R.mipmap.stopbutton, 7.5f, 29.f, 3.15f, 3.15f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                    playerRed.Stop(action == Button.Action.pressed);
                    return true;
                }
            }));

        add(Layer.TOUCH, new Button(R.mipmap.leftbutton, 11.f, 29.f, 3.15f, 3.15f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                    playerRed.setmovedir(1, action == Button.Action.pressed);
                    return true;
            }
        }));

        add(Layer.TOUCH, new Button(R.mipmap.rightbutton, 14.5f, 29.f, 3.5f, 3.5f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                    playerRed.setmovedir(2, action == Button.Action.pressed);
                    return true;
            }
        }));


    }

    protected int getTouchLayerIndex() {
        return Layer.TOUCH.ordinal();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}