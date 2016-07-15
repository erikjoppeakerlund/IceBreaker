package se.BaseUlterior.Aim;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.BaseUlterior.Config.Constants;

public class AimMachineGun extends AimTriggerHoldable {

	private int gunFireFrameWidth;
	private int gunFireFrameHeight;

	public AimMachineGun(Animation gunFire) {
		super("res/img/GUNSmachineGun.png", Constants.PERFERED_ARM_LENGTH * 0.7f);
		this.gunFire = gunFire;
		gunFireFrameWidth = this.gunFire.getCurrentFrame().getWidth();
		gunFireFrameHeight = this.gunFire.getCurrentFrame().getHeight();
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		gunFire.getCurrentFrame().setRotation((float) Math.toDegrees(angle + (Math.PI / 2f - 0.5f)));
		gunFire.draw(startShotX - gunFireFrameWidth / 2, startShotY - gunFireFrameHeight / 2);
	}

}
