package se.BaseUlterior.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Physics.Impact;

/**
 * Any type of consistent component in map; gravitation, materia or friction
 * affecting.
 * 
 * @author Johan Akerlund
 */
public abstract class WorldBuilder extends GameObject {

	protected WorldBuilder(float[] points, boolean isBackgroundObj, boolean invisible, boolean isRotatingObject,
			boolean forceUpdate, boolean forceRender, boolean isSolid) {
		super(points, isBackgroundObj, invisible, isRotatingObject, forceUpdate, forceRender, isSolid);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(this.color);
		graphics.fill(this);
	}

	@Override
	public void update(GameContainer container, int arg) throws SlickException {
	}

	@Override
	public abstract Impact getImpact(GameObject piece);
}
