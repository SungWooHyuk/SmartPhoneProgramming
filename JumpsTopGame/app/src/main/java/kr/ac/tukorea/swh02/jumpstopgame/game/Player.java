package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.Gravity;

import java.util.ArrayList;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IGameObject;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IRecyclable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.AnimSprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.SheetSprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Sprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.BitmapPool;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.RecycleBin;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class Player extends SheetSprite implements IBoxCollidable{
    private static final float FRAMES_PER_SECOND = 8.f;
    private static final String TAG = Player.class.getSimpleName();
    private  CollisionChecker collisionChecker = new CollisionChecker(this);
    private Ground ground;
    private final float jumpPower;
    private final float gravity;
    private float save_pos_x;
    private  float save_pos_y;

    static {
        State.initRects();
    }

    @Override
    public RectF getCollisionRect() {
        return collisionBox;
    }

    public enum  movestate{
        left,right,stop ,COUNT;
    }
    public enum PlayerType{
        Red,Blue,COUNT;
        public int playerBitmap(PlayerType type){
            if (type == PlayerType.Red){
                return R.mipmap.frog_idle;
            }
            else if(type == PlayerType.Blue){
                return R.mipmap.frog_idle;
            }
            return  0;
        }
    }


    private enum State {
        run, jump,idle, falling, slide, COUNT;
        Rect[] srcRects() {
            return rectsArray[this.ordinal()];
        }
        void applyInsets(RectF dstRect) {
            float[] inset = insets[this.ordinal()];
            float w = dstRect.width();
            float h = dstRect.height();

            dstRect.set(
                    dstRect.left += w * inset[0],
                    dstRect.top += h * inset[1],
                    dstRect.right -= w * inset[2],
                    dstRect.bottom -= h * inset[3]);
        }
        static Rect[][] rectsArray;
        static void initRects() {
            int[][] indices = {
                    new int[] { 0,1,2,3,4,5,6,7,8,9,10}, // run
                    new int[] { 0,}, // jump
                    new int[] { 0,1,2,3,4,5,6}, // jump
                    new int[] { 0,}, // doubleJump
                    new int[] { 0 }, // falling
                    new int[] { 9, 10 },    //slide
            };
            rectsArray = new Rect[indices.length][];
            for (int r = 0; r < indices.length; r++) {
                int[] ints = indices[r];
                Rect[] rects = new Rect[ints.length];

                for (int i = 0; i < ints.length; i++) {
                    int idx = ints[i];
                    int l = (idx % 100) * 32;
                    Rect rect = new Rect(l, 0, l+32,   32);
                    rects[i] = rect;
                }

                rectsArray[r] = rects;
            }
        }
        float[][] insets = {
                new float[] { 0.10f, 0.05f, 0.10f, 0.00f }, // run
                new float[] { 0.10f, 0.20f, 0.10f, 0.00f }, // jump
                new float[] { 0.10f, 0.15f, 0.10f, 0.00f }, // doubleJump
                new float[] { 0.10f, 0.05f, 0.10f, 0.00f }, // falling
                new float[] { 0.00f, 0.50f, 0.00f, 0.00f }, // slide
        };
    }
    private State state = State.run;

    private float jumpSpeed;
    private float moveSpeed;
    private float fliction;
    public movestate movedir;
    private  float m_w;
    private  float m_h;
    private PlayerType p_type;
    protected static RectF collisionBox = new RectF();

    public Player(float x, float y, float w, float h, PlayerType type ) {

        super(type.playerBitmap(type), FRAMES_PER_SECOND * 4);
        this.x = x;
        this.y = y;
        m_w =w/1.5f;
        m_h = h/1.5f;
        save_pos_x = x;
        save_pos_y = y;
        jumpPower = 30.f;
        moveSpeed = 4.f;
        gravity = 20.8f;
        setDstRect(w/1.5f, h/1.5f);
        setState(State.falling);
        SetBitmapflipSize(32);
        p_type = type;
    }
    public RectF getBoundingRect() {
        return collisionBox;
    }

    public void InitPlayer()
    {
        this.x = save_pos_x;
        this.y = save_pos_y;
        setDstRect(m_w, m_h);
        collisionBox.set(dstRect);
        setState(State.falling);
    }



    private Rect[][] rects_array;

    public void update() {
        super.update();
        switch (state)
        {
            case falling:
                float dy = jumpSpeed * BaseScene.frameTime;
                float dx = 0.f;

                jumpSpeed += gravity * BaseScene.frameTime;
                if (jumpSpeed >= 0)
                {
                    float foot = collisionBox.bottom;
                    float floor = 24.5f;

                    if (foot + dy >= floor) {
                        dy = floor - foot;
                        state = State.idle;
                        setState(State.idle);
                    }
                }
                y += dy;

                if( movedir == movestate.left)
                {
                    if(dstRect.left > 2.f) {
                        dx = -moveSpeed * BaseScene.frameTime;
                        x += dx;
                    }
                }
                if( movedir == movestate.right)
                {
                    if(dstRect.right < 16.f) {
                        dx = moveSpeed * BaseScene.frameTime;
                        x += dx;
                    }
                }

                Log.d(TAG, "dx:" + dx);
                dstRect.offset(dx, dy);
                collisionBox.set(dstRect);
                state.applyInsets(collisionBox);
                break;
            case run:
                float fx = 0;
                if( movedir == movestate.left)
                {
                    if(dstRect.left > 2.f) {
                        fx = -moveSpeed * BaseScene.frameTime;
                        x += fx;
                    }
                }
                if( movedir == movestate.right)
                {
                    if(dstRect.right < 16.f) {
                        fx = moveSpeed * BaseScene.frameTime;
                        x += fx;
                    }
                }
                dstRect.offset(fx, 0);
                collisionBox.set(dstRect);
                state.applyInsets(collisionBox);

                break;
            case idle:
                dstRect.offset(0, 0);
                collisionBox.set(dstRect);
                state.applyInsets(collisionBox);
                break;
        }
    }
    private float findNearestPlatformTop() {
        return ground.getCollisionRect().top;
    }

    private Ground findNearestPlatform(float foot) {
        Ground nearest = null;
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<IGameObject> platforms = scene.getObjectsAt(MainScene.Layer.GROUND);
        float top = Metrics.game_height - (bitmap.getHeight() * Metrics.game_width / bitmap.getWidth());
        for (IGameObject obj: platforms) {
            Ground platform = (Ground) obj;
            RectF rect = platform.getCollisionRect();
            if (rect.left > x || x > rect.right) {
                continue;
            }
            //Log.d(TAG, "foot:" + foot + " platform: " + rect);
            if (rect.top < foot) {
                continue;
            }
            if (top > rect.top) {
                top = rect.top;
                nearest = platform;
            }
            //Log.d(TAG, "top=" + top + " gotcha:" + platform);
        }
        return nearest;
    }
    public void setmovedir(int dir, boolean movestop)
    {
        if(dir == 0){
            movedir = movestate.stop;
        }
        else if(dir == 1 && movestop && state == State.falling){
            movedir = movestate.left;
            SetBitmapflip(true);
        }
        else if(dir == 1 && movestop && state != State.run){
            movedir = movestate.left;
            SetBitmapflip(true);
            setState(State.run);
        }
        else if(dir == 2 && movestop && state == State.falling){
            SetBitmapflip(false);
            movedir = movestate.right;
        }
        else if(dir == 2 && movestop && state != State.run){
            SetBitmapflip(false);
            movedir = movestate.right;
            setState(State.run);
        }
        else if(dir == 1 && !movestop && state == State.run){
            setState((State.idle));
        }
        else if(dir == 2 && !movestop && state == State.run){
            setState((State.idle));
        }
    }

    public void setState(State state) {
        this.state = state;
        if (state== State.jump)
        {
            ChangeBitmap(R.mipmap.frog_jump);
        }
        if (state== State.run)
        {
            ChangeBitmap(R.mipmap.frog_move);
        }
        if (state== State.idle)
        {
            ChangeBitmap(R.mipmap.frog_idle);
        }
        if (state== State.falling)
        {
            ChangeBitmap(R.mipmap.frog_fall);
        }

        srcRects = state.srcRects();
        collisionBox.set(dstRect);
        state.applyInsets(collisionBox);
    }

    public void jump() {
        if (state == State.idle) {
            setState(State.falling);
            jumpSpeed = -jumpPower;
        }
    }

    public void Stop(boolean startstop) {
        if (state != State.idle && startstop) {
            setState(State.idle);
        }

        if (state == State.idle && !startstop) {
            setState(State.falling);
        }
    }
}

