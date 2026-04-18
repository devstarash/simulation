package config;

public abstract class GameConfig {
    public static final int IDLE_HUNGER_DAMAGE = 15;
    public static final int STEP_ENERGY_DRAIN = 20;
    public static final int PREDATOR_HEALTH = 50;
    public static final int HERBIVORE_HEALTH = 100;
    public static final int HERBIVORE_SPEED = 3;
    public static final int PREDATOR_SPEED = 4;
    public static final int PREDATOR_DAMAGE = 30;
    public static final double PREDATOR_PERCENTAGE = 0.050;
    public static final double HERBIVORE_PERCENTAGE = 0.150;
    public static final double GRASS_PERCENTAGE = 0.25;
    public static final double TREE_PERCENTAGE = 0.20;
    public static final double ROCK_PERCENTAGE = 0.10;
    public static final int DEFAULT_MAP_SIZES = 10;
    public static final int GRASS_GROWTH_INTERVAL = 5;
    public static final int DELAY_BETWEEN_MOVE = 5000;
    public static final int START_PAUSE = 5000;
    public static final String STOP_GAME_LINE = "stop";
    public static final String GAME_RESUME_LINE = "continue";
    public static final String GAME_LANDSCAPE_SPRITE = "\uD83D\uDFEB";
}

