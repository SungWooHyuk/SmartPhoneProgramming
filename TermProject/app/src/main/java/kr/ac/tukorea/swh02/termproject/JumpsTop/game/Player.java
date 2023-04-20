package kr.ac.tukorea.swh02.termproject.JumpsTop.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.tukorea.swh02.termproject.R;
import kr.ac.tukorea.swh02.termproject.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.swh02.termproject.framework.interfaces.IGameObject;
import kr.ac.tukorea.swh02.termproject.framework.objects.AnimSprite;
import kr.ac.tukorea.swh02.termproject.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.termproject.framework.util.CollisionHelper;
import kr.ac.tukorea.swh02.termproject.framework.view.Metrics;

public class Player extends AnimSprite {

    public Player() {
        super(R.mipmap.player_sheet, 2.0f, 10.f, 3.86f, 10.f, 8, 4); // display the first image in the sprite sheet
    }
}
