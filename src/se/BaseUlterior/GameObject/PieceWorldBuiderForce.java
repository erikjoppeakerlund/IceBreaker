package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactForce;

public class PieceWorldBuiderForce extends PieceWorldBuilder {

	protected float gravityY;
	protected float gravityX;

	public PieceWorldBuiderForce(float[] nodes, float gravityX, float gravityY) {
		super(nodes);
		color = Color.darkGray;
		this.gravityX = gravityX;
		this.gravityY = gravityY;
	}

	@Override
	public Impact getImpact(AgileObject piece) {
		return new ImpactForce(this, gravityY, gravityX);
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		// TODO Auto-generated method stub
		super.update(container, arg);
	}

}
