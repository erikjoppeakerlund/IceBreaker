package se.BaseUlterior.Aim;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class AimRifle extends AimBulletWeapon {

	public AimRifle() {
		super("res/img/GUNSrifle.png");
	}

	@Override
	public void render(GameContainer container, Graphics graphics) {
		super.render(container, graphics);
		graphics.setColor(Color.red);
		graphics.drawLine(startShotX, startShotY, aimAtX, aimAtY);

	}

	@Override
	public void update(GameContainer container, int arg) {
		super.update(container, arg);
		updateAim();
	}

	@Override
	public void primaryPushed() {
		wasShoot();
	}

}
