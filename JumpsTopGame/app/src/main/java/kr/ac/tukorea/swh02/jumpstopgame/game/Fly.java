package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import java.util.Random;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IRecyclable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.SheetSprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.RecycleBin;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.util.Gauge;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class Fly extends SheetSprite implements IRecyclable, IBoxCollidable {
    private static final String TAG = Fly.class.getSimpleName();
    private Type type;
    private float speed, distance;

    protected Rect srcRect = new Rect();
    protected RectF collisionBox = new RectF();
    @Override
    public RectF getCollisionRect() {
        return collisionBox;
    }

    public enum Type {
        boss, red, blue, cyan, dragon, COUNT, RANDOM;
    }
    public static Fly get(Type type, float speed, float size) {
        Fly fly = (Fly) RecycleBin.get(Fly.class);
        if (fly == null) {
            fly = new Fly();
        }
        fly.init(type, speed, size);
        return fly;
    }

    private Fly() {
        super(R.mipmap.galaga_flies, 2.0f);
        if (rects_array == null) {
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            rects_array = new Rect[Type.COUNT.ordinal()][];
            int x = 0;
            for (int i = 0; i < Type.COUNT.ordinal(); i++) {
                rects_array[i] = new Rect[2];
                for (int j = 0; j < 2; j++) {
                    rects_array[i][j] = new Rect(x, 0, x+h, h);
                    x += h;
                }
            }
        }
    }

    private Rect[][] rects_array;
    private void init(Type type, float speed, float size) {
        this.type = type;
        this.speed = speed;
        this.width = this.height = size;
        this.distance = 0;
        srcRects = rects_array[type.ordinal()];
    }

    @Override
    public void update() {
        super.update();
        distance += speed * BaseScene.frameTime;
        moveTo(distance, y);
        collisionBox.set(dstRect);
    }

    @Override
    public void onRecycle() {
    }


}