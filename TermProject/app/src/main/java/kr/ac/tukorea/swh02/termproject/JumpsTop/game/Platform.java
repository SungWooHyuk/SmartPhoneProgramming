package kr.ac.tukorea.swh02.termproject.JumpsTop.game;

import androidx.annotation.NonNull;

import java.util.Random;

import kr.ac.tukorea.swh02.termproject.R;
import kr.ac.tukorea.swh02.termproject.framework.scene.RecycleBin;

public class Platform extends MapObject {
    private Type type;

    public boolean canPass() {
        return type == Type.GOUND;
    }
    public enum Type {
        GOUND;
        int resId() { return resIds[this.ordinal()]; }
        static int[] resIds = {
                R.mipmap.ground
        };
    }
    private Platform() {
        super(MainScene.Layer.platform);
    }
    public static Platform get(Type type, float left, float top) {
        Platform platform = (Platform) RecycleBin.get(Platform.class);
        if (platform == null) {
            platform = new Platform();
        }
        platform.init(type, left, top);
        return platform;
    }
    public void init(Type type, float left, float top) {
        this.type = type;
        setBitmapResource(type.resId());
        //width = type.width();
        //height = type.height();
        // Platform 은 x,y 를 사용하지 않고 dstRect 만을 사용하도록 한다.
        //dstRect.set(left, top, left + width, top + height);
    }

    @NonNull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + System.identityHashCode(this) + "(" + width + "x" + height + ")";
    }
}
