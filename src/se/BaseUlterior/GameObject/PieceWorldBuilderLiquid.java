package se.BaseUlterior.GameObject;

import se.BaseUlterior.Physics.Impact;

public class PieceWorldBuilderLiquid extends PieceWorldBuilder {

	public PieceWorldBuilderLiquid(float[] nodes) {
		super(nodes);
		// TODO Auto-generated constructor stub
	}

	private int prio;

	@Override
	public Impact getImpact(AgileObject piece) {
		return null;
	}

	@Override
	protected void animateHit() {

	}

}
