package se.BaseUlterior.GUI;

import org.newdawn.slick.Color;

import se.BaseUlterior.Context.SingleText;
import se.BaseUlterior.Context.TextInfo;
import se.BaseUlterior.Game.BreakingPoint;

public class Button extends Component {
	String text;

	public Button(String text) {
		super(120f, 30f);
		this.color = Color.gray;
		this.text = text;
	}

	protected void setText(String text) {
		TextInfo buttonText = new TextInfo(getX(), getY());
		buttonText.singleTexts.add(new SingleText(0, 0, 20, text, Color.black));
		BreakingPoint.info.textInfos.add(buttonText);
	}

	@Override
	protected void finalAction() {
		setText(text);
	}
}
