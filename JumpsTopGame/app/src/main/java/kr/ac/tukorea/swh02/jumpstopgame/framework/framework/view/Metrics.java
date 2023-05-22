package kr.ac.tukorea.swh02.jumpstopgame.framework.framework.view;

public class Metrics {
    public static float scale = 1.0f;
    public static float game_width = 18.0f;
    public static float game_height = 32.0f;
    public static int x_offset = 0, y_offset = 0;

    public static void setGameSize(float width, float height) {
        game_width = width;
        game_height = height;
    }

    public static float toGameX(float x) {
        return (x - x_offset) / scale;
    }
    public static float toGameY(float y) {
        return (y - y_offset) / scale;
    }

    public static float getGameWidth() {
        return game_width;
    }

    public static float getGameHeight() {
        return game_height;
    }
}
