package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;
import java.util.logging.Level;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.app.MainActivity;
import kr.ac.tukorea.swh02.jumpstopgame.app.RankActivitiy;
import kr.ac.tukorea.swh02.jumpstopgame.app.TitleActivity;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Button;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.Sound;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private MainActivity activity;
    private Player playerRed;
    private CollisionChecker ck;
    private static MainScene singleton;
    public static MainScene get() {
        if (singleton == null) {
            singleton = new MainScene();
        }
        return singleton;
    }

    public MainScene(MainActivity activity) {
        this.activity = activity;
    }
    public void switchToRankActivity() {
        Intent intent = new Intent(activity, RankActivitiy.class);
        activity.startActivity(intent);
    }
    public enum Layer {
        BG, ENEMY, WALL, SBOX, PLAYER, GROUND, TOUCH, ui, controller,  COUNT
    }
    private Score score;
    private Score timer;
    private FlyGen flygen;
    private Handler handler;
    private SafeBox safebox;
    private long startTime;
    public static int currentLevel = 1;

    public static int getCurrentLevel()
    {
        return currentLevel;
    }
    private Random rand = new Random();
    public static boolean LevelCollisionCheck = true;
    public static boolean MainSceneGo = true;
    public void setCurrentLevel(int lv)
    {
        currentLevel = lv;
    }

    public static int elapsedtime;
    public float size(float unit) {
        return Metrics.getGameHeight() / 9.5f * unit;
    }
    public float pos(float unit) {
        return unit;
    }
    public void addScore(int amount) {
        score.add(amount);
    }

    private Wall leftwall;
    private Wall rightwall;
    public int getScore() {
        return score.getScore();
    }
    public MainScene() {
        Metrics.setGameSize(18.0f, 32.0f);
        initLayers(Layer.COUNT);
        playerRed = new Player(
              9.f, size(6),
                size(1*0.9f), size(1 * 0.9f), Player.PlayerType.Red
        );
        ck = new CollisionChecker(playerRed);

        add(Layer.BG, new BackGround(R.mipmap.bg, 1.0f));
        leftwall = new Wall(R.mipmap.leftwall, 1.0f, 0);
        rightwall = new Wall(R.mipmap.rightwall, 1.0f, 1);
        add(Layer.WALL, leftwall);
        add(Layer.WALL, rightwall);
        score = new Score(0);
        timer = new Score(1);
        flygen = new FlyGen();
        add(Layer.ui, score);
        add(Layer.ui, timer);
        add(Layer.GROUND, new Ground(R.mipmap.ground));
        add(Layer.PLAYER, playerRed);
        add(Layer.controller, flygen);


        add(Layer.TOUCH, new Button(R.mipmap.jumpbutton, 4.f, 29.f, 3.5f, 3.5f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    if(playerRed.IsJumpState())
                        playerRed.JumpTimeFlip(true);
                }
                else if (action == Button.Action.released)
                {
                    if(playerRed.IsJumpState())
                    {
                        playerRed.JumpTimeFlip(false);
                        playerRed.jump();
                    }
                    //Sound.playMusic(R.raw.jump);
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

        add(Layer.controller, ck);
    }

    protected int getTouchLayerIndex() {
        return Layer.TOUCH.ordinal();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Sound.playMusic(R.raw.jumpstop);
        Sound.playEffect(R.raw.wall);
        Sound.playEffect(R.raw.jump);
        handler = new Handler();
        startTime = SystemClock.elapsedRealtime();
        handler.post(updateTimerRunnable);
    }

    @Override
    protected void onEnd() {
        super.onEnd();
        Sound.stopMusic();
    }

    int num = 0;
    int threetimecheck = 0;
    public static boolean b_threetimecheck = true;
    private boolean MainGo = false;
    int randomNumber = rand.nextInt(10)+5;
    boolean currentOK = false;
    private Runnable updateTimerRunnable = new Runnable() {
        @Override
        public void run() {
            long currentTime = SystemClock.elapsedRealtime();
            long realelapsedtime = (currentTime - startTime) / 1000;
            num++;
            elapsedtime = (int) realelapsedtime;
            timer.timeadd(elapsedtime);
            handler.postDelayed(this, 1000);

            if (LevelCollisionCheck) {
                if (num >= randomNumber) {
                    Sound.playEffect(R.raw.wall);
                    LevelCollisionCheck = false;
                    b_threetimecheck = false;
                    rightwall.ShowSafeBox();
                }
            }

            if (leftwall.CenterCheck() && rightwall.CenterCheck() && !LevelCollisionCheck) {

                if(!playerRed.getSafe()) {
                    if (playerRed.currentplayertype() == playerRed.blueplayer() && !currentOK) {
                        playerRed.change_ptype();
                        currentOK = true;
                    } else {
                        if (!currentOK) {
                            popAll();
                        }
                    }
                }
                threetimecheck++;
                if (threetimecheck > 3) {
                    b_threetimecheck = true;
                    MainGo = true;
                    threetimecheck = 0;
                    currentOK = false;
                }
            }

            if (MainGo && leftwall.OriginalCheck() && !LevelCollisionCheck) {

                    num = 0;
                    flygen.SetStage(flygen.GetStage() + 1);
                    setCurrentLevel(flygen.GetStage());
                    LevelCollisionCheck = true;
                    MainGo = false;

            }
        }
    };
}