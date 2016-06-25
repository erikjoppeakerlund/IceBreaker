package se.BaseUlterior.GameObject.Aimed;

import org.newdawn.slick.Color;

import se.BaseUlterior.GameObject.GameObjectAgile;
import se.BaseUlterior.Geom.Vector2;

public class Grenade extends GameObjectAgile {

	public static final float GRENADE_SIZE = 27.0f;

	public Grenade(float[] nodes, float bouncyness, Color color) {
		super(nodes, bouncyness, color);
		forceException = true;
	}

	public void initMotion(Vector2 motion) {
		this.motion = motion;
		forceException = false;
	}

}
