package kr.ac.tukorea.swh02.jumpstopgame.game;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.Log;

import kr.ac.tukorea.swh02.jumpstopgame.app.MainActivity;
import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IGameObject;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Timer;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.BitmapPool;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class Score implements IGameObject {
    public FlyGen fly;

    public Timer timer = new Timer();
    private final Bitmap bitmap;
    private int srcCharWidth, srcCharHeight;
    private float right, top;
    private float dstCharWidth, dstCharHeight;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    private int score, displayScore;
    private int time = 0;
    private int st;

    public Score(int st) {
        this.bitmap = BitmapPool.get(R.mipmap.number_24x32);
        if (st == 0) {
            this.right = Metrics.game_width - (Metrics.game_width * 0.1f);
            this.top = 0.5f;
            this.dstCharWidth = 0.6f;
            this.srcCharWidth = bitmap.getWidth() / 10;
            this.srcCharHeight = bitmap.getHeight();
            this.dstCharHeight = dstCharWidth * srcCharHeight / srcCharWidth;
            this.st = 0;
        }
        else if (st == 1) {
            this.right = Metrics.game_width - (Metrics.game_width * 0.8f);
            this.top = 0.5f;
            this.dstCharWidth = 0.6f;
            this.srcCharWidth = bitmap.getWidth() / 10;
            this.srcCharHeight = bitmap.getHeight();
            this.dstCharHeight = dstCharWidth * srcCharHeight / srcCharWidth;
            this.st = 1;
        }
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public int getTime() {
        return time;
    }
    @Override
    public void update() {
        int diff = score - displayScore;
        int diff2 = time - displayScore;

        if (st == 0) {
            if (diff == 0) return;
            if (-10 < diff && diff < 0) {
                displayScore--;
            } else if (0 < diff && diff < 10) {
                displayScore++;
            } else {
                displayScore += diff / 10;
            }
        }
        else if (st==1)
        {
            if (diff2 == 0) return;
            if (-10 < diff2 && diff2 < 0) {
                displayScore--;
            } else if (0 < diff2 && diff2 < 10) {
                displayScore++;
            } else {
                displayScore += diff2 / 10;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        float x = right;
        while (value > 0) {
            int digit = value % 10;
            srcRect.set(digit * srcCharWidth, 0, (digit + 1) * srcCharWidth, srcCharHeight);
            x -= dstCharWidth;
            dstRect.set(x, top, x + dstCharWidth, top + dstCharHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            value /= 10;
        }
    }

    public void add(int amount) {
        score += amount;
    }

    public void timeadd(int amount)
    {
        time = amount;
    }

}
