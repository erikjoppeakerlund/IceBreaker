package se.BaseUlterior.Actions;

import org.newdawn.slick.Input;

import se.BaseUlterior.Game.BreakingPoint;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.WorldBuilderGround;

public class WorldCreator implements ActionListenable {

	private GameObject wb = null;

	public WorldCreator() {
		wb = new WorldBuilderGround(new float[0]);
		BreakingPoint.objsToAdd.add(wb);
	}

	@Override
	public void wasDoubleClicked(int button, int x, int y) {
		GameObject wbNew = new WorldBuilderGround(new float[0]);
		BreakingPoint.objsToAdd.add(wbNew);
		wb = wbNew;
	}

	@Override
	public void wasSingleClicked(int button, int x, int y) {
		if (!BreakingPoint.insertMode) {
			return;
		}
		if (button == Input.MOUSE_LEFT_BUTTON) {
			wb.addPoint(x, y);
		}
	}

	@Override
	public void wasWasKeyPressed(int button, char c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void wasWasKeyReleased(int button, char c) {
		// TODO Auto-generated method stub

	}
}
