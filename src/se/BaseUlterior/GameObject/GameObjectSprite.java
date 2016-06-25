package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.Aim.AimGrenade;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public class GameObjectSprite extends GameObjectAgile {

	protected Aim aim = null;
	protected List<Aim> aims = null;
	protected float speed = 0.5f;
	private final float JUMP_EFFECT = 7.2f;
	protected final float MAX_SPEED = 8.0f;
	protected boolean mouseButtonPirmaryDown = false;

	public GameObjectSprite(float[] nodes, float bouncyness, Color color) {
		super(nodes, bouncyness, color);
		initAims();
	}

	private void initAims() {
		aims = new ArrayList<Aim>();
		aims.add(new AimGrenade());
		aim = aims.get(0);
	}

	@Override
	public void update(GameContainer container, int arg) {
		Input in = container.getInput();

		float mouseX = in.getMouseX();
		float mouseY = in.getMouseY();

		if (Mouse.getDWheel() > 0) {
			int i = aims.indexOf(aim);
			aim = i == aims.size() - 1 ? aims.get(0) : aims.get(i + 1);
		}
		if (in.isKeyDown(in.KEY_A)) {
			if (motion.getX() > -MAX_SPEED) {
				motion.add(-speed, 0.0f);
			}
		} else if (in.isKeyDown(in.KEY_D)) {
			if (motion.getX() < MAX_SPEED) {
				motion.add(speed, 0.0f);
			}
		}
		if (in.isKeyDown(in.KEY_W)) {
			motion.set(new Vector2(motion.getX(), -4.0f));
		}
		if (in.isMousePressed(in.MOUSE_LEFT_BUTTON)) {
			aim.primaryPushed();
			if (!mouseButtonPirmaryDown) {
				mouseButtonPirmaryDown = true;
			}
		} else if (!in.isMouseButtonDown(in.MOUSE_LEFT_BUTTON) && mouseButtonPirmaryDown) {
			mouseButtonPirmaryDown = false;
			aim.primaryReleased();
		}

		float angleToPoint = UlteriorUtils.angleToPoint(getCenterX(), getCenterY(), mouseX, mouseY);

		aim.setPosition(getCenterX(), getCenterY());

		aim.setAngleToMouse(angleToPoint);

		aim.update();

		super.update(container, arg);
	}

}
