package se.BaseUlterior.Aim;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.Processed.Grenade;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;

/**
 * se.BaseUlterior.GameObjectMateriaFirm producing class which produces a and
 * handling the act of throwing a grenade
 * 
 * @author Johan Akerlund
 */
public class AimGrenade extends Aim {

	List<Grenade> grenades = null;
	Grenade current;

	private long currentTime;

	private final long TIME_BETWEEN = 500;

	private boolean canMakeNew;

	private float force = 0.0f;
	private boolean charge = false;
	private final float CHARGE_SPEED = 1f;

	private final int CHARGE_ITERATION = 1;

	private final float THROW_FORCE = Constants.GRENADE_THROW_FORCE;

	private final float FORCE = 0.06f;
	private final float TIMES_LESS_INITIAL_ROTATION = 2.9f;

	public AimGrenade() {
		grenades = new ArrayList<>();
		Grenade startGrenade = new Grenade(
				new Circle(Constants.FAKE_START_COORDINATES, Constants.FAKE_START_COORDINATES, Grenade.GRENADE_SIZE)
						.getPoints());
		grenades.add(startGrenade);
		ParallaxPhysicsEngine.objsToAdd.add(startGrenade);
		current = startGrenade;
		slug = "GRENADE";
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
		float dX = arm.x * force * THROW_FORCE;
		float dY = arm.y * force * THROW_FORCE;
		current.initMotion(new Vector2(dX, dY));
		current.rotation = !isRight ? arm.x / TIMES_LESS_INITIAL_ROTATION : -arm.x / TIMES_LESS_INITIAL_ROTATION;
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
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		if (current != null) {
			current.setCenterX(spriteX + grip.x);
			current.setCenterY(spriteY + grip.y);
		}
		if (canMakeNew) {
			if (System.currentTimeMillis() - currentTime > TIME_BETWEEN) {
				grip.normalise().scale(armLengt);
				aimStart.normalise().scale(aimStartLengt);
				Grenade newCurrent = new Grenade(new Circle(Constants.FAKE_START_COORDINATES,
						Constants.FAKE_START_COORDINATES, Grenade.GRENADE_SIZE).getPoints());
				grenades.add(newCurrent);
				current = newCurrent;
				ParallaxPhysicsEngine.objsToAdd.add(newCurrent);
				canMakeNew = false;
			}
		}
		if (charge) {
			if (force < CHARGE_SPEED * CHARGE_ITERATION) {

				force += CHARGE_SPEED * FORCE;
				armLengt -= CHARGE_SPEED;

				armLengthQuote = armLengt / START_ARM_LENGTH;

				aimStart.normalise().scale(armLengthQuote * aimStartLengt);

				grip.normalise().scale(armLengthQuote * armLengt);
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);

	}

	@Override
	public void cleanUp() {
		if (current != null) {
			ParallaxPhysicsEngine.objsToRemove.add(current);
		}
	}

	@Override
	public void onThisWasChoosen() {
		super.onThisWasChoosen();
		if (current != null) {
			ParallaxPhysicsEngine.objsToRemove.add(current);
			ParallaxPhysicsEngine.objsToAdd.add(current);
		} else {

		}
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub

	}

}
