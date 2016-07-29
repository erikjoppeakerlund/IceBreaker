package se.BaseUlterior.ParallaX;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Entity.PartForce;
import se.BaseUlterior.Entity.PartFriction;
import se.BaseUlterior.Entity.PartSolidSimple;
import se.BaseUlterior.Physics.Density;

public class MapEssentials {
	private List<Entity> levelPieces;

	public MapEssentials() {
		levelPieces = new ArrayList<>();

		float thickness = 150.0f;

		float[] wallLeftPoints = new float[] { -thickness, -thickness, -thickness,
				Constants.CANVAS_HEIGHT_FULL + thickness, 0, Constants.CANVAS_HEIGHT_FULL + thickness, 0, -thickness };

		Entity wallLeft = new PartSolidSimple(wallLeftPoints);
		levelPieces.add(wallLeft);

		float[] wallRightPoints = new float[] { Constants.CANVAS_WIDTH_FULL, -thickness, Constants.CANVAS_WIDTH_FULL,
				Constants.CANVAS_HEIGHT_FULL + thickness, Constants.CANVAS_WIDTH_FULL + thickness,
				Constants.CANVAS_HEIGHT_FULL + thickness, Constants.CANVAS_WIDTH_FULL + thickness, -thickness };

		Entity wallRight = new PartSolidSimple(wallRightPoints);
		levelPieces.add(wallRight);

		float[] floorPoints = new float[] { -thickness, Constants.CANVAS_HEIGHT_FULL,
				Constants.CANVAS_WIDTH_FULL + thickness, Constants.CANVAS_HEIGHT_FULL,
				Constants.CANVAS_WIDTH_FULL + thickness, Constants.CANVAS_HEIGHT_FULL + thickness, -thickness,
				Constants.CANVAS_HEIGHT_FULL + thickness, -thickness, Constants.CANVAS_HEIGHT_FULL };

		Entity floor = new PartSolidSimple(floorPoints);
		levelPieces.add(floor);

		float[] roofPoints = new float[] { -thickness, 0, Constants.CANVAS_WIDTH_FULL + thickness, 0,
				Constants.CANVAS_WIDTH_FULL + thickness, -thickness, -thickness, -thickness };

		Entity roof = new PartSolidSimple(roofPoints);
		levelPieces.add(roof);

		float[] wholeScene = new float[] { 0.0f, 0.0f, 0.0f, Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL,
				Constants.CANVAS_HEIGHT_FULL, Constants.CANVAS_WIDTH_FULL, 0.0f };

		PartForce generalGravity = new PartForce(wholeScene, 0, Constants.GENERAL_GRAVITY,
				Color.transparent);

		levelPieces.add(generalGravity);

		levelPieces.add(new PartFriction(new float[] { 0, 0, 0, Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH,
				Constants.CANVAS_HEIGHT, Constants.CANVAS_WIDTH, 0 }, Density.AIR, true));

	}

	public List<Entity> getLevelPieces() {
		return levelPieces;
	}

}
