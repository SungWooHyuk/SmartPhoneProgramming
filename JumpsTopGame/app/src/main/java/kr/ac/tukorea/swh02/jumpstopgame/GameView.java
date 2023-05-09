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
    private BackGround background;
    private Ground ground;

    //private Bitmap backgroundimage;
    //private Bitmap ground;
    private RectF backRect = new RectF();

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
        // Create instances of the background and scaffold views
        background = new BackGround(getContext());
        ground = new Ground(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the background and scaffold views
        background.draw(canvas);
        ground.draw(canvas);

        // Draw other game objects here...
    }
}
