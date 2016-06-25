package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;
import se.BaseUlterior.Physics.ImpactForce;
import se.BaseUlterior.Utils.UlteriorUtils;

//will most likely be turned into an abstract class...
public class GameObjectAgile extends GameObject {
	// private List<Impact> currentImpacts = null;
	private boolean underImpact = false;
	// protected Vector2 motion = null;

	protected Impact currentForce = null;
	private boolean isOriginalForce = true;
	protected boolean forceException = false;

	public float bouncyness;

	public Impact getCurrentForce() {
		return this.currentForce;
	}

	private Color color = Color.lightGray;

	public GameObjectAgile(float[] nodes, float bouncyness) {
		super(nodes);
		this.bouncyness = bouncyness;
		// motion = new Vector2();
		// currentImpacts = new ArrayList<>();
		currentForce = (ImpactForce) BreakingPoint.generalGravity.getImpact(this);
	}

	public GameObjectAgile(Vector2 startMovement, float[] nodes) {
		super(nodes);
		motion = startMovement;
		currentForce = (ImpactForce) BreakingPoint.generalGravity.getImpact(this);
	}

	public float getBouncyness() {
		return this.bouncyness;
	}

	@Override
	public void update(GameContainer container, int arg) {

		if (!isOriginalForce) {
			if (!(currentForce.getOrigin().intersects(this) || currentForce.getOrigin().contains(this))) {
				currentForce = (ImpactForce) BreakingPoint.generalGravity.getImpact(this);
				isOriginalForce = true;
			}
		}
		currentForce.calculateEffect(motion);

		checkImpact();
		if (underImpact) {
			runImpact();
		}
		setCenterX(getCenterX() + this.motion.getX());
		setCenterY(getCenterY() + this.motion.getY());
	}

	private void runImpact() {

		int[] removeIndexes = new int[currentImpacts.size()];
		int i = 0;

		for (Impact im : currentImpacts) {

			im.calculateEffect(motion);

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
				if (p.intersectsGameobject(this)) {
					Impact i = p.getImpact(this);

					if (i instanceof ImpactForce) {
						currentForce = (ImpactForce) i;
						isOriginalForce = false;
					} else {
						this.currentImpacts.add(p.getImpact(this));
					}

					underImpact = true;
				}
			}
		}
	}

	public Vector2 getMotion() {
		return motion;
	}

	@Override
	public Impact getImpact(GameObjectAgile other) {
		return new ImpactBounce(this, other);
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
