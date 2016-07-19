package se.BaseUlterior.Actions;

import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Context.SingleText;
import se.BaseUlterior.Context.TextInfo;
import se.BaseUlterior.Game.IceBreaker;

public class GeneralInputHandler implements ActionListenable {
	private static final String ESC = "|SWITCH:ESC";

	private SingleText insertModeText = new SingleText(0, 0, 21,
			IceBreaker.pause ? Action.PAUSE + ESC : IceBreaker.MODE_LATEST_ACTION + ESC, Color.red);

	public GeneralInputHandler() {
		TextInfo randomText = new TextInfo(Constants.CANVAS_WIDTH - 320, 22, true);
		randomText.singleTexts.add(insertModeText);
		IceBreaker.info.textInfos.add(randomText);
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
			IceBreaker.pause = !IceBreaker.pause;
			IceBreaker.MODE_ACTUAL = IceBreaker.pause ? Action.PAUSE : IceBreaker.MODE_LATEST_ACTION;
			insertModeText.setValue(IceBreaker.pause ? Action.PAUSE + ESC : IceBreaker.MODE_LATEST_ACTION + ESC);
			IceBreaker.setActionModeAction(IceBreaker.pause ? Action.ACTION_MODE_DESKTOP : Action.INSERT_MODE);
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
