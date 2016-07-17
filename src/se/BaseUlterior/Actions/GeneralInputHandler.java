package se.BaseUlterior.Actions;

import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Context.SingleText;
import se.BaseUlterior.Context.TextInfo;
import se.BaseUlterior.Game.BreakingPoint;

public class GeneralInputHandler implements ActionListenable {
	private static final String ESC = "|SWITCH:ESC";

	private SingleText insertModeText = new SingleText(0, 0, 21,
			BreakingPoint.pause ? Constants.INSERT_MODE + ESC : Constants.ACTION_MODE + ESC, Color.red);

	public GeneralInputHandler() {
		TextInfo randomText = new TextInfo(Constants.CANVAS_WIDTH - 320, 22, true);
		randomText.singleTexts.add(insertModeText);
		BreakingPoint.info.textInfos.add(randomText);
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
			BreakingPoint.pause = !BreakingPoint.pause;
			BreakingPoint.MODE_ACTUAL = BreakingPoint.pause ? Action.PAUSE : BreakingPoint.MODE_LATEST_ACTION;
			insertModeText.setValue(BreakingPoint.pause ? Action.PAUSE + ESC : BreakingPoint.MODE_LATEST_ACTION + ESC);
			BreakingPoint.setActionModeAction(BreakingPoint.pause ? Action.ACTION_MODE_DESKTOP : Action.INSERT_MODE);
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
