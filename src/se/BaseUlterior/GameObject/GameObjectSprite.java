package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.Aim.AimGrenade;
import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public class GameObjectSprite extends GameObjectFalling {

	protected Aim aim = null;
	protected List<Aim> aims = null;
	protected float speed = 0.004f;
	protected final float MAX_SPEED = 0.4f;
	protected boolean mouseButtonPirmaryDown = false;

	protected boolean right = true;

	protected float JUMP_POWER = -0.3f;

	public GameObjectSprite(float[] nodes, float bouncyness) {
		super(nodes, bouncyness);
		color = Color.gray;
		initAims();
	}

	private void initAims() {
		aims = new ArrayList<Aim>();
		aims.add(new AimGrenade());
		aim = aims.get(0);
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (BreakingPoint.insertMode) {
			return;
		}
		Input in = container.getInput();

		float mouseX = in.getMouseX();
		float mouseY = in.getMouseY();

		if (Mouse.getDWheel() > 0) {
			int i = aims.indexOf(aim);
			aim = i == aims.size() - 1 ? aims.get(0) : aims.get(i + 1);
		}
		if (in.isKeyDown(in.KEY_A)) {
			if (motion.getX() > -MAX_SPEED) {
				motion.add(-speed * delta, 0.0f);
			}
		} else if (in.isKeyDown(in.KEY_D)) {
			if (motion.getX() < MAX_SPEED) {
				motion.add(speed * delta, 0.0f);
			}
		}
		if (in.isKeyDown(in.KEY_W)) {
			motion.set(new Vector2(motion.getX(), JUMP_POWER));
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

		super.update(container, delta);
	}

}
