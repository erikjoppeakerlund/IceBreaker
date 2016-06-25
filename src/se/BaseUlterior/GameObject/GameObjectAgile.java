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
//next thins: enable the user only to use the keyboard!
public abstract class GameObjectAgile extends GameObject {
	private boolean underImpact = false;
	protected Impact currentForce = null;
	protected boolean forceException = false;
	protected boolean startForceException = false;

	public boolean isForceException() {
		return forceException;
	}

	public void resetForceException() {
		forceException = startForceException;
	}

	protected float bouncyness;

	public Impact getCurrentForce() {
		return this.currentForce;
	}

	protected Color color;

	public GameObjectAgile(float[] nodes, float bouncyness) {
		super(nodes);
		this.bouncyness = bouncyness;
		currentForce = (ImpactForce) BreakingPoint.generalGravity.getImpact(this);
		resetForceException();
	}

	public GameObjectAgile(float[] nodes, float bouncyness, Color color, Vector2 startMovement) {
		super(nodes);
		motion = startMovement;
		currentForce = (ImpactForce) BreakingPoint.generalGravity.getImpact(this);
		this.bouncyness = bouncyness;
		resetForceException();
	}

	public float getBouncyness() {
		return this.bouncyness;
	}

	@Override
	public void update(GameContainer container, int arg) {

		checkImpact();
		if (underImpact) {
			runImpact();
		}
		setCenterX(getCenterX() + this.motion.getX());
		setCenterY(getCenterY() + this.motion.getY());
	}

	private void runImpact() {
		for (Impact im : currentImpacts) {
			im.calculateEffect(motion);
		}

		int[] removeIndexes = new int[currentImpacts.size()];
		int i = 0;

		for (Impact im : currentImpacts) {

			im.calculateEffect(motion);

			if (!im.getOrigin().containsGameObject(this)) {
				removeIndexes[i] = 1;
			} else {
				i++;
			}
		}
		for (int j = 0; j < removeIndexes.length; j++) {
			if (removeIndexes[j] == 1) {
				currentImpacts.get(j).getGameObjectAgile().resetForceException();
				;
				currentImpacts.remove(j);
			}
		}
		if (currentImpacts.isEmpty()) {
			underImpact = false;
		}

	}

	private void checkImpact() {
		for (GameObject go : BreakingPoint.all) {

			if (go == this) {
				continue;
			}

			if (UlteriorUtils.isWithinRange(go, this)) {
				if (go.intersectsGameobject(this) || go.containsGameObject(this)) {
					boolean contains = false;
					for (Impact im : currentImpacts) {
						if (im.getOrigin() == go) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						currentImpacts.add(go.getImpact(this));
						underImpact = true;
					}
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

	public void addForceException() {
		forceException = true;

	}

}
