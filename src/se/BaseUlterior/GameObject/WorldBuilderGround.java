package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

/**
 * Game object which getImpact method generates
 * se.BaseUlterior.Physics.ImpactBounce
 * 
 * @author Johan Akerlund
 */
public class WorldBuilderGround extends MateriaFirm {

	public WorldBuilderGround(float[] nodes) {
		super(nodes);
		this.color = Color.black;
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
		if (points.length > 0) {
			graphics.setLineWidth(Constants.LINE_WIDTH);
			graphics.setColor(Color.white);
			graphics.draw(this);
			graphics.resetLineWidth();
		}
	}

}
