package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.WorldBuilderForce;
import se.BaseUlterior.GameObject.WorldBuilderGroundSolid;
import se.BaseUlterior.GameObject.WorldBuilderLiquid;
import se.BaseUlterior.Physics.Density;

public class GroundMap {
	public List<GameObject> levelPieces;

	public GroundMap() {
		levelPieces = new ArrayList<>();
		try {
			Image parallax1 = new Image("res/img/BackgroundStars.jpg");
			float parallaxHeight = parallax1.getHeight();
			float parallaxWidth = parallax1.getWidth();
			float[] edges = { 0, 0, 0, parallaxHeight, parallaxWidth, parallaxHeight, parallaxWidth, 0 };
			Parallax parallax = new Parallax(edges, parallax1, 0.2f);
			levelPieces.add(parallax);
			IceBreaker.parallaxList.add(parallax);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		// ______________________________________________________

		float thickness = 150.0f;

		float[] wallLeftPoints = new float[] { -thickness, -thickness, -thickness,
				Constants.CANVAS_HEIGHT_FULL + thickness, 0, Constants.CANVAS_HEIGHT_FULL + thickness, 0, -thickness };

		GameObject wallLeft = new WorldBuilderGroundSolid(wallLeftPoints);
		levelPieces.add(wallLeft);

		float[] wallRightPoints = new float[] { Constants.CANVAS_WIDTH_FULL, -thickness, Constants.CANVAS_WIDTH_FULL,
				Constants.CANVAS_HEIGHT_FULL + thickness, Constants.CANVAS_WIDTH_FULL + thickness,
				Constants.CANVAS_HEIGHT_FULL + thickness, Constants.CANVAS_WIDTH_FULL + thickness, -thickness };

		GameObject wallRight = new WorldBuilderGroundSolid(wallRightPoints);
		levelPieces.add(wallRight);

		float[] floorPoints = new float[] { -thickness, Constants.CANVAS_HEIGHT_FULL,
				Constants.CANVAS_WIDTH_FULL + thickness, Constants.CANVAS_HEIGHT_FULL,
				Constants.CANVAS_WIDTH_FULL + thickness, Constants.CANVAS_HEIGHT_FULL + thickness, -thickness,
				Constants.CANVAS_HEIGHT_FULL + thickness, -thickness, Constants.CANVAS_HEIGHT_FULL };

		GameObject floor = new WorldBuilderGroundSolid(floorPoints);
		levelPieces.add(floor);

		float[] roofPoints = new float[] { -thickness, 0, Constants.CANVAS_WIDTH_FULL + thickness, 0,
				Constants.CANVAS_WIDTH_FULL + thickness, -thickness, -thickness, -thickness };

		GameObject roof = new WorldBuilderGroundSolid(roofPoints);
		levelPieces.add(roof);

		float[] wholeScene = new float[] { 0.0f, 0.0f, 0.0f, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL,
				Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL, 0.0f };

		WorldBuilderForce generalGravity = new WorldBuilderForce(wholeScene, 0, Constants.GENERAL_GRAVITY,
				Color.transparent);

		levelPieces.add(generalGravity);

		levelPieces.add(new WorldBuilderLiquid(wholeScene, Density.AIR, true));

	}

}
