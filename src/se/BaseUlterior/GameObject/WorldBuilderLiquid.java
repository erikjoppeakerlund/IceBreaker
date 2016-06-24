package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;

import se.BaseUlterior.Physics.Density;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactFriction;

public class WorldBuilderLiquid extends WorldBuilder {

	Density density;

	protected float frictionAmount;

	public WorldBuilderLiquid(float[] nodes, Density density) {
		super(nodes);
		this.density = density;
		init();
	}

	protected void init() {
		switch (density) {
		case SOIL:
			this.color = Color.darkGray;
			this.frictionAmount = 0.89f;
			break;
		case MUDD:
			this.color = Color.green;
			this.frictionAmount = 0.91f;
			break;
		case GREECE:
			this.color = Color.magenta;
			this.frictionAmount = 0.96f;
			break;
		case WATER:
			this.color = Color.blue;
			this.frictionAmount = 0.98f;
		}
	}

	@Override
	public Impact getImpact(GameObject piece) {
		return new ImpactFriction(this, piece, frictionAmount);
	}

	@Override
	protected void animateHit() {

	}

}
