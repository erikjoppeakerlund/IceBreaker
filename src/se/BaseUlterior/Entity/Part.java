package se.BaseUlterior.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Physics.Impact;

/**
 * Any type of consistent component in map; gravitation, materia or friction
 * affecting.
 * 
 * @author Johan Akerlund
 */
public abstract class Part extends Entity {

	protected Part(float[] points, boolean isBackgroundObj, boolean invisible, boolean isRotatingObject,
			boolean forceUpdate, boolean forceRender, boolean isSolid) {
		super(points, isBackgroundObj, invisible, isRotatingObject, forceUpdate, forceRender, isSolid);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fill(this);
	}

	@Override
	public void update(GameContainer container, int arg) {
	}

	@Override
	public abstract Impact getImpact(Entity piece);
}
