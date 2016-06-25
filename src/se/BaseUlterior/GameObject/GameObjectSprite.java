package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import se.BaseUlterior.Aim.Aim;
import se.BaseUlterior.Geom.Vector2;

public class GameObjectSprite extends GameObjectAgile {

	protected Aim aim = null;
	protected List<Aim> aims = null;
	protected float speed = 0.5f;
	private final float JUMP_EFFECT = 7.2f;
	protected final float MAX_SPEED = 8.0f;

	public GameObjectSprite(float[] nodes, float bouncyness) {
		super(nodes, bouncyness);
	}

	private void initAims() {
		aims = new ArrayList<Aim>();
		// aims.add(arg0)
		// TODO: enter the aims as you'be created them!
	}

	@Override
	public void update(GameContainer container, int arg) {
		Input in = container.getInput();

		if (in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			aim.primaryPushed();
		}
		if (in.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			aim.secondaryPushed();
		}

		if (Mouse.getDWheel() > 0) {
			int i = aims.indexOf(aim);
			aim = i == aims.size() - 1 ? aims.get(0) : aims.get(i + 1);
		}
		if (in.isKeyDown(in.KEY_LEFT)) {
			if (motion.getX() > -MAX_SPEED) {
				motion.add(-speed, 0.0f);
			}
		} else if (in.isKeyDown(in.KEY_RIGHT)) {
			if (motion.getX() < MAX_SPEED) {
				motion.add(speed, 0.0f);
			}
		}
		if (in.isKeyDown(in.KEY_SPACE)) {
			motion.set(new Vector2(motion.getX(), -4.0f));
		}

		super.update(container, arg);
	}

}
