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
		super(nodes, false);
		// this.color = new Color(0.2f, 0.2f, 0.2f, 0.93f);
		this.color = new Color(0.6f, 0.6f, 0.6f, 0.8f);
	}

	public PartSolid(float[] nodes, float extraBounciness) {
		super(nodes, false);
		this.color = Color.darkGray;
	}

	@Override
	public Impact getImpact(Entity piece) {
		return new ImpactBounce(this, piece, piece.bounciness, false);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(color);
		graphics.fill(this);
	}

}
