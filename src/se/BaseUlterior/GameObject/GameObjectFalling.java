package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;
import se.BaseUlterior.Utils.UlteriorUtils;

//next thins: enable the user only to use the keyboard!
public abstract class GameObjectFalling extends GameObject {
	private boolean underImpact = false;
	protected List<Impact> impactsToRemove = new ArrayList<>();

	protected float bouncyness;

	protected Color color;

	public GameObjectFalling(float[] nodes, float bouncyness) {
		super(nodes);
		this.bouncyness = bouncyness;
	}

	public GameObjectFalling(float[] nodes, float bouncyness, Color color, Vector2 startMovement) {
		super(nodes);
		motion = startMovement;
		this.bouncyness = bouncyness;
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
			im.checkCalculate();
			if (!(im.getTrigger().contains(this) || im.getTrigger().intersects(this))) {
				im.notTouchingButWithin();
			}
		}

		int[] removeIndexes = new int[currentImpacts.size()];
		int i = 0;

		for (Impact im : currentImpacts) {
			if (!UlteriorUtils.isWithinRange(im.getTrigger(), this)) {
				removeIndexes[i] = 1;
			} else {
				i++;
			}
		}
		for (int j = 0; j < removeIndexes.length; j++) {
			if (removeIndexes[j] == 1) {
				currentImpacts.get(j).onDestroy();
				currentImpacts.remove(j);
			}
		}
		if (currentImpacts.isEmpty()) {
			underImpact = false;
		}
		if (!impactsToRemove.isEmpty()) {
			currentImpacts.removeAll(impactsToRemove);
			impactsToRemove.clear();
		}
	}

	private void checkImpact() {
		for (GameObject go : BreakingPoint.all) {

			if (go == this) {
				continue;
			}

			if (UlteriorUtils.isWithinRange(go, this)) {
				if (go.intersects(this) || go.contains(this)) {
					boolean contains = false;
					for (Impact im : currentImpacts) {
						if (im.getTrigger() == go || im.getAffected().noForce) {
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
	public Impact getImpact(GameObjectFalling other) {
		return new ImpactBounce(this, other);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.setColor(this.color);
		graphics.fill(this);
	}

	public void addImpact(Impact im) {
		currentImpacts.add(im);
	}

	@Override
	public Shape[] subtract(Shape other) {
		return new Shape[0];
	}

	public void removeImpact(Impact im) {
		impactsToRemove.add(im);
	}

	public void removeCurrentImpactsWhichBelingTo(GameObject go) {
		for (Impact im : currentImpacts) {
			if (im.getTrigger() == go) {
				removeImpact(im);
			}
		}
	}

}
