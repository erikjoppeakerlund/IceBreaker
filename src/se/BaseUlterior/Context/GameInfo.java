package se.BaseUlterior.Context;

import se.BaseUlterior.Actions.Action;

public class GameInfo extends Info {

	private Action state;

	public void setActionState(Action action) {
		this.state = action;
		notifyObservers();
	}
	//
	// @Override
	// public void wasActionStateSet(Action action) {
	// for (TextInfo textInfo : textInfos) {
	// textInfo.wasShowAction(action);
	// }
	// }

	public Action getState() {
		return state;
	}
}
