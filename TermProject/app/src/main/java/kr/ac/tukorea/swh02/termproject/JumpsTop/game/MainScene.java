package kr.ac.tukorea.swh02.termproject.JumpsTop.game;

import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;

import kr.ac.tukorea.swh02.termproject.R;
import kr.ac.tukorea.swh02.termproject.framework.objects.Sprite;
import kr.ac.tukorea.swh02.termproject.framework.objects.Button;
import kr.ac.tukorea.swh02.termproject.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.termproject.framework.view.Metrics;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Player player;

    public enum Layer {
        bg, platform, item, obstacle, player, ui, touch, controller, COUNT
    }
    public MainScene() {
        Metrics.setGameSize(20.0f, 30.0f);
        initLayers(Layer.COUNT);

        add(Layer.bg, new VertScrollBackground(R.mipmap.background, -1.f));

        player = new Player();
        add(Layer.player, player);

        add(Layer.touch, new Button(R.mipmap.btn_slide_n, 1.5f, 8.0f, 2.0f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                //Log.d(TAG, "Button: Slide");
                //player.slide(action == Button.Action.pressed);
                return true;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.btn_jump_n, 14.5f, 7.7f, 2.0f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                   // player.jump();
                }
                //Log.d(TAG, "Button: Jump");
                return true;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.btn_fall_n, 14.5f, 8.5f, 2.0f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                   // player.fall();
                }
                //Log.d(TAG, "Button: Fall");
                return true;
            }
        }));
        add(Layer.controller, new MapLoader());
        //add(Layer.controller, new CollisionChecker(player));
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            player.jump();
//        }
//        return super.onTouchEvent(event);
//    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
}
