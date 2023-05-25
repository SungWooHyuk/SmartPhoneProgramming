package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.swh02.jumpstopgame.R;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IRecyclable;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.AnimSprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.SheetSprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.objects.Sprite;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.BitmapPool;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.RecycleBin;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view.Metrics;

public class Player extends SheetSprite {
    private static final float FRAMES_PER_SECOND = 8.f;
    private static final String TAG = Player.class.getSimpleName();
    private final float jumpPower;
    private final float gravity;
    private float save_pos_x;
    private  float save_pos_y;

    static {
        State.initRects();
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
            dstRect.left += w * inset[0];
            dstRect.top += h * inset[1];
            dstRect.right -= w * inset[2];
            dstRect.bottom -= h * inset[3];
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

    protected RectF collisionBox = new RectF();

    private static final int FRAME_COUNT = 11;
    private static final int IMAGE_SIZE = 32;

    private TYPE type;

    public Player(float x, float y, float w, float h, PlayerType type ) {

        super(type.playerBitmap(type), FRAMES_PER_SECOND * 2);
        this.x = x;
        this.y = y;
        m_w =w/2;
        m_h = h/2;
        save_pos_x = x;
        save_pos_y = y;
        jumpPower = 9.f;
        moveSpeed = 9.f;
        gravity = 9.f;
        setDstRect(w/2, h/2);
        setState(State.run);
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
        jumpSpeed = 0;
    }

    public enum TYPE {
        Blue, Red, COUNT
    }

    private Rect[][] rects_array;
    private void init(TYPE type, float speed, float size) {
        this.type = type;
        this.width = this.height = size;
        srcRects = rects_array[type.ordinal()];
    }
    public void update(float frameTime) {
        float foot = collisionBox.bottom;
        setState(State.run);
    }
    public void setmovedir(int dir)
    {

        if(dir == 0){
            movedir = movestate.stop;
        }
        else if(dir == 1){
            movedir = movestate.left;
            SetBitmapflip(true);
            // setState(State.run);
        }
        else if(dir == 2){
            SetBitmapflip(false);
            //   setState(State.run);

            movedir = movestate.right;
        }


    }

    public void setState(State state) {
        this.state = state;
        if (state== State.jump)
        {
            if(p_type == PlayerType.Red)
                ChangeBitmap(R.mipmap.frog_jump);
        }
        if (state== State.run)
        {
            if(p_type == PlayerType.Red)
                ChangeBitmap(R.mipmap.frog_move);
        }
        if (state== State.idle)
        {
            if(p_type == PlayerType.Red)
                ChangeBitmap(R.mipmap.frog_idle);
        }
        if (state== State.falling)
        {
            if(p_type == PlayerType.Red)
                ChangeBitmap(R.mipmap.frog_fall);
        }

        srcRects = state.srcRects();
        collisionBox.set(dstRect);
        state.applyInsets(collisionBox);
    }
}

