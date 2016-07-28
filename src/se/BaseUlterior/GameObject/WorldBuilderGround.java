package se.BaseUlterior.GameObject;

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
public class WorldBuilderGround extends MateriaFirm {

	GradientFill fill;

	public WorldBuilderGround(float[] nodes) {
		super(nodes);
		this.color = new Color(0.6f, 0.6f, 0.6f, 0.8f);
		fill = new GradientFill(getCenterX(), getMinY(), Color.gray, getCenterX(), getMaxY(), Color.white, false);
	}

	public WorldBuilderGround(float[] nodes, float extraBounciness) {
		super(nodes);
		this.color = Color.darkGray;
	}

	@Override
	public Impact getImpact(GameObject piece) {
		return new ImpactBounce(this, piece, piece.bounciness, false);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		// graphics.fill(this, fill);
		// if (points.length > 0) {
		// graphics.setLineWidth(Constants.LINE_WIDTH);
		// graphics.setColor(Color.white);
		// graphics.draw(this);
		// graphics.resetLineWidth();
		// }
	}

}
