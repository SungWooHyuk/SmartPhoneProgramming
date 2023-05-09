package kr.ac.tukorea.swh02.jumpstopgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

public class GameView extends View {
    private BackGround background;
    private Ground ground;
    private Player player;
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
        Resources res = getResources();
        // Create instances of the background and scaffold views
        background = new BackGround(getContext());
        ground = new Ground(getContext());
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.mipmap.player);
        Player.setBitmap(playerBitmap);

        player = new Player(ground);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw the background and scaffold views
        background.draw(canvas);
        ground.draw(canvas);
        player.draw(canvas);

        // Draw other game objects here...
    }
}
