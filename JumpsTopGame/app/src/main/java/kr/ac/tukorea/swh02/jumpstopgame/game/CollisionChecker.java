package kr.ac.tukorea.swh02.jumpstopgame.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.interfaces.IGameObject;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.res.Sound;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.jumpstopgame.framework.framework.util.CollisionHelper;

public class CollisionChecker implements IGameObject {
    private Player player;

    public CollisionChecker(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
//        ArrayList<IGameObject> items = scene.getObjectsAt(MainScene.Layer.item);
//        for (int i = items.size() - 1; i >= 0; i--) {
//            IGameObject gobj = items.get(i);
//            if (!(gobj instanceof BoxCollidable)) {
//                continue;
//            }
//            JellyItem item = (JellyItem) gobj;
//            if (CollisionHelper.collides(player, item)) {
//                scene.remove(MainScene.Layer.item, gobj);
//                Sound.playEffect(item.soundId());
//                if (item.index == 26) {
//                    player.magnify(true);
//                }
//            }
//        }
//        ArrayList<IGameObject> obstacles = scene.getObjectsAt(MainScene.Layer.obstacle);
//        for (int i = obstacles.size() - 1; i >= 0; i--) {
//            Obstacle obstacle = (Obstacle) obstacles.get(i);
//            if (CollisionHelper.collides(player, obstacle)) {
//                player.hurt(obstacle);
//            }
//        }
    }

    @Override
    public void draw(Canvas canvas) {}
}