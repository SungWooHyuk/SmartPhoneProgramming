package kr.ac.tukorea.swh02.jumpstopgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class BackGround extends View {

    protected Bitmap backgroundImage;
    protected RectF dstRect = new RectF();
    protected float x, y;
    private int screenWidth, screenHeight;

    public BackGround(Context context) {
        super(context);
        init();
    }

    public BackGround(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BackGround(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Load the background image from resources
        Resources res = getResources();
        backgroundImage = BitmapFactory.decodeResource(res, R.mipmap.background);
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the background image
        super.onDraw(canvas);

        // Set the coordinates of the destination rectangle for the background image
        dstRect.set(0, 0, screenWidth, screenHeight);

        // Draw the background image using the destination rectangle
        canvas.drawBitmap(backgroundImage, null, dstRect, null);
    }
}