package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IGameObject;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.util.CollisionHelper;

public class CollisionChecker implements IGameObject {
    private Player player;
    private static final String TAG = CollisionChecker.class.getSimpleName();
    public CollisionChecker(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<IGameObject> enemys = scene.getObjectsAt(MainScene.Layer.ENEMY);
        Log.d(TAG, "collsion:" + player.getCollisionRect().top);
        for (int i = enemys.size() - 1; i >= 0; i--) {
            IGameObject gobj = enemys.get(i);
            if (!(gobj instanceof Fly)) {
                continue;
            }
            Fly enemy = (Fly) gobj;
            if (CollisionHelper.collides(player, enemy)) {

                scene.remove(MainScene.Layer.ENEMY, gobj);
                //Sound.playEffect(item.soundId());
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {}
}