package kr.ac.tukorea.swh02.jumpstopgame.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.tukorea.swh02.jumpstopgame.game.MainScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.InGameView;

public class MainActivity extends AppCompatActivity {
    private InGameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new InGameView(this);
        gameView.setFullScreen();
        setContentView(gameView);

        new MainScene().pushScene();
    }
}