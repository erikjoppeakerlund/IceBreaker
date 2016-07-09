package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
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
		// this.rundDuringConatain = true;
	}

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
	public Impact getImpact(GameObjectFalling piece) {
		return new ImpactFriction(this, piece, frictionAmount);
	}

	@Override
	protected void animateHit() {

	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

	public void setDensity(Density d) {
		this.density = d;
		init();
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		// TODO Auto-generated method stub

	}

}
