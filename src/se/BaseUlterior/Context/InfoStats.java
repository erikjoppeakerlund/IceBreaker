package se.BaseUlterior.Context;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;

import se.BaseUlterior.Actions.Action;

public class InfoStats {
	public List<Observer> textInfos = new ArrayList<>();

	public void update(GameContainer container, int arg) {
	}

	public void notifyObservers() {
		for (Observer text : textInfos) {
			text.updateData();
		}
	}

	private float HP;
	private int kills;
	private HIT lastHit = HIT.STRAIGH;
	private String weapon;
	private Action gameState;

	public Action getGameState() {
		return gameState;
	}

	public void setGameState(Action gameState) {
		this.gameState = gameState;
	}

	public float getHP() {
		return HP;
	}

	public void setHP(float hP) {
		HP = hP;
		notifyObservers();
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
		notifyObservers();
	}

	public HIT getLastHit() {
		return lastHit;
	}

	public void setLastHit(HIT lastHit) {
		this.lastHit = lastHit;
		notifyObservers();
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
		notifyObservers();
	}

	public void addObserver(Observer observer) {
		this.textInfos.add(observer);
	}

}
