package kr.ac.tukorea.swh02.termproject.JumpsTop.game;

import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.swh02.termproject.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.swh02.termproject.framework.interfaces.IRecyclable;
import kr.ac.tukorea.swh02.termproject.framework.objects.Sprite;
import kr.ac.tukorea.swh02.termproject.framework.scene.BaseScene;

public class MapObject extends Sprite implements IBoxCollidable, IRecyclable {
    private static final String TAG = MapObject.class.getSimpleName();
    public static final float SPEED = 3.0f;
    protected MainScene.Layer layer;
    protected MapObject(MainScene.Layer layer) {
        this.layer = layer;
    }

    @Override
    public void update() {
        float dx = -SPEED * BaseScene.frameTime;
        dstRect.offset(dx, 0);
        if (dstRect.right < 0) {
//            Log.d(TAG, "Removing:" + this);
            BaseScene.getTopScene().remove(layer, this);
        }
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }
    @Override
    public void onRecycle() {}
}
