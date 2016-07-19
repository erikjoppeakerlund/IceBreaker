package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;

/**
 * Like superclass but implemented user input handling according to, shoot once
 * trigger is pulled principle
 * 
 * @author Johan Akerlund
 */
public abstract class AimTriggerHoldable extends AimBulletWeapon {

	private int TIME_BETWEEN_FIRES = 10;
	protected boolean isTriggerDown = false;
	protected int iterator = 0;

	public AimTriggerHoldable(String pathToImage) {
		super(pathToImage, IMAGE_SCALE_STANDARD);
	}

	public AimTriggerHoldable(String pathToImage, float startArmLength) {
		super(pathToImage, startArmLength, IMAGE_SCALE_STANDARD);
	}

	@Override
	public void primaryPushed() {
		iterator = 0;
		isTriggerDown = true;
	}

	@Override
	public void primaryReleased() {
		isTriggerDown = false;
	}

	@Override
	public void update(GameContainer container, int delta) {
		super.update(container, delta);

		if (isTriggerDown) {
			if (iterator == 0 || iterator > TIME_BETWEEN_FIRES) {

				this.wasShoot();
				iterator = 1;
			}
			iterator++;
		}

	}

}
