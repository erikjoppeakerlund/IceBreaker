package se.BaseUlterior.Aim;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.Aimed.Grenade;
import se.BaseUlterior.Geom.Vector2;

public class AimGrenade extends Aim {

	List<Grenade> grenades = null;
	Grenade current;

	long currentTime;

	private final long TIME_BETWEEN = 500;

	private boolean canMakeNew;

	private float force = 0.0f;
	private boolean charge = false;
	private final float CHARGE_SPEED = 1f;

	private final int CHARGE_ITERATION = 1;

	private final float TIMES_LESS_ACTUAL_FORCE = 2.0f;

	private final float FORCE = 0.04f;

	public AimGrenade() {
		grenades = new ArrayList<>();
		Grenade startGrenade = new Grenade(new Circle(x, y, Grenade.GRENADE_SIZE).getPoints());
		grenades.add(startGrenade);
		BreakingPoint.objsToAdd.add(startGrenade);
		current = startGrenade;
	}

	@Override
	public void primaryPushed() {
		charge = true;
	}

	@Override
	public void primaryReleased() {
		if (current == null) {
			return;
		}
		float dX = (float) Math.cos(angle) * force / TIMES_LESS_ACTUAL_FORCE;
		float dY = (float) Math.sin(angle) * force / TIMES_LESS_ACTUAL_FORCE;
		current.initMotion(new Vector2(dX, dY));
		currentTime = System.currentTimeMillis();
		canMakeNew = true;
		current = null;
		force = 0.0f;
		charge = false;
		armLengt = START_ARM_LENGTH;
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
		if (current != null) {
			current.setCenterX(x);
			current.setCenterY(y);
		}
		if (canMakeNew) {
			if (System.currentTimeMillis() - currentTime > TIME_BETWEEN) {
				Grenade newCurrent = new Grenade(new Circle(x, y, Grenade.GRENADE_SIZE).getPoints());
				grenades.add(newCurrent);
				current = newCurrent;
				BreakingPoint.objsToAdd.add(newCurrent);
				canMakeNew = false;
			}
		}
		if (charge) {
			if (force < CHARGE_SPEED * CHARGE_ITERATION) {
				force += CHARGE_SPEED * FORCE;
				armLengt -= CHARGE_SPEED;
			}
		}
	}

	@Override
	protected void renderFullfill(GameContainer container, Graphics graphics) throws SlickException {
		// TODO Auto-generated method stub

	}

}
