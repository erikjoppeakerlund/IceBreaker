package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

public class PieceWorldBuilderGround extends PieceWorldBuilder {

	public PieceWorldBuilderGround(float[] nodes, float bounceEffect) {
		super(nodes);
		this.color = Color.black;
		this.bounceEffect = bounceEffect;
	}

	private float bounceEffect;

	@Override
	public Impact getImpact(AgileObject piece) {
		return new ImpactBounce(this, bounceEffect, latestNormal);
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

}
