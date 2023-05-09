package kr.ac.tukorea.swh02.jumpstopgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class Ground extends View {
    private Bitmap groundImage;
    protected RectF dstRect = new RectF();
    protected float x, y;
    private int groundWidth, groundHeight;
    private int screenWidth, screenHeight;
    public Ground(Context context) {
        super(context);
        init();
    }

    public Ground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Ground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Set up paint object for drawing the platform
        Resources res = getResources();
        groundImage = BitmapFactory.decodeResource(res, R.mipmap.ground);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        groundWidth = 800;
        groundHeight = 300;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the ground platform
        super.onDraw(canvas);

        // Calculate the left, top, right, and bottom coordinates of the platform
        float centerX = screenWidth / 2f - 170;
        float bottomY = screenHeight * 0.9f;
        x = centerX - (groundWidth / 2.f) + (centerX * 0.6f);
        y = bottomY - groundHeight - (bottomY * 0.2f);
        float right = x + groundWidth;
        float bottom = y + 300;

        // Set the coordinates of the destination rectangle for the platform image
        dstRect.set(x, y, right, bottom);

        // Draw the ground platform using the destination rectangle
        canvas.drawBitmap(groundImage, null, dstRect, null);
    }
    public float getGroundWidth() {
        return screenWidth;
    }

    public float getGroundHeight() {
        return screenHeight;
    }
}
