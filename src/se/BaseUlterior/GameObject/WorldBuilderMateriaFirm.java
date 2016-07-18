package se.BaseUlterior.GameObject;

import org.newdawn.slick.GameContainer;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Utils.UlteriorUtils;

public abstract class WorldBuilderMateriaFirm extends WorldBuilder {

	protected boolean underImpact;
	// protected float bouncyness;

	public WorldBuilderMateriaFirm(float[] nodes) {
		super(nodes);
	}

	@Override
	public void update(GameContainer container, int delta) {
		checkImpact();
		if (underImpact) {
			runImpact(delta);
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

	private void checkImpact() {
		for (GameObject go : BreakingPoint.all) {

			if (go == this) {
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

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

}
