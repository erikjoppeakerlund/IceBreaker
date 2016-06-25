package se.BaseUlterior.Aim;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.Aimed.Grenade;

public class AimGrenade extends Aim {

	Grenade grenade = null;

	public AimGrenade() {
		grenade = new Grenade(new Circle(x, y, Grenade.GRENADE_SIZE).getPoints(), 1.0f, Color.green);
		BreakingPoint.all.add(grenade);
	}

	@Override
	public void update() {
		grenade.setCenterX(x);
		grenade.setCenterY(y);
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

}
