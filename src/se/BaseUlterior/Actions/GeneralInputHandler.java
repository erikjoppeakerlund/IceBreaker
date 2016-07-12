package se.BaseUlterior.Actions;

import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Context.SingleText;
import se.BaseUlterior.Context.TextInfo;
import se.BaseUlterior.Game.BreakingPoint;

public class GeneralInputHandler implements ActionListenable {

	private SingleText insertModeText = new SingleText(0, 0, 19,
			BreakingPoint.insertMode ? Constants.INSERT_MODE : Constants.ACTION_MODE, Color.green);

	public GeneralInputHandler() {
		TextInfo randomText = new TextInfo(Constants.CANVAS_WIDTH - 220, 22);
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
			BreakingPoint.insertMode = !BreakingPoint.insertMode;
			insertModeText.setValue(BreakingPoint.insertMode ? Constants.INSERT_MODE : Constants.ACTION_MODE);
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
