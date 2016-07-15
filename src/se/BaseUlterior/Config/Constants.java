package se.BaseUlterior.Config;

import org.newdawn.slick.Color;

public class Constants {
	public static final float GENERAL_GRAVITY = 0.009f;
	public static final int CANVAS_WIDTH = 1920;
	public static final int CANVAS_HEIGHT = 1080;

	public static final int CANVAS_EXTENTION_FACTOR = 5;

	public static final int CANVAS_WIDTH_FULL = CANVAS_WIDTH * CANVAS_EXTENTION_FACTOR;
	public static final int CANVAS_HEIGHT_FULL = CANVAS_HEIGHT * CANVAS_EXTENTION_FACTOR;

	public static final int LINE_WIDTH = 3;
	public static final Color THEME_COLOR = new Color(0.2f, 0.3f, 0.2f);

	public static final float SPRITE_RADIUS = 44.0f;
	public static final float PERFERED_ARM_LENGTH = Constants.SPRITE_RADIUS * 3.2f;

	public static String INSERT_MODE = "INSERT_MODE";
	public static String ACTION_MODE = "ACTION_MODE";
}
