package se.BaseUlterior.Aim;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class AimBlade extends Aim {

	protected Shape blade;

	public AimBlade() {
		blade = new Polygon(new float[] { 1062, 383, 1129, 315, 1037, 365, 1052, 369, 1022, 387, 1036, 415, 1042, 389,
				1055, 390, });
		blade.setCenterX(-60);
		blade.setCenterY(-60);
	}

	@Override
	public void primaryPushed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void primaryReleased() {
		// TODO Auto-generated method stub

	}

	@Override
	public void secondaryPushed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void secondaryReleased() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateFulfill(GameContainer container, int arg) {
		blade = blade.transform(Transform.createRotateTransform(dAngle));
		blade.setCenterX(x);
		blade.setCenterY(y);
	}

	@Override
	protected void renderFullfill(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(Color.cyan);
		graphics.fill(blade);
	}

}
