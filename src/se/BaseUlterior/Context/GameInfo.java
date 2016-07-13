package se.BaseUlterior.Context;

import se.BaseUlterior.Actions.Action;

public class GameInfo extends Info {
	// public void show(boolean show) {
	// for (TextInfo textInfo : textInfos) {
	// textInfo.wasShowAction(show);
	// }
	// }

	@Override
	public void wasActionStateSet(Action action) {
		for (TextInfo textInfo : textInfos) {
			textInfo.wasShowAction(action);
		}
	}
}
