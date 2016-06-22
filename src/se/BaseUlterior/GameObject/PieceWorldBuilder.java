package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class PieceWorldBuilder extends GameObject {

	// not really thought triugh
	protected boolean underHit;

	// dummy, just for testing...
	Color color = null;

	public PieceWorldBuilder(float[] nodes) {
		super(nodes);
	}

	protected abstract void animateHit();

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
		if (underHit) {
			this.animateHit();
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(this.color);
		graphics.fill(this);
	}

}
