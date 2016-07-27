package se.BaseUlterior.Game;

import java.util.ArrayList;
import java.util.List;

import se.BaseUlterior.Config.Constants;
import se.BaseUlterior.GameObject.GameObject;
import se.BaseUlterior.GameObject.WorldBuilderForce;
import se.BaseUlterior.GameObject.WorldBuilderGround;
import se.BaseUlterior.GameObject.WorldBuilderLiquid;
import se.BaseUlterior.Physics.Density;

public class LevelDummy {
	public List<GameObject> levelPieces;

	public LevelDummy() {

		levelPieces = new ArrayList<>();
		level2();
	}

	public void level1() {
		levelPieces.add(new WorldBuilderForce(new float[] { 10477, 2579, 5049, 77, 10477, 66, },
				Constants.GENERAL_GRAVITY / 4f, 0f));
		levelPieces.add(new WorldBuilderLiquid(new float[] { 5747, 3608, 6066, 4000, 5000, 4867, 4081, 4933, 3162, 4697,
				2579, 4334, 2255, 3844, 1952, 3487, 1650, 3157, 1655, 3047,

		}, Density.LAVA));

		levelPieces.add(new WorldBuilderGround(new float[] { 1848, 4455, 2112, 4636, 2431, 4713, 2772, 4856, 3256, 4801,
				3575, 4939, 3927, 4917, 4345, 5104, 4554, 5307, 4609, 5577, 4394, 5725, 3817, 5764, 6484, 5780, 5467,
				5654, 5115, 5357, 4988, 4972, 5324, 4664, 6165, 4444, 6413, 4136, 6314, 3740, 6006, 3388, 5797, 3190,
				6242, 2970, 4510, 2882, 5296, 3267, 5654, 3729, 5929, 4097, 5659, 4339, 5093, 4504, 4669, 4598, 4191,
				4460, 4064, 4108, 4086, 3696, 4059, 3366, 3718, 3179, 3580, 3019, 3822, 2887, 2662, 2656, 3118, 2898,
				3239, 3272, 3228, 3641, 3498, 3932, 3718, 4224, 3701, 4449, 3360, 4548, 3014, 4482, 2535, 4224, 2354,
				3767, 2013, 3393, 1694, 3052, 1600, 2656, 2123, 2508, 1358, 2370, 891, 2370, 396, 2348, 104, 2502, 693,
				2739, 1193, 3118, 1578, 3624, 1732, 4108, 1666, 4246, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 137, 4218, 132, 4449, 137, 4955, 170, 5461, 110, 5846,
				2898, 5797, 984, 4746, 643, 3949, 324, 3327, 99, 3074, 77, 3619,

		}));

		levelPieces.add(new WorldBuilderGround(new float[] { 9311, 5742, 9570, 5747, 9839, 5769, 10081, 5742, 10411,
				5417, 10384, 4268, 8618, 5670, 8987, 5753, 9135, 5725, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 9124, 5065, 9421, 4867, 9762, 4482, 9982, 4207, 10263,
				3993, 10373, 3399, 7562, 5731, 8002, 5698, 8360, 5648, 8811, 5329, 8943, 5093, }));

		levelPieces.add(new WorldBuilderGround(
				new float[] { 8475, 4812, 8882, 4510, 9707, 3740, 10213, 3316, 10345, 3074, 10428, 2442, 9531, 3443,
						8266, 4378, 7452, 5175, 6825, 5571, 6963, 5747, 7293, 5703, 7815, 5302, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 7942, 3602, 8096, 3481, 8371, 3333, 8453, 2194, 7738, 2178,
				7804, 3338, 7788, 3635, 7898, 3657, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 7590, 3113, 7623, 2777, 7617, 2403, 7612, 2139, 7562, 1892,
				6677, 1837, 6759, 2156, 6886, 2497, 7018, 2794, 7238, 3036, 7491, 3223, 7639, 3234, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 6534, 2348, 6347, 2112, 6308, 1886, 6325, 1694, 6110, 1567,
				5769, 1595, 5230, 1485, 4895, 1369, 5109, 1771, 5494, 2046, 5940, 2249, 6165, 2480, 6325, 2684, 6561,
				2750, 6710, 2673, 6649, 2453, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 4075, 2205, 4345, 2332, 4625, 2381, 4796, 2403, 5087, 2469,
				5417, 2480, 5417, 2425, 5230, 2299, 4845, 2057, 4554, 1721, 4273, 1540, 4119, 1342, 4114, 1204, 3690,
				1193, 3349, 1226, 3014, 1210, 2678, 1100, 2359, 973, 2156, 907, 2249, 1149, 2656, 1523, 2948, 1842,
				3767, 2216, 4026, 2266, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 1182, 1380, 1237, 1743, 1199, 2167, 1534, 1985, 1749, 1617,
				1881, 1166, 1859, 918, 1435, 907, 1155, 924, 995, 1001, 1133, 1133, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 198, 1303, 253, 1600, 374, 1908, 517, 2084, 709, 2189, 753,
				2046, 638, 1760, 588, 1424, 676, 1182, 797, 1050, 385, 990, 82, 1039, 55, 1221, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 4658, 346, 4647, 500, 4609, 693, 4587, 863, 4818, 737,
				4928, 451, 5109, 187, 4884, 159, 4697, 165, 4521, 220, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 3773, 231, 3861, 385, 3888, 533, 3872, 704, 3987, 638,
				4053, 368, 4251, 170, 4020, 148, 3850, 148, 3641, 126, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 3371, 484, 3289, 368, 3261, 220, 3047, 176, 2750, 126,
				2964, 357, 3245, 511, 3443, 671, 3498, 528, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 7414, 1012, 7568, 1006, 7722, 1006, 7738, 1336, 7744, 1523,
				7947, 1677, 8261, 1787, 8651, 1848, 8926, 1699, 9053, 1424, 9069, 1078, 8932, 764, 8635, 638, 8052, 616,
				7326, 676, 6556, 759, 6143, 726, 5439, 792, 5164, 698, 4906, 1193, 5318, 1028, 5357, 1133, 5681, 1001,
				5709, 1155, 6066, 1028, 6138, 1100, 6407, 1001, 6990, 946, 7502, 973, 7667, 907, }));

	}

	public void level2() {

		levelPieces.add(new WorldBuilderForce(new float[] { 375, 4365, 1875, 4030, 1280, 4745, 2975, 4300, 2480, 5015,
				3775, 4635, 3775, 5100, 5585, 4505, 5385, 5115, 6950, 4320, 6885, 5060, 8375, 4365, 8405, 4980, 9435,
				3770, 8055, 4365, 8655, 3340, 6715, 4170, 7300, 2965, 4910, 4420, 5740, 3070, 3335, 4355, 4095, 3200,
				2060, 3915, 2725, 3270, 1100, 3845,

		}, 0, -Constants.GENERAL_GRAVITY * 2f));

		levelPieces.add(new WorldBuilderLiquid(new float[] { 7345, 1495, 4440, 2465, 6000, 1290, 3000, 2240, 3975, 1605,
				1400, 2560, 2960, 2500, 800, 3425, 3820, 3275, 2440, 4235, 5995, 3780, 3445, 4525, 8670, 3550, 6530,
				3780, 8555, 2500, 4975, 3155, 8730, 1955, 6015, 2350,

		}, Density.LAVA));

		levelPieces.add(new WorldBuilderGround(new float[] { 490, 1015, 1665, 1400, 2490, 1110, 3400, 895, 4580, 990,
				4620, 590, 3850, 515, 2460, 645, 1515, 655, 1300, 955, 600, 555, 325, 465, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 2010, 1725, 3120, 1990, 4275, 1950, 5265, 1715, 5855, 2005,
				6320, 1500, 5050, 1230, 3860, 1700, 3280, 1380, 2730, 1620, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 280, 2205, 1065, 2435, 2085, 2770, 2265, 2290, 1545, 2235,
				1570, 1730, 945, 1650, 480, 1855, 185, 1600, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 1170, 3420, 3045, 3590, 4310, 3700, 5810, 3000, 4330, 2895,
				3795, 3430, 2380, 2975, 1975, 2965, 1085, 2875, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 280, 4090, 405, 4615, 920, 4845, 1415, 4715, 1785, 4280,
				980, 4420, 715, 3835, 600, 4155, 240, 3670, }));
		levelPieces.add(new WorldBuilderGround(new float[] { 2590, 4375, 3585, 4360, 3765, 4710, 4980, 4580, 4660, 4210,
				4890, 3770, 4085, 3995, 2745, 3760, 2205, 4135, 2110, 4400, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 1495, 4925, 1615, 5225, 3935, 5240, 4225, 4915, 3015, 4975,
				2540, 4600, 2250, 4920, 1760, 4695, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 5300, 4195, 6230, 4375, 6380, 4100, 6520, 3820, 7050, 4235,
				7130, 3760, 7745, 3685, 7735, 3330, 6715, 3345, 6410, 3640, 5695, 3510, 5620, 3840, 5080, 3820, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 5770, 2700, 6860, 2680, 7600, 2880, 8355, 2695, 7580, 2570,
				6685, 2450, 6350, 1950, 6340, 2435, 4940, 2405, }));

		levelPieces.add(new WorldBuilderGround(
				new float[] { 2930, 2525, 3720, 2620, 4315, 2425, 4285, 2185, 3425, 2435, 2915, 2265, }));
		levelPieces.add(new WorldBuilderGround(new float[] { 5435, 755, 5680, 1010, 6010, 750, 7030, 900, 7480, 690,
				8075, 995, 8290, 560, 6905, 530, 6060, 445, 5450, 320, 4980, 535, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 9385, 1275, 9250, 935, 8075, 1285, 7650, 1480, 7265, 1215,
				7045, 1610, 7525, 2075, 8165, 1850, 8285, 1495, 8975, 1770, 9285, 1600, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 7930, 3105, 8295, 3400, 8860, 3250, 9430, 3310, 9525, 2880,
				9030, 2605, 8690, 3120, 8300, 2980, }));
		levelPieces.add(new WorldBuilderGround(new float[] { 7610, 5260, 7920, 4720, 8670, 4890, 8945, 5090, 9245, 4695,
				8715, 4385, 9260, 4010, 8760, 3950, 8150, 4350, 7560, 4260, 7170, 4645, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 4815, 4885, 4825, 5175, 5375, 5015, 5810, 5240, 6775, 5085,
				6790, 4800, 6870, 4520, 6035, 4575, 5690, 4825, 5225, 4555, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 1295, 220, 1860, 345, 2195, 215, 2875, 395, 3520, 255,
				3885, 105, 3140, 200, 1850, 120, }));

		levelPieces.add(new WorldBuilderGround(new float[] { 8390, 160, 8540, 450, 8945, 335, 9005, 570, 9295, 585,
				9350, 200, 8920, 145, 8585, 110, }));

	}
}
