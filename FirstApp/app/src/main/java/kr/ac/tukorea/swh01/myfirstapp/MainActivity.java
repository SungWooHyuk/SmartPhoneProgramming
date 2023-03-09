package kr.ac.tukorea.swh01.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.PushMeButton);
        btn.setOnClickListener(this); // 나한테 알려주기, on clickedListener여야함.  interface를 지켜주기만하면됌
       // 이 this가 아래 onCLick을 가르킴
    }
    // MainActivity가 화면을 구성하고 버튼을 누르면 클릭을 감지하니까 onclick으로가서 text를 바꿔준다.
    // 인터페이스를 implements

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.PushMeButton:
                break;
        }
        TextView tv = findViewById(R.id.sNumTextView);
        tv.setText("PushMe button clicked");
    }
}