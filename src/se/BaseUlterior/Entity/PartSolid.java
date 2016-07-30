package se.BaseUlterior.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;

import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

/**
 * Game object which getImpact method generates
 * se.BaseUlterior.Physics.ImpactBounce
 * 
 * @author Johan Akerlund
 */
public class PartSolid extends Materia {

	GradientFill fill;

	public PartSolid(float[] nodes) {
		super(nodes);
		this.color = new Color(0.6f, 0.6f, 0.6f, 0.8f);
		fill = new GradientFill(getCenterX(), getMinY(), Color.gray, getCenterX(), getMaxY(), Color.white, false);
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
		super.render(container, graphics);
	}

}
