package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

public class WorldBuilderGround extends WorldBuilder {

	// this thing with color int the constructor was just for testing!
	public WorldBuilderGround(float[] nodes) {
		super(nodes);
		this.color = Color.white;
	}

	@Override
	public Impact getImpact(GameObjectFalling piece) {
		ImpactBounce ib = new ImpactBounce(this, piece);
		generlImacts.add(ib);
		return ib;
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		// TODO Auto-generated method stub
	}

}
