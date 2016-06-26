package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

public class WorldBuilderGround extends WorldBuilder {

	// this thing with color int the constructor was just for testing!
	public WorldBuilderGround(float[] nodes, Color color) {
		super(nodes);
		this.color = color;
	}

	@Override
	public Impact getImpact(GameObjectAgile piece) {
		return new ImpactBounce(this, piece);
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
	}

}