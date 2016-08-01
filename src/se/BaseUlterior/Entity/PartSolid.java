package se.BaseUlterior.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

/**
 * Game object which getImpact method generates
 * se.BaseUlterior.Physics.ImpactBounce
 * 
 * @author Johan Akerlund
 */
public class PartSolid extends Materia {

	public PartSolid(float[] nodes) {
		super(nodes);
		this.color = new Color(0.2f, 0.2f, 0.2f, 0.93f);
	}

	public PartSolid(float[] nodes, float extraBounciness) {
		super(nodes);
		this.color = Color.darkGray;
	}

	@Override
	public Impact getImpact(Entity piece) {
		return new ImpactBounce(this, piece, piece.bounciness, false);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(Color.darkGray);
		graphics.fill(this);
	}

}
