package kr.ac.tukorea.swh01.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // 바깥에 만들면 멤버변수
    public void onBtnPushMe(View view) {
        TextView tv = findViewById(R.id.sNumTextView);
        tv.setText("PushMe");
    }

    public void onBtnHello(View view) {
        TextView tv = findViewById(R.id.sNumTextView);
        tv.setText("Hello");
    }

}