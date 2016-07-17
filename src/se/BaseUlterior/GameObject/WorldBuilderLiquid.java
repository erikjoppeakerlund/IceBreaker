package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Physics.Density;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactFriction;

public class WorldBuilderLiquid extends WorldBuilder {

	Density density;

	protected float frictionAmount;

	public WorldBuilderLiquid(float[] nodes) {
		super(nodes);
		this.density = Density.WATER;
	}

	public WorldBuilderLiquid(float[] nodes, Density density) {
		super(nodes);
		this.density = density;
		init();
	}

	public WorldBuilderLiquid(float[] nodes, Density density, boolean isBackground) {
		super(nodes);
		isBackgroundObj = isBackground;
		this.density = density;
		init();
	}

	protected void init() {
		switch (density) {
		case SOIL:
			this.color = Color.darkGray;
			this.frictionAmount = 0.81f;
			break;
		case MUDD:
			this.color = Color.green;
			this.frictionAmount = 0.83f;
			break;
		case GREECE:
			this.color = Color.magenta;
			this.frictionAmount = 0.88f;
			break;
		case WATER:
			this.color = Color.blue;
			this.frictionAmount = 0.90f;
			break;
		case LAVA:
			this.color = new Color(1, 0, 0, 0.4f);
			this.frictionAmount = 0.86f;
			break;
		case AIR:
			this.color = Color.transparent;
			this.frictionAmount = 0.981f;
			break;
		}
	}

	@Override
	public Impact getImpact(GameObject piece) {
		return new ImpactFriction(this, piece, frictionAmount);
	}

	@Override
	protected void animateHit() {

	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

	public void setDensity(Density density) {
		this.density = density;
		init();
	}

}
