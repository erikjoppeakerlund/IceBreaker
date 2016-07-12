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
		blade = new Polygon(new float[] { 40, 10, 0, 0, 0, 20 });
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
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(Color.white);
		graphics.fill(blade);

	}

	@Override
	protected void updateFulfill(GameContainer container, int arg) {
		blade = blade.transform(Transform.createRotateTransform(dAngle));
		blade.setCenterX(x);
		blade.setCenterY(y);
	}

}
