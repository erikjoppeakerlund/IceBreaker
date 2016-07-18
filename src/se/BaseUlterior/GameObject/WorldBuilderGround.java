package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;

public class WorldBuilderGround extends WorldBuilderMateriaFirm {

	public WorldBuilderGround(float[] nodes) {
		super(nodes);
		this.color = Color.white;
	}

	public WorldBuilderGround(float[] nodes, float extraBouncyness) {
		super(nodes);
		this.color = Color.darkGray;
	}

	@Override
	public Impact getImpact(GameObject piece) {
		return new ImpactBounce(this, piece, piece.bouncyness, false);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		super.render(container, graphics);
		if (points.length > 0) {
			graphics.setLineWidth(Constants.LINE_WIDTH);
			graphics.setColor(Color.black);
			graphics.draw(this);
			graphics.resetLineWidth();
		}
	}

}
