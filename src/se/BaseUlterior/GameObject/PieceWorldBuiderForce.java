package se.BaseUlterior.GameObject;

import se.BaseUlterior.Physics.Impact;

public class PieceWorldBuiderForce extends PieceWorldBuilder {

	private int prio;

	public PieceWorldBuiderForce(int prio, float[] nodes) {
		super(nodes);
		this.prio = prio;
	}

	@Override
	public Impact getImpact(AgileObject piece) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

}
