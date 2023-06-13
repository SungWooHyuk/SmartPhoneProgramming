package kr.ac.tukorea.swh02.jumpstopgame.app;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.databinding.ActivityMainBinding;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.Sound;

public class RankActivitiy extends AppCompatActivity {
    private static final String TAG = RankActivitiy.class.getSimpleName();
    private ActivityMainBinding binding;
    private ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createAnimator();
    }
    private void createAnimator() {
        animator = ValueAnimator.ofFloat(0.0f, 0.5f);
        animator.setDuration(30000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnimation();
    }

    @Override
    protected void onPause() {
        stopAnimation();
        super.onPause();
    }

    protected void startAnimation() {
        animator.start();
    }

    protected void stopAnimation() {
        animator.end();
    }
    public void onBtnStart(View view) {
        startActivity(new Intent(this, TitleActivity.class));
    }
}