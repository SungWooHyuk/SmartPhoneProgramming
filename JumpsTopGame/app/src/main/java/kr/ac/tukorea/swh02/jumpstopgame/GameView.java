package kr.ac.tukorea.swh02.jumpstopgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
    private Bitmap backGround;
    private RectF backRect = new RectF();

    public GameView(Context context) {
        super(context);
        init(null, 0);
    }
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }
    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    private void init(AttributeSet attrs, int defStyle) {
        Resources res = getResources();
        backGround = BitmapFactory.decodeResource(res, R.mipmap.background);
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Get the dimensions of the screen
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        // Create a new RectF object that fills the entire screen
        RectF screenRect = new RectF(0, 0, screenWidth, screenHeight);

        // Draw the background image using the new RectF object
        canvas.drawBitmap(backGround, null, screenRect, null);
    }
}
