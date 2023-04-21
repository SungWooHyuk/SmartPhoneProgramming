package kr.ac.tukorea.swh02.termproject.JumpsTop.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import kr.ac.tukorea.swh02.termproject.framework.interfaces.IGameObject;
import kr.ac.tukorea.swh02.termproject.framework.scene.BaseScene;
import kr.ac.tukorea.swh02.termproject.framework.view.Metrics;

public class MapLoader implements IGameObject {
    private static final String TAG = MapLoader.class.getSimpleName();
    private Random random = new Random();
    private float platformX, itemX;
    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        platformX -= MapObject.SPEED * BaseScene.frameTime;
        while (platformX < Metrics.game_width) {
            Platform platform = Platform.get(Platform.Type.random(random), platformX, 7);
            scene.add(MainScene.Layer.platform, platform);
            platformX += platform.getWidth();
        }
//        itemX -= MapObject.SPEED * BaseScene.frameTime;
//        while (itemX < Metrics.game_width) {
//            int y = random.nextInt(6) + 1;
//            int count = 3;
//            if (y < 5) {
//                Platform platform = Platform.get(Platform.Type.GOUND, itemX, y+1);
//                scene.add(MainScene.Layer.platform, platform);
//            } else {
//                count = random.nextInt(5) + 1;
//            }
//            if (y <= 3) {
//                int index = random.nextInt(Obstacle.COUNT);
//                int oy = 6;
//                if (index == 3) oy--;
//                Obstacle obstacle = Obstacle.get(index, itemX, oy);
//                scene.add(MainScene.Layer.obstacle, obstacle);
//            }
//            for (int i = 0; i < count; i++) {
//                int y2 = y -= random.nextInt(2);
//                JellyItem jellyItem = JellyItem.get(JellyItem.getRandomIndex(random), itemX, y2);
//                scene.add(MainScene.Layer.item, jellyItem);
//                itemX += jellyItem.getWidth();
//            }
//        }
    }

    @Override
    public void draw(Canvas canvas) {}
}
