package se.BaseUlterior.Aim;

import org.newdawn.slick.GameContainer;

public abstract class AimTriggerHoldable extends AimBulletWeapon {

	private int TIME_BETWEEN_FIRES = 10;
	protected boolean isTriggerDown = false;
	protected int iterator = 0;

	public AimTriggerHoldable(String pathToImage) {
		super(pathToImage);
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
