package se.BaseUlterior.GameObject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounceGiven;
import se.BaseUlterior.Utils.UlteriorUtils;

//next thins: enable the user only to use the keyboard!
public abstract class GameObjectFalling extends GameObject {
	protected boolean underImpact = false;

	public GameObjectFalling(float[] nodes, float bouncyness) {
		super(nodes);
		this.bouncyness = bouncyness;
	}

	@Override
	public void update(GameContainer container, int delta) {
		checkImpact();
		if (underImpact) {
			runImpact(delta);
		}
		setCenterX(getCenterX() + this.motion.getX() * delta);
		setCenterY(getCenterY() + this.motion.getY() * delta);
	}

	private void runImpact(int delta) {
		for (Impact im : currentImpacts) {
			im.checkCalculate(delta);
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

	protected void checkImpact() {
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
						Impact im = go.getImpact(this);
						if (im != null) {
							currentImpacts.add(im);
						}
						underImpact = true;
					}
				}
			}

		}
	}

	@Override
	public Impact getImpact(GameObject other) {
		return new ImpactBounceGiven(this, other, bouncyness);
	}

	public float getBouncyness() {
		return this.bouncyness;
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

	public void removeCurrentImpactsWhichBelingTo(GameObject go) {
		for (Impact im : currentImpacts) {
			if (im.getTrigger() == go) {
				removeImpact(im);
			}
		}
	}

}
