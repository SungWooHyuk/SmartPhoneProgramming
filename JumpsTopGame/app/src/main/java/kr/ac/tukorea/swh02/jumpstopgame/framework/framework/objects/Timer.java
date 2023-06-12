package kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects;

import static android.content.ContentValues.TAG;

import android.os.CountDownTimer;
import android.util.Log;

public class Timer {
    public int time = 0;
    public int m_stage = 1;
    public void Timer(int stage) {
        new CountDownTimer(5000 * stage, 1000) { // 30초(30000밀리초), 1초(1000밀리초) 간격으로 작업 수행
            public void onTick(long millisUntilFinished) {
                // 초를 세는 중일 때 매 초마다 호출되는 콜백 메서드
                time++;
                long secondsRemaining = millisUntilFinished / 1000;
                // 초를 처리하는 로직 추가
                // 예: 초를 표시하는 TextView 업데이트
                //Log.d(TAG, "millisUntilFinished = " + time);
                //textView.setText("남은 시간: " + secondsRemaining + "초");
            }

            public void onFinish() {
                Timer(stage);
            }
        }.start();
    }

    public int getTime()
    {
        return time;
    }
}
