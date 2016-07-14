package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Utils.UlteriorUtils;

public class AimBlade extends Aim {

	protected Shape blade;

	private float SIZE_OF_CUT = 110f;

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
		UlteriorUtils.removeGround(x, y, SIZE_OF_CUT, null);
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
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		blade = blade.transform(Transform.createRotateTransform(dAngle));
		blade.setCenterX(x);
		blade.setCenterY(y);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		graphics.setColor(Constants.THEME_COLOR);
		graphics.fill(blade);
	}

}
