package se.BaseUlterior.Entity;

import org.newdawn.slick.GameContainer;

import se.BaseUlterior.ParallaX.ParallaxPhysicsEngine;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Utils.UlteriorUtils;

/**
 * Abstract game object class for any firm materia
 * 
 * @author Johan Akerlund
 */
public abstract class Materia extends Part {

	protected boolean underImpact;

	public Materia(float[] nodes) {
		super(nodes, false, false, false, false, false, true);
	}

	@Override
	public void update(GameContainer container, int delta) {
		checkImpact();
		if (underImpact) {
			if (!container.isPaused()) {
				runImpact(delta);
			}
		}
	}

	private void runImpact(int delta) {
		for (Impact im : currentImpacts) {

			im.checkCalculate(delta);

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
		for (Entity go : ParallaxPhysicsEngine.all) {

			if (go == this || (go.motionLess && this.motionLess) || go.isBackgroundObj) {
				continue;
			}

			if (UlteriorUtils.isWithinRange(go, this)) {
				if (go.intersects(this) || go.contains(this)) {
					boolean contains = false;
					for (Impact im : currentImpacts) {
						if (im.getTrigger() == go || im.getAffected() == go) {
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

}
