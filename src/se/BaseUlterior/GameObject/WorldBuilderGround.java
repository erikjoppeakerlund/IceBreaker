package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;

public class WorldBuilderGround extends WorldBuilder {

	public WorldBuilderGround(float[] nodes) {
		super(nodes);
		this.color = Color.white;
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

}
