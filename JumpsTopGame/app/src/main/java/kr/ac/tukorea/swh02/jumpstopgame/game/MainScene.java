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

        add(Layer.TOUCH, new Button(R.mipmap.btn_jump_p, 4.f, 28.f, 5.0f, 3.f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    playerRed.jump();
                }
                return true;
            }
        }));
        add(Layer.TOUCH, new Button(R.mipmap.stop_p, 8.f, 28.f, 3.f, 3.f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {

                    playerRed.jump();
                }
                //Log.d(TAG, "Button: Fall");
                return true;
            }
        }));

        float btn_x = size(2.5f);
        float btn_y = size(8.75f);
        float btn_w = size(8.0f / 2.0f);
        float btn_h = size(1.5f);

        add(Layer.TOUCH, new Button(
                btn_x, btn_y, btn_w, btn_h, R.mipmap.bt_nomal, R.mipmap.bt_press,
                new Button.Calltoback() {
                    @Override
                    public boolean onTouch(Button.Action action,boolean pressed) {
                        if(action == Button.Action.moveLeft)
                        {
                            playerRed.setmovedir(1);
                        }
                        else if(action == Button.Action.moveRight)
                        {
                            playerRed.setmovedir(2);
                        }
                        else if(action == Button.Action.released)
                        {
                            playerRed.setmovedir(0);
                        }
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