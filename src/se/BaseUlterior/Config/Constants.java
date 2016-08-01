package se.BaseUlterior.Config;

import org.newdawn.slick.Color;

import se.BaseUlterior.Actions.Action;

public class Constants {
	public static final float GENERAL_GRAVITY = 0.021f;
	public static final int CANVAS_WIDTH = 1920;
	public static final int CANVAS_HEIGHT = 1080;

	public static final int CANVAS_EXTENTION_FACTOR = 5;

	public static final int CANVAS_WIDTH_FULL = CANVAS_WIDTH * CANVAS_EXTENTION_FACTOR;
	public static final int CANVAS_HEIGHT_FULL = CANVAS_HEIGHT * CANVAS_EXTENTION_FACTOR;

	public static final int LINE_WIDTH = 1;
	public static final Color THEME_COLOR = new Color(0.2f, 0.3f, 0.2f);

	public static final float SPRITE_RADIUS = 44.0f;
	public static final float PERFERED_ARM_LENGTH = Constants.SPRITE_RADIUS * 3.2f;

	public static final float VECTOR_SHOT_RADIUS = 39f;

	public static final Action START_MODE = Action.ACTION_MODE_DESKTOP;

	public static final float GENERA_FRICTION = 0.999f;
	public static final float GROUND_FRICTION = 0.8f;

	public static final float SPLINTER_FRICTION = 0.198f;

	public static final float GENERA_OBJECT_MAX_SPEED = 1f;

	public static final float VECTOR_SHOOOT_SPEED = 0.5f;
	public static final float TURRETS_DISTANCE = 3499;

	public static final int STATS_BOX_HEIGHT = 199;
	public static final int STATS_BOX_WIDTH = 346;

	public static final float MAX_FOLLOW_SPEED_TURRET = 0.39f;

	public final static int TURRETS_MAX_RELOAD_SPEED = 127;

	public final static float GENERAL_EXPLOSION_HURT_EFFECT = 40;
	public static final float GRENADE_THROW_FORCE = 1.52f;

	public static final float GRENADE_BOUNCINESS = 0.5f;

	public static final int GRENADE_SIZE_OF_EXPLOSION = 419;

	public static final float FAKE_START_COORDINATES = -450f;

	public static final int MAIN_CHARACTER_START_LIFE = 1000;

	public static final int HIT_LENGTH_FRAMES = 7;

	/*
	 * Weapon effect (out of 100)
	 */

	public static final float WEAPON_EFFECT_RIFLE = 39f;
	public static final float WEAPON_EFFECT_SHOTGUN = 9f;
	public static final float WEAPON_EFFECT_UZI = 5f;
	public static final float WEAPON_EFFECT_GRENADE = 71f;

}
