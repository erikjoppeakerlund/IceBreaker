package se.BaseUlterior.Actions;

import java.util.ArrayList;

public class ActionListenablers {

	private ArrayList<ActionListenable> listeners;
	private WorldCreator wc = null;
	private GeneralInputHandler gih = null;

	public ActionListenablers() {
		listeners = new ArrayList<>();
		gih = new GeneralInputHandler();
		wc = new WorldCreator();
		listeners.add(wc);
		listeners.add(gih);
	}

	public void wasMouseClickedOnce(int button, int x, int y) {
		for (ActionListenable al : listeners) {
			al.wasSingleClicked(button, x, y);
		}
	}

	public void wasMouseClickedTwice(int button, int x, int y) {
		for (ActionListenable al : listeners) {
			al.wasDoubleClicked(button, x, y);
		}
	}

	public void wasKeyPressed(int key, char c) {
		for (ActionListenable al : listeners) {
			al.wasWasKeyPressed(key, c);
		}
	}

	public void wasKeyReleased(int key, char c) {
		for (ActionListenable al : listeners) {
			al.wasWasKeyReleased(key, c);
		}
	}

	public void wasMouseWheelMoved(int change) {
		for (ActionListenable al : listeners) {
			al.wasMouseWheelMoved(change);
		}
	}

}
