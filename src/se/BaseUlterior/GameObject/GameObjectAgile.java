package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactForce;
import se.BaseUlterior.Utils.UlteriorUtils;

//will most likely be turned into an abstract class...
public class GameObjectAgile extends GameObject {
	private List<Impact> currentImpacts = null;
	private boolean underImpact = false;
	private Vector2 movement = null;

	private Impact currentForce = null;
	private boolean isOriginalForce = true;

	private Color color = Color.lightGray;

	public GameObjectAgile(float[] nodes) {
		super(nodes);
		movement = new Vector2();
		currentImpacts = new ArrayList<>();
		currentForce = BreakingPoint.generalGravity.getImpact(this);
	}

	public GameObjectAgile(Vector2 startMovement, float[] nodes) {
		super(nodes);
		movement = startMovement;
		currentImpacts = new ArrayList<>();
		currentForce = BreakingPoint.generalGravity.getImpact(this);
	}

	public void getAffected() {
		underImpact = true;
	}

	@Override
	public void update(GameContainer container, int arg) {

		if (!isOriginalForce) {
			if (!(currentForce.getOrigin().intersects(this) || currentForce.getOrigin().contains(this))) {
				currentForce = BreakingPoint.generalGravity.getImpact(this);
				isOriginalForce = true;
			}
		}
		// if (!(currentForce.getOrigin().contains(this) ||
		// currentForce.getOrigin().intersects(this))
		// && !currentImpacts.contains(currentForce)) {
		// currentForce = Game.generalGravity.getImpact(this);
		// }
		// if (currentForce != null) {
		currentForce.calculateEffect(this.movement);
		// }

		checkImpact();
		if (underImpact) {
			runImpact();
		}
		setCenterX(getCenterX() + this.movement.getX());
		setCenterY(getCenterY() + this.movement.getY());
	}

	private void runImpact() {

		int[] removeIndexes = new int[currentImpacts.size()];
		int i = 0;

		for (Impact im : currentImpacts) {
			im.calculateEffect(movement);
			if (!im.getOrigin().contains(this)) {
				removeIndexes[i] = 1;
			} else {
				i++;
			}
		}
		for (int j = 0; j < removeIndexes.length; j++) {
			if (removeIndexes[j] == 1) {
				currentImpacts.remove(j);
			}
		}
		if (currentImpacts.isEmpty()) {
			underImpact = false;
		}

	}

	private void checkImpact() {
		for (GameObject p : BreakingPoint.all) {
			if (p == this || p == currentForce.getOrigin()) {
				continue;
			}

			if (UlteriorUtils.isWithinRange(p, this)) {
				if (p.intersects(this)) {
					Impact i = p.getImpact(this);

					if (i instanceof ImpactForce) {
						currentForce = i;
						isOriginalForce = false;
					} else {
						this.currentImpacts.add(p.getImpact(this));
					}

					underImpact = true;
				}
			}
		}
	}

	public Vector2 getMovement() {
		return movement;
	}

	public void setMovement(Vector2 movement) {
		this.movement = movement;
	}

	@Override
	public Impact getImpact(GameObjectAgile piece) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(this.color);
		graphics.fill(this);
		// this.checkPoints();
	}

	public void addImpact(Impact im) {
		currentImpacts.add(im);
	}

}
