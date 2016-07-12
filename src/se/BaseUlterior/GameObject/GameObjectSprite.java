package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.Aim.AimBlade;
import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.Geom.Vector2;
import se.BaseUlterior.Utils.UlteriorUtils;

public class GameObjectSprite extends GameObjectFalling {

	protected Aim aim = null;
	protected List<Aim> aims = null;
	protected float speed = 0.0006f;
	protected final float MAX_SPEED = 0.27f;
	protected boolean mouseButtonPirmaryDown = false;

	protected boolean right = true;

	protected float JUMP_POWER = -0.23f;

	public GameObjectSprite(float[] nodes, float bouncyness) {
		super(nodes, bouncyness);
		color = Color.darkGray;
		initAims();
	}

	private void initAims() {
		aims = new ArrayList<Aim>();
		// aims.add(new AimGrenade());
		aims.add(new AimBlade());
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
		} else if (Mouse.getDWheel() < 0) {
			int i = aims.indexOf(aim);
			aim = i == 0 ? aims.get(aims.size() - 1) : aims.get(i - 1);
		}
		if (in.isKeyDown(Input.KEY_A)) {
			if (motion.getX() > -MAX_SPEED) {
				motion.add(-speed * delta, 0.0f);
			}
		} else if (in.isKeyDown(Input.KEY_D)) {
			if (motion.getX() < MAX_SPEED) {
				motion.add(speed * delta, 0.0f);
			}
		}
		if (in.isKeyDown(Input.KEY_W)) {
			if (motion.y > 0) {
				motion.set(new Vector2(motion.getX(), JUMP_POWER));
			}
		}
		if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			aim.primaryPushed();
			if (!mouseButtonPirmaryDown) {
				mouseButtonPirmaryDown = true;
			}
		} else if (!in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && mouseButtonPirmaryDown) {
			mouseButtonPirmaryDown = false;
			aim.primaryReleased();
		}

		float angleToPoint = UlteriorUtils.angleToPoint(getCenterX(), getCenterY(), mouseX, mouseY);

		aim.setPosition(getCenterX(), getCenterY());

		aim.setAngleToMouse(angleToPoint);

		aim.update(container, delta);

		super.update(container, delta);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		super.render(container, graphics);
		aim.render(container, graphics);
	}

}
