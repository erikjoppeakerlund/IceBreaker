package se.BaseUlterior.ParallaX;

import se.BaseUlterior.Entity.Entity;

public abstract class ParallaxDefault extends Entity {

	protected ParallaxDefault(float[] points, boolean isBackgroundObj, boolean invisible, boolean isRotatingObject,
			boolean forceUpdate, boolean forceRender, boolean isSolid) {
		super(points, isBackgroundObj, invisible, isRotatingObject, forceUpdate, forceRender, isSolid);
		// TODO Auto-generated constructor stub
	}

	public abstract void moveParallax(float x, float y);

}
