package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.Color;

import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Vector2;

public class Grenade extends GameObjectAgile {

	public static final float GRENADE_SIZE = 27.0f;
	private boolean isReleased = false;

	public Grenade(float[] nodes, float bouncyness, Color color) {
		super(nodes, bouncyness, color);
		startForceException = true;
	}

	public void initMotion(Vector2 motion) {
		this.motion = motion;
		isReleased = true;
		resetForceException();
	}

	@Override
	public void resetForceException() {
		if (isReleased) {
			forceException = false;
		} else {
			addForceException();
		}
	}

}
