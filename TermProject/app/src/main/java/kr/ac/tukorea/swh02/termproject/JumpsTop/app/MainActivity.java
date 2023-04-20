package kr.ac.tukorea.swh02.termproject.JumpsTop.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.swh02.termproject.JumpsTop.game.MainScene;
import kr.ac.tukorea.swh02.termproject.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.termproject.framework.view.GameView;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        super.onDestroy();
    }
}
