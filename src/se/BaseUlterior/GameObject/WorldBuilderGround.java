package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

public class WorldBuilderGround extends WorldBuilder {

	// this thing with color int the constructor was just for testing!
	public WorldBuilderGround(float[] nodes, float bounceEffect, Color color) {
		super(nodes);
		this.color = color;
		this.bounceEffect = bounceEffect;
	}

	private float bounceEffect;

	@Override
	public Impact getImpact(GameObjectAgile piece) {
		return new ImpactBounce(this, bounceEffect, latestNormal);
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		super.update(container, arg);
		return;
		// for (GameObject go : BreakingPoint.all) {
		// if (go != this) {
		// if (go instanceof GameObjectAgile) {
		// if (UlteriorUtils.isWithinRange(go, this)) {
		// if (go.intersects(this)) {
		// (GameObjectAgile)((GameObjectAgile)
		// go).addImpact(this.getImpact(this));
		// }
		// }
		// }
		// }
		// }
	}

}
