package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.AnimSprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Sprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.BitmapPool;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class Player extends Sprite implements IBoxCollidable {
    private static final float PLAYER_SIZE_RATIO = 0.2f; // 플레이어의 크기 비율

    private static final float FIGHTER_Y_OFFSET = 10.2f;
    private static final float PLAYER_WIDTH = 32 * 0.1f; //1.75f;
    private static final float PLAYER_HEIGHT = 32 * 0.1f; //1.75f;
    private static final float SPEED = 10.0f;
    private static final float FIGHTER_LEFT = PLAYER_WIDTH / 2;
    private static final float FIGHTER_RIGHT = 9.0f - PLAYER_WIDTH / 2;
    private static final String TAG = Player.class.getSimpleName();
    private float tx;
    private static final Rect[] rects = new Rect[] {
            new Rect(  0, 0,   0 + 32, 32),
            new Rect( 32, 0,  32 + 32, 32),
            new Rect(64, 0, 64 + 32, 32),
            new Rect(96, 0, 96 + 32, 32),
            new Rect(128, 0, 128 + 32, 32),
            new Rect(160, 0, 160 + 32, 32),
            new Rect(192, 0, 192 + 32, 32),
            new Rect(224, 0, 224 + 32, 32),
            new Rect(256, 0, 256 + 32, 32),
            new Rect(288, 0, 288 + 32, 32),
            new Rect(320, 0, 320 + 32, 32),
    };
    private RectF collisionRect; // RectF 객체 추가
    private static final int FRAME_COUNT = 11; // 스프라이트 이미지 프레임 개수
    private static final int FRAME_WIDTH = 32; // 스프라이트 이미지 프레임의 너비
    private static final int FRAME_HEIGHT = 32; // 스프라이트 이미지 프레임의 높이
    private static final float FPS = 10.0f; // 프레임 속도 (프레임/초)
    private float rollTime;
    private static final float MAX_ROLL_TIME = 0.4f;
    public Player() {
        super(R.mipmap.frog_idle, Metrics.game_width / 2, Metrics.game_height - FIGHTER_Y_OFFSET, PLAYER_WIDTH, PLAYER_HEIGHT);
        collisionRect = new RectF();
    }
    private static float calculatePlayerWidth() {
        return Metrics.getGameWidth() * PLAYER_SIZE_RATIO;
    }

    private static float calculatePlayerHeight() {
        return Metrics.getGameHeight() * PLAYER_SIZE_RATIO;
    }

    private static float calculatePlayerX() {
        return Metrics.getGameWidth() / 2 - calculatePlayerWidth() / 2;
    }

    private static float calculatePlayerY() {
        return Metrics.getGameHeight() / 2 - calculatePlayerHeight() / 2;
    }
    private static Rect calculatePlayerSize() {
        int playerWidth = (int) (Metrics.getGameWidth() * PLAYER_SIZE_RATIO);
        int playerHeight = (int) (Metrics.getGameHeight() * PLAYER_SIZE_RATIO);
        return new Rect(0, 0, playerWidth, playerHeight);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void update() {
        super.update();

        float time = BaseScene.frameTime;

    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(bitmap, rects[0], dstRect, null);

    }
}
