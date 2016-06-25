package se.BaseUlterior.GameObject.Aimed;

import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Vector2;

public class Grenade extends GameObjectAgile {

	public static final float GRENADE_SIZE = 27.0f;
	private boolean isReleased = false;

	private static float BOUNCYNESS = 0.9f;

	public Grenade(float[] nodes) {
		super(nodes, BOUNCYNESS);
		color = color.black;
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
