package se.BaseUlterior.Context;

import se.BaseUlterior.Actions.Action;

public class GameInfo extends Info {
	@Override
	public void wasActionStateSet(Action action) {
		for (TextInfo textInfo : textInfos) {
			textInfo.wasShowAction(action);
		}
	}
}
