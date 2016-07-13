package se.BaseUlterior.Context;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.Physics.Impact;

public abstract class Info extends GameObject {

	public List<TextInfo> textInfos = new ArrayList<>();

	public void update(GameContainer container, int arg) throws SlickException {
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		for (TextInfo infos : textInfos) {
			infos.draw();
		}
	}

	@Override
	public Impact getImpact(GameObject agileObject) {
		return null;
	}

}
