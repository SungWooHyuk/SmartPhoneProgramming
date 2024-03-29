package kr.ac.tukorea.swh02.jumpstopgame.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.GameView;
import kr.ac.tukorea.swh02.jumpstopgame.game.MainScene;
import kr.ac.tukorea.swh02.jumpstopgame.app.RankActivitiy;
import kr.ac.tukorea.swh02.jumpstopgame.game.Score;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    protected GameView gameView;
    private Context context;
    private MainScene mainScene;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        context = this;
        gameView = new GameView(this);
        gameView.setFullScreen();
        setContentView(gameView);
        new MainScene().pushScene();
    }


    @Override
    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

    @Override
    protected void onDestroy() {
        BaseScene.popAll();
        GameView.clear();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (gameView.handleBackKey()) {
            return;
        }
        super.onBackPressed();
    }

    public void GoTitle() {
        startActivity(new Intent(this, RankActivitiy.class));
    }

}