package se.BaseUlterior.GameObject;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import se.BaseUlterior.Aim.Aim;

public class GameObjectSprite extends GameObjectAgile {

	protected Aim aim = null;
	protected List<Aim> aims = null;

	public GameObjectSprite(float[] nodes) {
		super(nodes);
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

		super.update(container, arg);
	}

}
