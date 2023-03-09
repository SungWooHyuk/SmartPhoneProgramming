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

        Button btn = findViewById(R.id.PushMeButton);
        btn.setOnClickListener(new View.OnClickListener() { // 즉석에서 객체 하나 만들어서 던져줌
            @Override
            public void onClick(View view) {
                TextView tv = findViewById(R.id.sNumTextView);
                tv.setText("PushMe");
            }
        });
    }
}