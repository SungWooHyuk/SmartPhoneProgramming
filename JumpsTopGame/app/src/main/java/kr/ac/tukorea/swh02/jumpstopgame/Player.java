package kr.ac.tukorea.swh02.jumpstopgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Player {
    private static Bitmap bitmap;
    private RectF dstRect = new RectF();
    private float x, y; // 플레이어 위치
    private float jumpSpeed;
    private static final float JUMP_POWER = 9.0f;
    private static final float GRAVITY = 17.0f;
    protected enum State {
        idle, jump
    }
    protected State state = State.idle;
    public Player() {
        float cx = 100.f, y = 100.0f;
        float r = 100.f;
        dstRect.set(cx-r, y, cx+r, y+2*r);
    }
    public Player(Ground ground) {
        x = ground.getGroundWidth() * 0.5f;
        y = ground.getGroundHeight()* 0.5f;
        float r = 100.f;
        dstRect.set(x-r, y, x+r, y+2*r);
    }
    public static void setBitmap(Bitmap bitmap) {
        Player.bitmap = bitmap;
    }

    public void update() {
        if (state == State.jump) {
            float dy = jumpSpeed * GameView.frameTime;
            jumpSpeed += GRAVITY * GameView.frameTime;
            if (y + dy >= y) {
                dy = y - y;
                state = State.idle;
            }
            y += dy;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    public void jump() {
        if (state == State.idle) {
            state = State.jump;
            jumpSpeed = -JUMP_POWER;
        }
    }
}
