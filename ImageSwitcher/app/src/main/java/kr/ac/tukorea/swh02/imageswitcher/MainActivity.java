package kr.ac.tukorea.swh02.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private int page;
    private ImageView mainImageView;
    private TextView pageTextView;
    private ImageButton prevBtn, nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImageView = findViewById(R.id.mainImageView); // find할때마다 항상 같은 값인데, find값에 드는 비용은 시간이 좀 걸린다.
        pageTextView = findViewById(R.id.pageTextView);
        prevBtn = findViewById(R.id.prevBtn);
        nextBtn = findViewById(R.id.nextBtn);

        setPage(1);
    }

    public void onBtnPrev(View view) {
        Log.d(TAG, "Prev Button Clicked!!");
        setPage(page - 1);
    }

    public void onBtnNext(View view) {
        Log.d(TAG, "Next Button Clicked!!");
        setPage(page + 1);
    }

    private static final int[] IMG_RES_IDS = new int[] { // mainactivity와 라이프사이클이 같다. 메모리를 계속 차지하고있긴함. 시간은 더 빨라짐
            R.mipmap.cat_1,
            R.mipmap.cat_2,
            R.mipmap.cat_3,
            R.mipmap.cat_4,
            R.mipmap.cat_5,
            R.mipmap.cat_6,
    };
    private void setPage(int page) {
        if(page < 1 || page > IMG_RES_IDS.length) return;

        int resId = IMG_RES_IDS[page - 1];
        mainImageView.setImageResource(resId);
        pageTextView.setText(page + " / " + IMG_RES_IDS.length);

        prevBtn.setEnabled(page > 1);
        nextBtn.setEnabled(page < IMG_RES_IDS.length);

        this.page = page;
    }
}