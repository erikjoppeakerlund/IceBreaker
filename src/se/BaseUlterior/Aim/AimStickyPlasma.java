package se.BaseUlterior.Aim;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.Aimed.Plasma;
import se.BaseUlterior.Geom.Vector2;

public class AimStickyPlasma extends Aim {

	Plasma current;

	private List<Plasma> plasmas;

	private float START_SPEED = 0.3f;

	private boolean canMakeNew;

	long currentTime;

	private final long TIME_BETWEEN = 500;

	public AimStickyPlasma() {
		plasmas = new ArrayList<>();
		Plasma startPlasma = new Plasma();
		plasmas.add(startPlasma);
		BreakingPoint.objsToAdd.add(startPlasma);
		current = startPlasma;
	}

	@Override
	public void primaryPushed() {
		if (current == null) {
			return;
		}
		// current = null;
		float dX = (float) Math.cos(angle) * START_SPEED;
		float dY = (float) Math.sin(angle) * START_SPEED;
		current.initMotion(new Vector2(dX, dY));

		currentTime = System.currentTimeMillis();
		canMakeNew = true;
		current = null;

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
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		if (current != null) {
			current.setCenterX(x);
			current.setCenterY(y);
		}
		if (canMakeNew) {
			if (System.currentTimeMillis() - currentTime > TIME_BETWEEN) {
				Plasma newCurrent = new Plasma();
				plasmas.add(newCurrent);
				current = newCurrent;
				BreakingPoint.objsToAdd.add(newCurrent);
				canMakeNew = false;
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		// TODO Auto-generated method stub

	}

}
