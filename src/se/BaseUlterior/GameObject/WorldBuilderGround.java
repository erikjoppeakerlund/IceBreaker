package se.BaseUlterior.GameObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Physics.Impact;
import se.BaseUlterior.Physics.ImpactBounce;
import se.BaseUlterior.Utils.UlteriorUtils;

public class WorldBuilderGround extends WorldBuilder {

	protected boolean underImpact;

	public WorldBuilderGround(float[] nodes) {
		super(nodes);
		this.color = Color.darkGray;
	}

	@Override
	protected void animateHit() {
		// TODO Auto-generated method stub

	}

	@Override
	public Impact getImpact(GameObject piece) {
		return new ImpactBounce(this, piece, piece.bouncyness);
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
			// ugly hack :/
			// if (!(im.getTrigger() instanceof Grenade)) {
			im.checkCalculate(delta);
			// }
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
