package kr.ac.tukorea.swh02.jumpstopgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GameView extends View {
    private BackGround background;
    private Ground ground;
    private Player player;
    //private Bitmap backgroundimage;
    //private Bitmap ground;
    private RectF backRect = new RectF();
    private RectF jumpBtnRect = new RectF();
    public static float frameTime;
    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Resources res = getResources();
        // Create instances of the background and scaffold views
        background = new BackGround(getContext());
        ground = new Ground(getContext());
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.mipmap.player);
        Player.setBitmap(playerBitmap);

        player = new Player(ground);

        int btnWidth = 100; // Width of jump button
        int btnHeight = 50; // Height of jump button
        int btnMargin = 10; // Margin between jump button and edge of screen
        jumpBtnRect.set(
                1000, // left
                1000, // top
                1000, // right
                30 // bottom
        );
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw the background and scaffold views
        background.draw(canvas);
        ground.draw(canvas);
        player.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(jumpBtnRect, paint);
        // Draw other game objects here...
    }

    public void update(long elapsedNanos) {
        frameTime = elapsedNanos / 1_000_000_000f;
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (jumpBtnRect.contains(event.getX(), event.getY())) {
                    player.jump();
                }
                break;
            // 생략...
        }
        return true;
    }
}
