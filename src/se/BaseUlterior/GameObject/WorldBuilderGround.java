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
		this.color = Color.black;
	}

	public WorldBuilderGround(float[] nodes, float extraBouncyness) {
		super(nodes);
		this.color = Color.darkGray;
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

	@Override
	public Impact getImpact(GameObject piece) {
		return new ImpactBounce(this, piece, piece.bouncyness, false);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		super.render(container, graphics);
		graphics.setLineWidth(Constants.LINE_WIDTH);
		graphics.setColor(Constants.THEME_COLOR);
		graphics.draw(this);
	}

}
