package se.BaseUlterior.Actions;

import org.newdawn.slick.Input;

public class GeneralInputHandler implements ActionListenable {
	public GeneralInputHandler() {
	}

	@Override
	public void wasDoubleClicked(int button, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void wasSingleClicked(int button, int x, int y) {

	}

	@Override
	public void wasWasKeyPressed(int button, char c) {
		if (button == Input.KEY_ESCAPE) {
		}
	}

	@Override
	public void wasWasKeyReleased(int button, char c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void wasMouseWheelMoved(int change) {
		// TODO Auto-generated method stub

	}

}
