package se.BaseUlterior.ParallaX;

import java.util.ArrayList;
import java.util.List;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.Entity.Entity;
import se.BaseUlterior.Entity.PartForce;
import se.BaseUlterior.Entity.PartFriction;
import se.BaseUlterior.Entity.PartSolid;
import se.BaseUlterior.Physics.Density;

public class LevelDummy {
	public List<Entity> levelPieces;

	public LevelDummy() {

		levelPieces = new ArrayList<>();
		level2();
	}

	public void level2() {

		levelPieces.add(new PartForce(new float[] { 375, 4365, 1875, 4030, 1280, 4745, 2975, 4300, 2480, 5015, 3775,
				4635, 3775, 5100, 5585, 4505, 5385, 5115, 6950, 4320, 6885, 5060, 8375, 4365, 8405, 4980, 9435, 3770,
				8055, 4365, 8655, 3340, 6715, 4170, 7300, 2965, 4910, 4420, 5740, 3070, 3335, 4355, 4095, 3200, 2060,
				3915, 2725, 3270, 1100, 3845,

		}, 0, -Constants.GENERAL_GRAVITY * 2f));

		levelPieces.add(new PartFriction(new float[] { 7345, 1495, 4440, 2465, 6000, 1290, 3000, 2240, 3975, 1605, 1400,
				2560, 2960, 2500, 800, 3425, 3820, 3275, 2440, 4235, 5995, 3780, 3445, 4525, 8670, 3550, 6530, 3780,
				8555, 2500, 4975, 3155, 8730, 1955, 6015, 2350,

		}, Density.LAVA));

		levelPieces.add(new PartSolid(new float[] { 490, 1015, 1665, 1400, 2490, 1110, 3400, 895, 4580, 990, 4620, 590,
				3850, 515, 2460, 645, 1515, 655, 1300, 955, 600, 555, 325, 465, }));

		levelPieces.add(new PartSolid(new float[] { 2010, 1725, 3120, 1990, 4275, 1950, 5265, 1715, 5855, 2005, 6320,
				1500, 5050, 1230, 3860, 1700, 3280, 1380, 2730, 1620, }));

		levelPieces.add(new PartSolid(new float[] { 280, 2205, 1065, 2435, 2085, 2770, 2265, 2290, 1545, 2235, 1570,
				1730, 945, 1650, 480, 1855, 185, 1600, }));

		levelPieces.add(new PartSolid(new float[] { 1170, 3420, 3045, 3590, 4310, 3700, 5810, 3000, 4330, 2895, 3795,
				3430, 2380, 2975, 1975, 2965, 1085, 2875, }));

		levelPieces.add(new PartSolid(new float[] { 280, 4090, 405, 4615, 920, 4845, 1415, 4715, 1785, 4280, 980, 4420,
				715, 3835, 600, 4155, 240, 3670, }));
		levelPieces.add(new PartSolid(new float[] { 2590, 4375, 3585, 4360, 3765, 4710, 4980, 4580, 4660, 4210, 4890,
				3770, 4085, 3995, 2745, 3760, 2205, 4135, 2110, 4400, }));

		levelPieces.add(new PartSolid(new float[] { 1495, 4925, 1615, 5225, 3935, 5240, 4225, 4915, 3015, 4975, 2540,
				4600, 2250, 4920, 1760, 4695, }));

		levelPieces.add(new PartSolid(new float[] { 5300, 4195, 6230, 4375, 6380, 4100, 6520, 3820, 7050, 4235, 7130,
				3760, 7745, 3685, 7735, 3330, 6715, 3345, 6410, 3640, 5695, 3510, 5620, 3840, 5080, 3820, }));

		levelPieces.add(new PartSolid(new float[] { 5770, 2700, 6860, 2680, 7600, 2880, 8355, 2695, 7580, 2570, 6685,
				2450, 6350, 1950, 6340, 2435, 4940, 2405, }));

		levelPieces.add(
				new PartSolid(new float[] { 2930, 2525, 3720, 2620, 4315, 2425, 4285, 2185, 3425, 2435, 2915, 2265, }));
		levelPieces.add(new PartSolid(new float[] { 5435, 755, 5680, 1010, 6010, 750, 7030, 900, 7480, 690, 8075, 995,
				8290, 560, 6905, 530, 6060, 445, 5450, 320, 4980, 535, }));

		levelPieces.add(new PartSolid(new float[] { 9385, 1275, 9250, 935, 8075, 1285, 7650, 1480, 7265, 1215, 7045,
				1610, 7525, 2075, 8165, 1850, 8285, 1495, 8975, 1770, 9285, 1600, }));

		levelPieces.add(new PartSolid(new float[] { 7930, 3105, 8295, 3400, 8860, 3250, 9430, 3310, 9525, 2880, 9030,
				2605, 8690, 3120, 8300, 2980, }));
		levelPieces.add(new PartSolid(new float[] { 7610, 5260, 7920, 4720, 8670, 4890, 8945, 5090, 9245, 4695, 8715,
				4385, 9260, 4010, 8760, 3950, 8150, 4350, 7560, 4260, 7170, 4645, }));

		levelPieces.add(new PartSolid(new float[] { 4815, 4885, 4825, 5175, 5375, 5015, 5810, 5240, 6775, 5085, 6790,
				4800, 6870, 4520, 6035, 4575, 5690, 4825, 5225, 4555, }));

		levelPieces.add(new PartSolid(new float[] { 1295, 220, 1860, 345, 2195, 215, 2875, 395, 3520, 255, 3885, 105,
				3140, 200, 1850, 120, }));

		levelPieces.add(new PartSolid(new float[] { 8390, 160, 8540, 450, 8945, 335, 9005, 570, 9295, 585, 9350, 200,
				8920, 145, 8585, 110, }));

	}
}
