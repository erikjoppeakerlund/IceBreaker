package se.BaseUlterior.Actions;

public interface ActionListenable {

	void wasDoubleClicked(int button, int x, int y);

	void wasSingleClicked(int button, int x, int y);

	void wasWasKeyPressed(int button, char c);

	void wasWasKeyReleased(int button, char c);

	void wasMouseWheelMoved(int change);
}
