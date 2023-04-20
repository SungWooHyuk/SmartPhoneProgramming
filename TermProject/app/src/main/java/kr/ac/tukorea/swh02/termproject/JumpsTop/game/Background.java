package kr.ac.tukorea.swh02.termproject.JumpsTop.game;

import kr.ac.tukorea.swh02.termproject.framework.objects.Sprite;
import kr.ac.tukorea.swh02.termproject.framework.view.Metrics;

public class Background extends Sprite {
    public Background(int bitmapResId) {
        super(bitmapResId, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        float height = bitmap.getHeight() * Metrics.game_width / bitmap.getWidth();
        setSize(Metrics.game_width, height);
    }

}
