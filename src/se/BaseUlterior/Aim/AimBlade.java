package se.BaseUlterior.Aim;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.Aimed.Blade;

public class AimBlade extends Aim {

	protected GameObject blade = null;

	public AimBlade() {
		blade = new Blade();

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
	public void update() {
		x = spriteX + (float) Math.cos(angle) * armLengt;
		y = spriteY + (float) Math.sin(angle) * armLengt;
		blade.setCenterX(x);
		blade.setCenterY(y);
		((Blade) blade).seteMe((Polygon) blade.transform(Transform.createRotateTransform(angle)));
	}

}
