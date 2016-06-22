package se.BaseUlterior.GameObject;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

public class PieceWorldBuilderGround extends PieceWorldBuilder {

	public PieceWorldBuilderGround(float[] nodes) {
		super(nodes);
		// TODO Auto-generated constructor stub
	}

	private float bounceEffect = 2.0f;

	@Override
	public Impact getImpact(AgileObject piece) {

		return new ImpactBounce(this, bounceEffect);
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

}
