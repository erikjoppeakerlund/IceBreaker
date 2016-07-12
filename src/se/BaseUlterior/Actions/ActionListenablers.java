package se.BaseUlterior.Actions;

import java.util.ArrayList;

public class ActionListenablers {

	private ArrayList<ActionListenable> listeners;
	private WorldCreator worldCreator = null;
	private GeneralInputHandler input = null;

	public ActionListenablers() {
		listeners = new ArrayList<>();
		input = new GeneralInputHandler();
		worldCreator = new WorldCreator();
		listeners.add(worldCreator);
		listeners.add(input);
	}

	public void wasMouseClickedOnce(int button, int x, int y) {
		for (ActionListenable listener : listeners) {
			listener.wasSingleClicked(button, x, y);
		}
	}

	public void wasMouseClickedTwice(int button, int x, int y) {
		for (ActionListenable listener : listeners) {
			listener.wasDoubleClicked(button, x, y);
		}
	}

	public void wasKeyPressed(int key, char c) {
		for (ActionListenable listener : listeners) {
			listener.wasWasKeyPressed(key, c);
		}
	}

	public void wasKeyReleased(int key, char c) {
		for (ActionListenable listener : listeners) {
			listener.wasWasKeyReleased(key, c);
		}
	}

	public void wasMouseWheelMoved(int change) {
		for (ActionListenable listener : listeners) {
			listener.wasMouseWheelMoved(change);
		}
	}

	public void wasMouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub

	}

}
