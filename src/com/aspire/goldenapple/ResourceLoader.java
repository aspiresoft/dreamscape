package com.aspire.goldenapple;

import java.io.File;
import javax.imageio.ImageIO;

public class ResourceLoader implements Runnable {
	@Override
	public void run() {
		try {
			Constants.BG = ImageIO.read(new File("Resources/Textures/Backgrounds/BG_menu.png"));

			Constants.IMAGES[0] = ImageIO.read(new File("Resources/Textures/Units/Player/player.png"));

			Constants.IMAGES[1] = ImageIO.read(new File("Resources/Textures/Units/Gate/gateOn.png"));

			Constants.IMAGES[2] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagBlue.png"));
			Constants.IMAGES[3] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagGreen.png"));
			Constants.IMAGES[4] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagRed.png"));
			Constants.IMAGES[5] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagYellow.png"));

			Constants.IMAGES[6] = ImageIO.read(new File("Resources/Textures/Units/Coin/Bronze/bronze_1.png"));
			Constants.IMAGES[7] = ImageIO.read(new File("Resources/Textures/Units/Coin/Silver/silver_1.png"));
			Constants.IMAGES[8] = ImageIO.read(new File("Resources/Textures/Units/Coin/Gold/gold_1.png"));

			Constants.IMAGES[9] = ImageIO.read(new File("Resources/Textures/Units/Key/keyBlue.png"));
			Constants.IMAGES[10] = ImageIO.read(new File("Resources/Textures/Units/Key/keyGreen.png"));
			Constants.IMAGES[11] = ImageIO.read(new File("Resources/Textures/Units/Key/keyRed.png"));
			Constants.IMAGES[12] = ImageIO.read(new File("Resources/Textures/Units/Key/keyYellow.png"));

			Constants.IMAGES[13] = ImageIO.read(new File("Resources/Textures/Units/Lock/lockBlue.png"));
			Constants.IMAGES[14] = ImageIO.read(new File("Resources/Textures/Units/Lock/lockGreen.png"));
			Constants.IMAGES[15] = ImageIO.read(new File("Resources/Textures/Units/Lock/lockRed.png"));
			Constants.IMAGES[16] = ImageIO.read(new File("Resources/Textures/Units/Lock/lockYellow.png"));

			Constants.IMAGES[17] = ImageIO.read(new File("Resources/Textures/Units/Spring/springDown.png"));
			Constants.IMAGES[18] = ImageIO.read(new File("Resources/Textures/Units/Spring/springLeft.png"));
			Constants.IMAGES[19] = ImageIO.read(new File("Resources/Textures/Units/Spring/springRight.png"));
			Constants.IMAGES[20] = ImageIO.read(new File("Resources/Textures/Units/Spring/springUp.png"));

			Constants.IMAGES[21] = ImageIO.read(new File("Resources/Textures/Units/Spike/spikesDown.png"));
			Constants.IMAGES[22] = ImageIO.read(new File("Resources/Textures/Units/Spike/spikesLeft.png"));
			Constants.IMAGES[23] = ImageIO.read(new File("Resources/Textures/Units/Spike/spikesRight.png"));
			Constants.IMAGES[24] = ImageIO.read(new File("Resources/Textures/Units/Spike/spikesUp.png"));

			Constants.IMAGES[25] = ImageIO.read(new File("Resources/Textures/Units/Laser/laserDown.png"));
			Constants.IMAGES[26] = ImageIO.read(new File("Resources/Textures/Units/Laser/laserLeft.png"));
			Constants.IMAGES[27] = ImageIO.read(new File("Resources/Textures/Units/Laser/laserRight.png"));
			Constants.IMAGES[28] = ImageIO.read(new File("Resources/Textures/Units/Laser/laserUp.png"));

			Constants.IMAGES[29] = ImageIO.read(new File("Resources/Textures/Units/Box/box01.png"));
			Constants.IMAGES[30] = ImageIO.read(new File("Resources/Textures/Units/Box/box02.png"));
			Constants.IMAGES[31] = ImageIO.read(new File("Resources/Textures/Units/Box/box03.png"));

			Constants.IMAGES[32] = ImageIO.read(new File("Resources/Textures/Units/Water/water01.png"));
			Constants.IMAGES[33] = ImageIO.read(new File("Resources/Textures/Units/Water/water02.png"));

			Constants.IMAGES[34] = ImageIO.read(new File("Resources/Textures/Units/Acid/acid01.png"));
			Constants.IMAGES[35] = ImageIO.read(new File("Resources/Textures/Units/Acid/acid02.png"));

			Constants.IMAGES[36] = ImageIO.read(new File("Resources/Textures/Units/Lava/lava01.png"));
			Constants.IMAGES[37] = ImageIO.read(new File("Resources/Textures/Units/Lava/lava02.png"));

			Constants.IMAGES[38] = ImageIO.read(new File("Resources/Textures/Units/Brick/brick01.png"));
			Constants.IMAGES[39] = ImageIO.read(new File("Resources/Textures/Units/Brick/brick02.png"));

			Constants.IMAGES[40] = ImageIO.read(new File("Resources/Textures/Units/Door/close01.png"));
			Constants.IMAGES[41] = ImageIO.read(new File("Resources/Textures/Units/Door/close02.png"));
			Constants.IMAGES[42] = ImageIO.read(new File("Resources/Textures/Units/Door/open01.png"));
			Constants.IMAGES[43] = ImageIO.read(new File("Resources/Textures/Units/Door/open02.png"));

			Constants.IMAGES[44] = ImageIO.read(new File("Resources/Textures/Units/Fire/01.png"));

			Constants.IMAGES[45] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_01.png"));

			Constants.IMAGES[46] = ImageIO.read(new File("Resources/Textures/Units/Kunai/Kunais.png"));

			Constants.IMAGES[47] = ImageIO.read(new File("Resources/Textures/Units/Slime/slimeWalk1.png"));

			Constants.IMAGES[48] = ImageIO.read(new File("Resources/Textures/Units/Snail/snail_walk_left_1.png"));

			Constants.IMAGES[49] = ImageIO.read(new File("Resources/Textures/Units/Ladder/ladder.png"));

			Constants.IMAGES[50] = ImageIO.read(new File("Resources/UI/hudHeart_full.png"));

			Constants.IMAGES[51] = ImageIO.read(new File("Resources/Textures/Units/Fly/fly_r_1.png"));
			Constants.IMAGES[52] = ImageIO.read(new File("Resources/Textures/Units/Spinner/ver_1.png"));
			Constants.IMAGES[53] = ImageIO.read(new File("Resources/Textures/Units/Ghost/ghost_l_1.png"));

			Constants.IMAGES[54] = ImageIO.read(new File("Resources/Textures/Units/Robot/player.png"));
			Constants.IMAGES[55] = ImageIO.read(new File("Resources/Textures/Units/Knight/player.png"));

			Constants.IMAGES[78] = ImageIO.read(new File("Resources/Textures/Particle/DarkBrown/particle_darkBrown_01.png"));
			Constants.IMAGES[79] = ImageIO.read(new File("Resources/Textures/Particle/DarkBrown/particle_darkBrown_02.png"));
			Constants.IMAGES[80] = ImageIO.read(new File("Resources/Textures/Particle/DarkBrown/particle_darkBrown_03.png"));

			Constants.IMAGES[81] = ImageIO.read(new File("Resources/Textures/Particle/Grey/particle_grey_01.png"));
			Constants.IMAGES[82] = ImageIO.read(new File("Resources/Textures/Particle/Grey/particle_grey_02.png"));
			Constants.IMAGES[83] = ImageIO.read(new File("Resources/Textures/Particle/Grey/particle_grey_03.png"));

			Constants.IMAGES[98] = ImageIO.read(new File("Resources/Textures/Units/Snail/snail_walk_left_1.png"));
			Constants.IMAGES[99] = ImageIO.read(new File("Resources/Textures/Units/Snail/snail_walk_left_2.png"));
			Constants.IMAGES[100] = ImageIO.read(new File("Resources/Textures/Units/Snail/snail_walk_right_1.png"));
			Constants.IMAGES[101] = ImageIO.read(new File("Resources/Textures/Units/Snail/snail_walk_right_2.png"));
			Constants.IMAGES[102] = ImageIO.read(new File("Resources/Textures/Units/Snail/snail_shell.png"));
			Constants.IMAGES[103] = ImageIO.read(new File("Resources/Textures/Units/Snail/snail_shell.png"));

			Constants.IMAGES[104] = ImageIO.read(new File("Resources/Textures/Units/Slime/slimeWalk1.png"));
			Constants.IMAGES[105] = ImageIO.read(new File("Resources/Textures/Units/Slime/slimeWalk2.png"));
			Constants.IMAGES[106] = ImageIO.read(new File("Resources/Textures/Units/Slime/slimeWalk3.png"));
			Constants.IMAGES[107] = ImageIO.read(new File("Resources/Textures/Units/Slime/slimeWalk4.png"));
			Constants.IMAGES[108] = ImageIO.read(new File("Resources/Textures/Units/Snail/snail_shell.png"));
			Constants.IMAGES[109] = ImageIO.read(new File("Resources/Textures/Units/Slime/slimeDead.png"));
			Constants.IMAGES[110] = ImageIO.read(new File("Resources/Textures/Units/Slime/slimeDead2.png"));

			Constants.IMAGES[111] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_01.png"));
			Constants.IMAGES[112] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_02.png"));
			Constants.IMAGES[113] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_03.png"));
			Constants.IMAGES[114] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_04.png"));
			Constants.IMAGES[115] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_05.png"));
			Constants.IMAGES[116] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_06.png"));
			Constants.IMAGES[117] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_07.png"));
			Constants.IMAGES[118] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_08.png"));
			Constants.IMAGES[119] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_09.png"));
			Constants.IMAGES[120] = ImageIO.read(new File("Resources/Textures/Units/Volcano/volcano_10.png"));

			Constants.IMAGES[121] = Constants.IMAGES[50];
			Constants.IMAGES[122] = ImageIO.read(new File("Resources/UI/hudHeart_half.png"));
			Constants.IMAGES[123] = ImageIO.read(new File("Resources/UI/hudHeart_empty.png"));

			Constants.IMAGES[124] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_green_left.png"));
			Constants.IMAGES[125] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_green_mid.png"));
			Constants.IMAGES[126] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_green_right.png"));
			Constants.IMAGES[127] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_red_left.png"));
			Constants.IMAGES[128] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_red_mid.png"));
			Constants.IMAGES[129] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_red_right.png"));
			Constants.IMAGES[130] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_white_left.png"));
			Constants.IMAGES[131] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_white_mid.png"));
			Constants.IMAGES[132] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_white_right.png"));
			Constants.IMAGES[133] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_yellow_left.png"));
			Constants.IMAGES[134] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_yellow_mid.png"));
			Constants.IMAGES[135] = ImageIO.read(new File("Resources/UI/Hud/barHorizontal_yellow_right.png"));

			Constants.IMAGES[136] = ImageIO.read(new File("Resources/UI/hudKey_blue.png"));
			Constants.IMAGES[137] = ImageIO.read(new File("Resources/UI/hudKey_blue_empty.png"));
			Constants.IMAGES[138] = ImageIO.read(new File("Resources/UI/hudKey_green.png"));
			Constants.IMAGES[139] = ImageIO.read(new File("Resources/UI/hudKey_green_empty.png"));
			Constants.IMAGES[140] = ImageIO.read(new File("Resources/UI/hudKey_red.png"));
			Constants.IMAGES[141] = ImageIO.read(new File("Resources/UI/hudKey_red_empty.png"));
			Constants.IMAGES[142] = ImageIO.read(new File("Resources/UI/hudKey_yellow.png"));
			Constants.IMAGES[143] = ImageIO.read(new File("Resources/UI/hudKey_yellow_empty.png"));

			Constants.IMAGES[144] = ImageIO.read(new File("Resources/UI/Num/hud0.png"));
			Constants.IMAGES[145] = ImageIO.read(new File("Resources/UI/Num/hud1.png"));
			Constants.IMAGES[146] = ImageIO.read(new File("Resources/UI/Num/hud2.png"));
			Constants.IMAGES[147] = ImageIO.read(new File("Resources/UI/Num/hud3.png"));
			Constants.IMAGES[148] = ImageIO.read(new File("Resources/UI/Num/hud4.png"));
			Constants.IMAGES[149] = ImageIO.read(new File("Resources/UI/Num/hud5.png"));
			Constants.IMAGES[150] = ImageIO.read(new File("Resources/UI/Num/hud6.png"));
			Constants.IMAGES[151] = ImageIO.read(new File("Resources/UI/Num/hud7.png"));
			Constants.IMAGES[152] = ImageIO.read(new File("Resources/UI/Num/hud8.png"));
			Constants.IMAGES[153] = ImageIO.read(new File("Resources/UI/Num/hud9.png"));

			Constants.IMAGES[154] = ImageIO.read(new File("Resources/Textures/Blocks/Collision/collision_full.png"));
			Constants.IMAGES[155] = ImageIO.read(new File("Resources/Textures/Blocks/Collision/collision_half.png"));

			Constants.IMAGES[156] = ImageIO.read(new File("Resources/UI/Tools/pencil.png"));
			Constants.IMAGES[157] = ImageIO.read(new File("Resources/UI/Tools/line.png"));
			Constants.IMAGES[158] = ImageIO.read(new File("Resources/UI/Tools/color_fill.png"));
			Constants.IMAGES[159] = ImageIO.read(new File("Resources/UI/Tools/selection3.png"));
			Constants.IMAGES[160] = ImageIO.read(new File("Resources/UI/Tools/selection.png"));
			Constants.IMAGES[161] = ImageIO.read(new File("Resources/UI/Tools/copy.png"));
			Constants.IMAGES[162] = ImageIO.read(new File("Resources/UI/Tools/cut.png"));
			Constants.IMAGES[163] = ImageIO.read(new File("Resources/UI/Tools/paste.png"));
			Constants.IMAGES[164] = ImageIO.read(new File("Resources/UI/Tools/delete.png"));

			Constants.IMAGES[165] = ImageIO.read(new File("Resources/UI/Tools/tips.png"));

			Constants.IMAGES[166] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/1.png"));
			Constants.IMAGES[167] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/2.png"));
			Constants.IMAGES[168] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/3.png"));
			Constants.IMAGES[169] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/4.png"));
			Constants.IMAGES[170] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/5.png"));
			Constants.IMAGES[171] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/6.png"));
			Constants.IMAGES[172] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/7.png"));
			Constants.IMAGES[173] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/8.png"));
			Constants.IMAGES[174] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/9.png"));
			Constants.IMAGES[175] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/10.png"));
			Constants.IMAGES[176] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/11.png"));
			Constants.IMAGES[177] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/12.png"));
			Constants.IMAGES[178] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/13.png"));
			Constants.IMAGES[179] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/14.png"));
			Constants.IMAGES[180] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/15.png"));
			Constants.IMAGES[181] = ImageIO.read(new File("Resources/Textures/Tiles/Desert/16.png"));

			Constants.IMAGES[182] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/1.png"));
			Constants.IMAGES[183] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/2.png"));
			Constants.IMAGES[184] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/3.png"));
			Constants.IMAGES[185] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/4.png"));
			Constants.IMAGES[186] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/5.png"));
			Constants.IMAGES[187] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/6.png"));
			Constants.IMAGES[188] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/7.png"));
			Constants.IMAGES[189] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/8.png"));
			Constants.IMAGES[190] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/9.png"));
			Constants.IMAGES[191] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/10.png"));
			Constants.IMAGES[192] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/11.png"));
			Constants.IMAGES[193] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/12.png"));
			Constants.IMAGES[194] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/13.png"));
			Constants.IMAGES[195] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/14.png"));
			Constants.IMAGES[196] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/15.png"));
			Constants.IMAGES[197] = ImageIO.read(new File("Resources/Textures/Tiles/Garden/16.png"));

			Constants.IMAGES[198] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/1.png"));
			Constants.IMAGES[199] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/2.png"));
			Constants.IMAGES[200] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/3.png"));
			Constants.IMAGES[201] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/4.png"));
			Constants.IMAGES[202] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/5.png"));
			Constants.IMAGES[203] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/6.png"));
			Constants.IMAGES[204] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/7.png"));
			Constants.IMAGES[205] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/8.png"));
			Constants.IMAGES[206] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/9.png"));
			Constants.IMAGES[207] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/10.png"));
			Constants.IMAGES[208] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/11.png"));
			Constants.IMAGES[209] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/12.png"));
			Constants.IMAGES[210] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/13.png"));
			Constants.IMAGES[211] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/14.png"));
			Constants.IMAGES[212] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/15.png"));
			Constants.IMAGES[213] = ImageIO.read(new File("Resources/Textures/Tiles/Graveyard/16.png"));

			Constants.IMAGES[214] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/1.png"));
			Constants.IMAGES[215] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/2.png"));
			Constants.IMAGES[216] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/3.png"));
			Constants.IMAGES[217] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/4.png"));
			Constants.IMAGES[218] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/5.png"));
			Constants.IMAGES[219] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/6.png"));
			Constants.IMAGES[220] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/7.png"));
			Constants.IMAGES[221] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/8.png"));
			Constants.IMAGES[222] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/9.png"));
			Constants.IMAGES[223] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/10.png"));
			Constants.IMAGES[224] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/11.png"));
			Constants.IMAGES[225] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/12.png"));
			Constants.IMAGES[226] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/13.png"));
			Constants.IMAGES[227] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/14.png"));
			Constants.IMAGES[228] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/15.png"));
			Constants.IMAGES[229] = ImageIO.read(new File("Resources/Textures/Tiles/Winter/16.png"));

			Constants.IMAGES[230] = ImageIO.read(new File("Resources/Textures/Backgrounds/BG_desert.png"));
			Constants.IMAGES[231] = ImageIO.read(new File("Resources/Textures/Backgrounds/BG_garden.png"));
			Constants.IMAGES[232] = ImageIO.read(new File("Resources/Textures/Backgrounds/BG_grave.png"));
			Constants.IMAGES[233] = ImageIO.read(new File("Resources/Textures/Backgrounds/BG_winter.png"));
			Constants.IMAGES[234] = ImageIO.read(new File("Resources/Textures/Backgrounds/BG_menu.png"));

			Constants.IMAGES[235] = ImageIO.read(new File("Resources/Textures/Anim/Shine/shine01.png"));
			Constants.IMAGES[236] = ImageIO.read(new File("Resources/Textures/Anim/Shine/shine02.png"));
			Constants.IMAGES[237] = ImageIO.read(new File("Resources/Textures/Anim/Shine/shine03.png"));
			Constants.IMAGES[238] = ImageIO.read(new File("Resources/Textures/Anim/Shine/shine04.png"));
			Constants.IMAGES[239] = ImageIO.read(new File("Resources/Textures/Anim/Shine/shine05.png"));
			Constants.IMAGES[240] = ImageIO.read(new File("Resources/Textures/Anim/Shine/shine06.png"));
			Constants.IMAGES[241] = ImageIO.read(new File("Resources/Textures/Anim/Shine/shine07.png"));
			Constants.IMAGES[242] = ImageIO.read(new File("Resources/Textures/Anim/Shine/shine08.png"));

			Constants.IMAGES[243] = ImageIO.read(new File("Resources/Textures/Decor/bush_01_1.png"));
			Constants.IMAGES[244] = ImageIO.read(new File("Resources/Textures/Decor/bush_01_2.png"));
			Constants.IMAGES[245] = ImageIO.read(new File("Resources/Textures/Decor/bush_02.png"));
			Constants.IMAGES[246] = ImageIO.read(new File("Resources/Textures/Decor/bush_03.png"));
			Constants.IMAGES[247] = ImageIO.read(new File("Resources/Textures/Decor/bush_04_1.png"));
			Constants.IMAGES[248] = ImageIO.read(new File("Resources/Textures/Decor/bush_04_2.png"));
			Constants.IMAGES[249] = ImageIO.read(new File("Resources/Textures/Decor/bush_05_1.png"));
			Constants.IMAGES[250] = ImageIO.read(new File("Resources/Textures/Decor/bush_05_2.png"));
			Constants.IMAGES[251] = ImageIO.read(new File("Resources/Textures/Decor/bush_06.png"));
			Constants.IMAGES[252] = ImageIO.read(new File("Resources/Textures/Decor/bush_07.png"));
			Constants.IMAGES[253] = ImageIO.read(new File("Resources/Textures/Decor/bush_08_1.png"));
			Constants.IMAGES[254] = ImageIO.read(new File("Resources/Textures/Decor/bush_08_2.png"));
			Constants.IMAGES[255] = ImageIO.read(new File("Resources/Textures/Decor/bush_09.png"));
			Constants.IMAGES[256] = ImageIO.read(new File("Resources/Textures/Decor/bush_10_1.png"));
			Constants.IMAGES[257] = ImageIO.read(new File("Resources/Textures/Decor/bush_10_2.png"));
			Constants.IMAGES[258] = ImageIO.read(new File("Resources/Textures/Decor/bush_11_1.png"));
			Constants.IMAGES[259] = ImageIO.read(new File("Resources/Textures/Decor/bush_11_2.png"));
			Constants.IMAGES[260] = ImageIO.read(new File("Resources/Textures/Decor/bush_12_1.png"));
			Constants.IMAGES[261] = ImageIO.read(new File("Resources/Textures/Decor/bush_12_2.png"));
			Constants.IMAGES[262] = ImageIO.read(new File("Resources/Textures/Decor/bush_13_1.png"));
			Constants.IMAGES[263] = ImageIO.read(new File("Resources/Textures/Decor/bush_13_2.png"));
			Constants.IMAGES[264] = ImageIO.read(new File("Resources/Textures/Decor/bush_14_1.png"));
			Constants.IMAGES[265] = ImageIO.read(new File("Resources/Textures/Decor/bush_14_2.png"));
			Constants.IMAGES[266] = ImageIO.read(new File("Resources/Textures/Decor/bush_15_1.png"));
			Constants.IMAGES[267] = ImageIO.read(new File("Resources/Textures/Decor/bush_15_2.png"));
			Constants.IMAGES[268] = ImageIO.read(new File("Resources/Textures/Decor/bush_16.png"));
			Constants.IMAGES[269] = ImageIO.read(new File("Resources/Textures/Decor/bush_17.png"));
			Constants.IMAGES[270] = ImageIO.read(new File("Resources/Textures/Decor/bush_18.png"));

			Constants.IMAGES[271] = ImageIO.read(new File("Resources/Textures/Decor/cacti_01_1.png"));
			Constants.IMAGES[272] = ImageIO.read(new File("Resources/Textures/Decor/cacti_01_2.png"));
			Constants.IMAGES[273] = ImageIO.read(new File("Resources/Textures/Decor/cacti_01_3.png"));
			Constants.IMAGES[274] = ImageIO.read(new File("Resources/Textures/Decor/cacti_01_4.png"));
			Constants.IMAGES[275] = ImageIO.read(new File("Resources/Textures/Decor/cacti_02.png"));

			Constants.IMAGES[276] = ImageIO.read(new File("Resources/Textures/Decor/fence_01.png"));
			Constants.IMAGES[277] = ImageIO.read(new File("Resources/Textures/Decor/fence_02.png"));
			Constants.IMAGES[278] = ImageIO.read(new File("Resources/Textures/Decor/fence_03.png"));

			Constants.IMAGES[279] = ImageIO.read(new File("Resources/Textures/Decor/mushroom_1.png"));
			Constants.IMAGES[280] = ImageIO.read(new File("Resources/Textures/Decor/mushroom_2.png"));
			Constants.IMAGES[281] = ImageIO.read(new File("Resources/Textures/Decor/mushroom_3.png"));
			Constants.IMAGES[282] = ImageIO.read(new File("Resources/Textures/Decor/mushroom_4.png"));

			Constants.IMAGES[283] = ImageIO.read(new File("Resources/Textures/Decor/sign_01.png"));
			Constants.IMAGES[284] = ImageIO.read(new File("Resources/Textures/Decor/sign_02.png"));
			Constants.IMAGES[285] = ImageIO.read(new File("Resources/Textures/Decor/sign_03.png"));
			Constants.IMAGES[286] = ImageIO.read(new File("Resources/Textures/Decor/sign_04.png"));

			Constants.IMAGES[287] = ImageIO.read(new File("Resources/Textures/Decor/spooky_01.png"));
			Constants.IMAGES[288] = ImageIO.read(new File("Resources/Textures/Decor/spooky_02.png"));
			Constants.IMAGES[289] = ImageIO.read(new File("Resources/Textures/Decor/spooky_03.png"));

			Constants.IMAGES[290] = ImageIO.read(new File("Resources/Textures/Decor/tree_01_1.png"));
			Constants.IMAGES[291] = ImageIO.read(new File("Resources/Textures/Decor/tree_01_2.png"));
			Constants.IMAGES[292] = ImageIO.read(new File("Resources/Textures/Decor/tree_01_3.png"));
			Constants.IMAGES[293] = ImageIO.read(new File("Resources/Textures/Decor/tree_01_4.png"));
			Constants.IMAGES[294] = ImageIO.read(new File("Resources/Textures/Decor/tree_02_1.png"));
			Constants.IMAGES[295] = ImageIO.read(new File("Resources/Textures/Decor/tree_02_2.png"));
			Constants.IMAGES[296] = ImageIO.read(new File("Resources/Textures/Decor/tree_03_1.png"));
			Constants.IMAGES[297] = ImageIO.read(new File("Resources/Textures/Decor/tree_03_2.png"));
			Constants.IMAGES[298] = ImageIO.read(new File("Resources/Textures/Decor/tree_03_3.png"));
			Constants.IMAGES[299] = ImageIO.read(new File("Resources/Textures/Decor/tree_03_4.png"));
			Constants.IMAGES[300] = ImageIO.read(new File("Resources/Textures/Decor/tree_04_1.png"));
			Constants.IMAGES[301] = ImageIO.read(new File("Resources/Textures/Decor/tree_04_2.png"));
			Constants.IMAGES[302] = ImageIO.read(new File("Resources/Textures/Decor/tree_04_3.png"));
			Constants.IMAGES[303] = ImageIO.read(new File("Resources/Textures/Decor/tree_04_4.png"));
			Constants.IMAGES[304] = ImageIO.read(new File("Resources/Textures/Decor/tree_05_1.png"));
			Constants.IMAGES[305] = ImageIO.read(new File("Resources/Textures/Decor/tree_05_2.png"));
			Constants.IMAGES[306] = ImageIO.read(new File("Resources/Textures/Decor/tree_05_3.png"));
			Constants.IMAGES[307] = ImageIO.read(new File("Resources/Textures/Decor/tree_05_4.png"));
			Constants.IMAGES[308] = ImageIO.read(new File("Resources/Textures/Decor/tree_06_1.png"));
			Constants.IMAGES[309] = ImageIO.read(new File("Resources/Textures/Decor/tree_06_2.png"));
			Constants.IMAGES[310] = ImageIO.read(new File("Resources/Textures/Decor/tree_06_3.png"));
			Constants.IMAGES[311] = ImageIO.read(new File("Resources/Textures/Decor/tree_06_4.png"));
			Constants.IMAGES[312] = ImageIO.read(new File("Resources/Textures/Decor/tree_07_1.png"));
			Constants.IMAGES[313] = ImageIO.read(new File("Resources/Textures/Decor/tree_07_2.png"));
			Constants.IMAGES[314] = ImageIO.read(new File("Resources/Textures/Decor/tree_07_3.png"));
			Constants.IMAGES[315] = ImageIO.read(new File("Resources/Textures/Decor/tree_07_4.png"));
			Constants.IMAGES[316] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_1.png"));
			Constants.IMAGES[317] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_2.png"));
			Constants.IMAGES[318] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_3.png"));
			Constants.IMAGES[319] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_4.png"));
			Constants.IMAGES[320] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_5.png"));
			Constants.IMAGES[321] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_6.png"));
			Constants.IMAGES[322] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_7.png"));
			Constants.IMAGES[323] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_8.png"));
			Constants.IMAGES[324] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_9.png"));
			Constants.IMAGES[325] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_10.png"));
			Constants.IMAGES[326] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_11.png"));
			Constants.IMAGES[327] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_12.png"));
			Constants.IMAGES[328] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_13.png"));
			Constants.IMAGES[329] = ImageIO.read(new File("Resources/Textures/Decor/tree_08_14.png"));
			Constants.IMAGES[330] = ImageIO.read(new File("Resources/Textures/Decor/tree_09_1.png"));
			Constants.IMAGES[331] = ImageIO.read(new File("Resources/Textures/Decor/tree_09_2.png"));
			Constants.IMAGES[332] = ImageIO.read(new File("Resources/Textures/Decor/tree_09_3.png"));
			Constants.IMAGES[333] = ImageIO.read(new File("Resources/Textures/Decor/tree_09_4.png"));

			Constants.IMAGES[344] = ImageIO.read(new File("Resources/Textures/Units/Coin/Bronze/bronze_1.png"));
			Constants.IMAGES[345] = ImageIO.read(new File("Resources/Textures/Units/Coin/Bronze/bronze_2.png"));
			Constants.IMAGES[346] = ImageIO.read(new File("Resources/Textures/Units/Coin/Bronze/bronze_3.png"));
			Constants.IMAGES[347] = ImageIO.read(new File("Resources/Textures/Units/Coin/Bronze/bronze_4.png"));
			Constants.IMAGES[348] = ImageIO.read(new File("Resources/Textures/Units/Coin/Bronze/bronze_5.png"));
			Constants.IMAGES[349] = ImageIO.read(new File("Resources/Textures/Units/Coin/Bronze/bronze_6.png"));
			Constants.IMAGES[350] = ImageIO.read(new File("Resources/Textures/Units/Coin/Bronze/bronze_1.png"));

			Constants.IMAGES[351] = ImageIO.read(new File("Resources/Textures/Units/Coin/Silver/silver_1.png"));
			Constants.IMAGES[352] = ImageIO.read(new File("Resources/Textures/Units/Coin/Silver/silver_2.png"));
			Constants.IMAGES[353] = ImageIO.read(new File("Resources/Textures/Units/Coin/Silver/silver_3.png"));
			Constants.IMAGES[354] = ImageIO.read(new File("Resources/Textures/Units/Coin/Silver/silver_4.png"));
			Constants.IMAGES[355] = ImageIO.read(new File("Resources/Textures/Units/Coin/Silver/silver_5.png"));
			Constants.IMAGES[356] = ImageIO.read(new File("Resources/Textures/Units/Coin/Silver/silver_6.png"));
			Constants.IMAGES[357] = ImageIO.read(new File("Resources/Textures/Units/Coin/Silver/silver_1.png"));

			Constants.IMAGES[358] = ImageIO.read(new File("Resources/Textures/Units/Coin/Gold/gold_1.png"));
			Constants.IMAGES[359] = ImageIO.read(new File("Resources/Textures/Units/Coin/Gold/gold_2.png"));
			Constants.IMAGES[360] = ImageIO.read(new File("Resources/Textures/Units/Coin/Gold/gold_3.png"));
			Constants.IMAGES[361] = ImageIO.read(new File("Resources/Textures/Units/Coin/Gold/gold_4.png"));
			Constants.IMAGES[362] = ImageIO.read(new File("Resources/Textures/Units/Coin/Gold/gold_5.png"));
			Constants.IMAGES[363] = ImageIO.read(new File("Resources/Textures/Units/Coin/Gold/gold_6.png"));
			Constants.IMAGES[364] = ImageIO.read(new File("Resources/Textures/Units/Coin/Gold/gold_1.png"));

			Constants.IMAGES[365] = ImageIO.read(new File("Resources/Textures/Units/Gate/gateOff.png"));

			Constants.IMAGES[366] = ImageIO.read(new File("Resources/Textures/Units/Spring/springDown.png"));
			Constants.IMAGES[367] = ImageIO.read(new File("Resources/Textures/Units/Spring/sprungLeft.png"));
			Constants.IMAGES[368] = ImageIO.read(new File("Resources/Textures/Units/Spring/springRight.png"));
			Constants.IMAGES[369] = ImageIO.read(new File("Resources/Textures/Units/Spring/sprungUp.png"));

			Constants.IMAGES[370] = ImageIO.read(new File("Resources/Textures/Units/Laser/laserDownShoot.png"));
			Constants.IMAGES[371] = ImageIO.read(new File("Resources/Textures/Units/Laser/laserLeftShoot.png"));
			Constants.IMAGES[372] = ImageIO.read(new File("Resources/Textures/Units/Laser/laserRightShoot.png"));
			Constants.IMAGES[373] = ImageIO.read(new File("Resources/Textures/Units/Laser/laserUpShoot.png"));

			Constants.IMAGES[374] = ImageIO.read(new File("Resources/Textures/Units/Fire/01.png"));
			Constants.IMAGES[375] = ImageIO.read(new File("Resources/Textures/Units/Fire/02.png"));
			Constants.IMAGES[376] = ImageIO.read(new File("Resources/Textures/Units/Fire/03.png"));
			Constants.IMAGES[377] = ImageIO.read(new File("Resources/Textures/Units/Fire/04.png"));
			Constants.IMAGES[378] = ImageIO.read(new File("Resources/Textures/Units/Fire/05.png"));
			Constants.IMAGES[379] = ImageIO.read(new File("Resources/Textures/Units/Fire/06.png"));
			Constants.IMAGES[380] = ImageIO.read(new File("Resources/Textures/Units/Fire/07.png"));
			Constants.IMAGES[381] = ImageIO.read(new File("Resources/Textures/Units/Fire/08.png"));

			Constants.IMAGES[382] = ImageIO.read(new File("Resources/Textures/Units/Volcano/fireball_up.png"));
			Constants.IMAGES[383] = ImageIO.read(new File("Resources/Textures/Units/Volcano/fireball_down.png"));

			Constants.IMAGES[384] = ImageIO.read(new File("Resources/Textures/Units/Laser/laserGreenVertical.png"));
			Constants.IMAGES[385] = ImageIO.read(new File("Resources/Textures/Units/Laser/laserGreenHorizontal.png"));

			Constants.IMAGES[386] = ImageIO.read(new File("Resources/Textures/Units/Kunai/Kunai.png"));
			Constants.IMAGES[387] = ImageIO.read(new File("Resources/Textures/Units/Kunai/Kunai_reverse.png"));

			Constants.IMAGES[388] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagBlue.png"));
			Constants.IMAGES[389] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagBlue2.png"));
			Constants.IMAGES[390] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagBlueHanging.png"));
			Constants.IMAGES[391] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagGreen.png"));
			Constants.IMAGES[392] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagGreen2.png"));
			Constants.IMAGES[393] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagGreenHanging.png"));
			Constants.IMAGES[394] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagRed.png"));
			Constants.IMAGES[395] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagRed2.png"));
			Constants.IMAGES[396] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagRedHanging.png"));
			Constants.IMAGES[397] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagYellow.png"));
			Constants.IMAGES[398] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagYellow2.png"));
			Constants.IMAGES[399] = ImageIO.read(new File("Resources/Textures/Units/Flag/flagYellowHanging.png"));

			Constants.IMAGES[400] = ImageIO.read(new File("Resources/Textures/Tiles/castleCenter.png"));

			Constants.IMAGES[401] = ImageIO.read(new File("Resources/Textures/Units/Player/Climb/Climb_000.png"));
			Constants.IMAGES[402] = ImageIO.read(new File("Resources/Textures/Units/Player/Climb/Climb_001.png"));
			Constants.IMAGES[403] = ImageIO.read(new File("Resources/Textures/Units/Player/Climb/Climb_002.png"));
			Constants.IMAGES[404] = ImageIO.read(new File("Resources/Textures/Units/Player/Climb/Climb_003.png"));
			Constants.IMAGES[405] = ImageIO.read(new File("Resources/Textures/Units/Player/Climb/Climb_004.png"));
			Constants.IMAGES[406] = ImageIO.read(new File("Resources/Textures/Units/Player/Climb/Climb_005.png"));
			Constants.IMAGES[407] = ImageIO.read(new File("Resources/Textures/Units/Player/Climb/Climb_006.png"));
			Constants.IMAGES[408] = ImageIO.read(new File("Resources/Textures/Units/Player/Climb/Climb_007.png"));
			Constants.IMAGES[409] = ImageIO.read(new File("Resources/Textures/Units/Player/Climb/Climb_008.png"));
			Constants.IMAGES[410] = ImageIO.read(new File("Resources/Textures/Units/Player/Climb/Climb_009.png"));

			Constants.IMAGES[411] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideLeft/Glidel_000.png"));
			Constants.IMAGES[412] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideLeft/Glidel_001.png"));
			Constants.IMAGES[413] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideLeft/Glidel_002.png"));
			Constants.IMAGES[414] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideLeft/Glidel_003.png"));
			Constants.IMAGES[415] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideLeft/Glidel_004.png"));
			Constants.IMAGES[416] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideLeft/Glidel_005.png"));
			Constants.IMAGES[417] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideLeft/Glidel_006.png"));
			Constants.IMAGES[418] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideLeft/Glidel_007.png"));
			Constants.IMAGES[419] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideLeft/Glidel_008.png"));
			Constants.IMAGES[420] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideLeft/Glidel_009.png"));

			Constants.IMAGES[421] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideRight/Glide_000.png"));
			Constants.IMAGES[422] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideRight/Glide_001.png"));
			Constants.IMAGES[423] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideRight/Glide_002.png"));
			Constants.IMAGES[424] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideRight/Glide_003.png"));
			Constants.IMAGES[425] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideRight/Glide_004.png"));
			Constants.IMAGES[426] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideRight/Glide_005.png"));
			Constants.IMAGES[427] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideRight/Glide_006.png"));
			Constants.IMAGES[428] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideRight/Glide_007.png"));
			Constants.IMAGES[429] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideRight/Glide_008.png"));
			Constants.IMAGES[430] = ImageIO.read(new File("Resources/Textures/Units/Player/GlideRight/Glide_009.png"));

			Constants.IMAGES[431] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleLeft/Idlel__000.png"));
			Constants.IMAGES[432] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleLeft/Idlel__001.png"));
			Constants.IMAGES[433] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleLeft/Idlel__002.png"));
			Constants.IMAGES[434] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleLeft/Idlel__003.png"));
			Constants.IMAGES[435] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleLeft/Idlel__004.png"));
			Constants.IMAGES[436] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleLeft/Idlel__005.png"));
			Constants.IMAGES[437] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleLeft/Idlel__006.png"));
			Constants.IMAGES[438] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleLeft/Idlel__007.png"));
			Constants.IMAGES[439] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleLeft/Idlel__008.png"));
			Constants.IMAGES[440] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleLeft/Idlel__009.png"));

			Constants.IMAGES[441] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleRight/Idle__000.png"));
			Constants.IMAGES[442] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleRight/Idle__001.png"));
			Constants.IMAGES[443] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleRight/Idle__002.png"));
			Constants.IMAGES[444] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleRight/Idle__003.png"));
			Constants.IMAGES[445] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleRight/Idle__004.png"));
			Constants.IMAGES[446] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleRight/Idle__005.png"));
			Constants.IMAGES[447] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleRight/Idle__006.png"));
			Constants.IMAGES[448] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleRight/Idle__007.png"));
			Constants.IMAGES[449] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleRight/Idle__008.png"));
			Constants.IMAGES[450] = ImageIO.read(new File("Resources/Textures/Units/Player/IdleRight/Idle__009.png"));

			Constants.IMAGES[451] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpLeft/Jumpl__000.png"));
			Constants.IMAGES[452] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpLeft/Jumpl__001.png"));
			Constants.IMAGES[453] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpLeft/Jumpl__002.png"));
			Constants.IMAGES[454] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpLeft/Jumpl__003.png"));
			Constants.IMAGES[455] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpLeft/Jumpl__004.png"));
			Constants.IMAGES[456] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpLeft/Jumpl__005.png"));
			Constants.IMAGES[457] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpLeft/Jumpl__006.png"));
			Constants.IMAGES[458] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpLeft/Jumpl__007.png"));
			Constants.IMAGES[459] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpLeft/Jumpl__008.png"));
			Constants.IMAGES[460] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpLeft/Jumpl__009.png"));

			Constants.IMAGES[461] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpRight/Jump__000.png"));
			Constants.IMAGES[462] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpRight/Jump__001.png"));
			Constants.IMAGES[463] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpRight/Jump__002.png"));
			Constants.IMAGES[464] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpRight/Jump__003.png"));
			Constants.IMAGES[465] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpRight/Jump__004.png"));
			Constants.IMAGES[466] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpRight/Jump__005.png"));
			Constants.IMAGES[467] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpRight/Jump__006.png"));
			Constants.IMAGES[468] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpRight/Jump__007.png"));
			Constants.IMAGES[469] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpRight/Jump__008.png"));
			Constants.IMAGES[470] = ImageIO.read(new File("Resources/Textures/Units/Player/JumpRight/Jump__009.png"));

			Constants.IMAGES[471] = ImageIO.read(new File("Resources/Textures/Units/Player/RunLeft/Runl__000.png"));
			Constants.IMAGES[472] = ImageIO.read(new File("Resources/Textures/Units/Player/RunLeft/Runl__001.png"));
			Constants.IMAGES[473] = ImageIO.read(new File("Resources/Textures/Units/Player/RunLeft/Runl__002.png"));
			Constants.IMAGES[474] = ImageIO.read(new File("Resources/Textures/Units/Player/RunLeft/Runl__003.png"));
			Constants.IMAGES[475] = ImageIO.read(new File("Resources/Textures/Units/Player/RunLeft/Runl__004.png"));
			Constants.IMAGES[476] = ImageIO.read(new File("Resources/Textures/Units/Player/RunLeft/Runl__005.png"));
			Constants.IMAGES[477] = ImageIO.read(new File("Resources/Textures/Units/Player/RunLeft/Runl__006.png"));
			Constants.IMAGES[478] = ImageIO.read(new File("Resources/Textures/Units/Player/RunLeft/Runl__007.png"));
			Constants.IMAGES[479] = ImageIO.read(new File("Resources/Textures/Units/Player/RunLeft/Runl__008.png"));
			Constants.IMAGES[480] = ImageIO.read(new File("Resources/Textures/Units/Player/RunLeft/Runl__009.png"));

			Constants.IMAGES[481] = ImageIO.read(new File("Resources/Textures/Units/Player/RunRight/Run__000.png"));
			Constants.IMAGES[482] = ImageIO.read(new File("Resources/Textures/Units/Player/RunRight/Run__001.png"));
			Constants.IMAGES[483] = ImageIO.read(new File("Resources/Textures/Units/Player/RunRight/Run__002.png"));
			Constants.IMAGES[484] = ImageIO.read(new File("Resources/Textures/Units/Player/RunRight/Run__003.png"));
			Constants.IMAGES[485] = ImageIO.read(new File("Resources/Textures/Units/Player/RunRight/Run__004.png"));
			Constants.IMAGES[486] = ImageIO.read(new File("Resources/Textures/Units/Player/RunRight/Run__005.png"));
			Constants.IMAGES[487] = ImageIO.read(new File("Resources/Textures/Units/Player/RunRight/Run__006.png"));
			Constants.IMAGES[488] = ImageIO.read(new File("Resources/Textures/Units/Player/RunRight/Run__007.png"));
			Constants.IMAGES[489] = ImageIO.read(new File("Resources/Textures/Units/Player/RunRight/Run__008.png"));
			Constants.IMAGES[490] = ImageIO.read(new File("Resources/Textures/Units/Player/RunRight/Run__009.png"));

			Constants.IMAGES[491] = ImageIO.read(new File("Resources/Textures/Anim/Explosion/01.png"));
			Constants.IMAGES[492] = ImageIO.read(new File("Resources/Textures/Anim/Explosion/02.png"));
			Constants.IMAGES[493] = ImageIO.read(new File("Resources/Textures/Anim/Explosion/03.png"));
			Constants.IMAGES[494] = ImageIO.read(new File("Resources/Textures/Anim/Explosion/04.png"));
			Constants.IMAGES[495] = ImageIO.read(new File("Resources/Textures/Anim/Explosion/05.png"));
			Constants.IMAGES[496] = ImageIO.read(new File("Resources/Textures/Anim/Explosion/06.png"));
			Constants.IMAGES[497] = ImageIO.read(new File("Resources/Textures/Anim/Explosion/07.png"));
			Constants.IMAGES[498] = ImageIO.read(new File("Resources/Textures/Anim/Explosion/08.png"));

			Constants.IMAGES[499] = ImageIO.read(new File("Resources/Textures/Units/Fly/fly_r_1.png"));
			Constants.IMAGES[500] = ImageIO.read(new File("Resources/Textures/Units/Fly/fly_r_2.png"));
			Constants.IMAGES[501] = ImageIO.read(new File("Resources/Textures/Units/Fly/fly_r_3.png"));
			Constants.IMAGES[502] = ImageIO.read(new File("Resources/Textures/Units/Fly/fly_l_1.png"));
			Constants.IMAGES[503] = ImageIO.read(new File("Resources/Textures/Units/Fly/fly_l_2.png"));
			Constants.IMAGES[504] = ImageIO.read(new File("Resources/Textures/Units/Fly/fly_l_3.png"));

			Constants.IMAGES[505] = ImageIO.read(new File("Resources/Textures/Units/Spinner/ver_2.png"));

			Constants.IMAGES[506] = ImageIO.read(new File("Resources/Textures/Units/Ghost/ghost_l_1.png"));
			Constants.IMAGES[507] = ImageIO.read(new File("Resources/Textures/Units/Ghost/ghost_l_2.png"));
			Constants.IMAGES[508] = ImageIO.read(new File("Resources/Textures/Units/Ghost/ghost_r_1.png"));
			Constants.IMAGES[509] = ImageIO.read(new File("Resources/Textures/Units/Ghost/ghost_r_2.png"));
			Constants.IMAGES[510] = ImageIO.read(new File("Resources/Textures/Units/Ghost/ghost.png"));

			Constants.IMAGES[511] = ImageIO.read(new File("Resources/Textures/Units/Robot/Climb/Climb_000.png"));
			Constants.IMAGES[512] = ImageIO.read(new File("Resources/Textures/Units/Robot/Climb/Climb_001.png"));
			Constants.IMAGES[513] = ImageIO.read(new File("Resources/Textures/Units/Robot/Climb/Climb_002.png"));
			Constants.IMAGES[514] = ImageIO.read(new File("Resources/Textures/Units/Robot/Climb/Climb_003.png"));
			Constants.IMAGES[515] = ImageIO.read(new File("Resources/Textures/Units/Robot/Climb/Climb_004.png"));
			Constants.IMAGES[516] = ImageIO.read(new File("Resources/Textures/Units/Robot/Climb/Climb_005.png"));
			Constants.IMAGES[517] = ImageIO.read(new File("Resources/Textures/Units/Robot/Climb/Climb_006.png"));
			Constants.IMAGES[518] = ImageIO.read(new File("Resources/Textures/Units/Robot/Climb/Climb_007.png"));
			Constants.IMAGES[519] = ImageIO.read(new File("Resources/Textures/Units/Robot/Climb/Climb_008.png"));
			Constants.IMAGES[520] = ImageIO.read(new File("Resources/Textures/Units/Robot/Climb/Climb_009.png"));

			Constants.IMAGES[521] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideLeft/Glidel_000.png"));
			Constants.IMAGES[522] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideLeft/Glidel_001.png"));
			Constants.IMAGES[523] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideLeft/Glidel_002.png"));
			Constants.IMAGES[524] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideLeft/Glidel_003.png"));
			Constants.IMAGES[525] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideLeft/Glidel_004.png"));
			Constants.IMAGES[526] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideLeft/Glidel_005.png"));
			Constants.IMAGES[527] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideLeft/Glidel_006.png"));
			Constants.IMAGES[528] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideLeft/Glidel_007.png"));
			Constants.IMAGES[529] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideLeft/Glidel_008.png"));
			Constants.IMAGES[530] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideLeft/Glidel_009.png"));

			Constants.IMAGES[531] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideRight/Glide_000.png"));
			Constants.IMAGES[532] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideRight/Glide_001.png"));
			Constants.IMAGES[533] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideRight/Glide_002.png"));
			Constants.IMAGES[534] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideRight/Glide_003.png"));
			Constants.IMAGES[535] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideRight/Glide_004.png"));
			Constants.IMAGES[536] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideRight/Glide_005.png"));
			Constants.IMAGES[537] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideRight/Glide_006.png"));
			Constants.IMAGES[538] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideRight/Glide_007.png"));
			Constants.IMAGES[539] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideRight/Glide_008.png"));
			Constants.IMAGES[540] = ImageIO.read(new File("Resources/Textures/Units/Robot/GlideRight/Glide_009.png"));

			Constants.IMAGES[541] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleLeft/Idlel__000.png"));
			Constants.IMAGES[542] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleLeft/Idlel__001.png"));
			Constants.IMAGES[543] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleLeft/Idlel__002.png"));
			Constants.IMAGES[544] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleLeft/Idlel__003.png"));
			Constants.IMAGES[545] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleLeft/Idlel__004.png"));
			Constants.IMAGES[546] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleLeft/Idlel__005.png"));
			Constants.IMAGES[547] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleLeft/Idlel__006.png"));
			Constants.IMAGES[548] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleLeft/Idlel__007.png"));
			Constants.IMAGES[549] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleLeft/Idlel__008.png"));
			Constants.IMAGES[550] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleLeft/Idlel__009.png"));

			Constants.IMAGES[551] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleRight/Idle__000.png"));
			Constants.IMAGES[552] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleRight/Idle__001.png"));
			Constants.IMAGES[553] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleRight/Idle__002.png"));
			Constants.IMAGES[554] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleRight/Idle__003.png"));
			Constants.IMAGES[555] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleRight/Idle__004.png"));
			Constants.IMAGES[556] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleRight/Idle__005.png"));
			Constants.IMAGES[557] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleRight/Idle__006.png"));
			Constants.IMAGES[558] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleRight/Idle__007.png"));
			Constants.IMAGES[559] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleRight/Idle__008.png"));
			Constants.IMAGES[560] = ImageIO.read(new File("Resources/Textures/Units/Robot/IdleRight/Idle__009.png"));

			Constants.IMAGES[561] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpLeft/Jumpl__000.png"));
			Constants.IMAGES[562] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpLeft/Jumpl__001.png"));
			Constants.IMAGES[563] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpLeft/Jumpl__002.png"));
			Constants.IMAGES[564] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpLeft/Jumpl__003.png"));
			Constants.IMAGES[565] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpLeft/Jumpl__004.png"));
			Constants.IMAGES[566] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpLeft/Jumpl__005.png"));
			Constants.IMAGES[567] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpLeft/Jumpl__006.png"));
			Constants.IMAGES[568] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpLeft/Jumpl__007.png"));
			Constants.IMAGES[569] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpLeft/Jumpl__008.png"));
			Constants.IMAGES[570] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpLeft/Jumpl__009.png"));

			Constants.IMAGES[571] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpRight/Jump__000.png"));
			Constants.IMAGES[572] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpRight/Jump__001.png"));
			Constants.IMAGES[573] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpRight/Jump__002.png"));
			Constants.IMAGES[574] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpRight/Jump__003.png"));
			Constants.IMAGES[575] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpRight/Jump__004.png"));
			Constants.IMAGES[576] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpRight/Jump__005.png"));
			Constants.IMAGES[577] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpRight/Jump__006.png"));
			Constants.IMAGES[578] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpRight/Jump__007.png"));
			Constants.IMAGES[579] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpRight/Jump__008.png"));
			Constants.IMAGES[580] = ImageIO.read(new File("Resources/Textures/Units/Robot/JumpRight/Jump__009.png"));

			Constants.IMAGES[581] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunLeft/Runl__000.png"));
			Constants.IMAGES[582] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunLeft/Runl__001.png"));
			Constants.IMAGES[583] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunLeft/Runl__002.png"));
			Constants.IMAGES[584] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunLeft/Runl__003.png"));
			Constants.IMAGES[585] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunLeft/Runl__004.png"));
			Constants.IMAGES[586] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunLeft/Runl__005.png"));
			Constants.IMAGES[587] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunLeft/Runl__006.png"));
			Constants.IMAGES[588] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunLeft/Runl__007.png"));
			Constants.IMAGES[589] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunLeft/Runl__008.png"));
			Constants.IMAGES[590] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunLeft/Runl__009.png"));

			Constants.IMAGES[591] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunRight/Run__000.png"));
			Constants.IMAGES[592] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunRight/Run__001.png"));
			Constants.IMAGES[593] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunRight/Run__002.png"));
			Constants.IMAGES[594] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunRight/Run__003.png"));
			Constants.IMAGES[595] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunRight/Run__004.png"));
			Constants.IMAGES[596] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunRight/Run__005.png"));
			Constants.IMAGES[597] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunRight/Run__006.png"));
			Constants.IMAGES[598] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunRight/Run__007.png"));
			Constants.IMAGES[599] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunRight/Run__008.png"));
			Constants.IMAGES[600] = ImageIO.read(new File("Resources/Textures/Units/Robot/RunRight/Run__009.png"));

			Constants.IMAGES[601] = ImageIO.read(new File("Resources/Textures/Units/Knight/Climb/Climb_000.png"));
			Constants.IMAGES[602] = ImageIO.read(new File("Resources/Textures/Units/Knight/Climb/Climb_001.png"));
			Constants.IMAGES[603] = ImageIO.read(new File("Resources/Textures/Units/Knight/Climb/Climb_002.png"));
			Constants.IMAGES[604] = ImageIO.read(new File("Resources/Textures/Units/Knight/Climb/Climb_003.png"));
			Constants.IMAGES[605] = ImageIO.read(new File("Resources/Textures/Units/Knight/Climb/Climb_004.png"));
			Constants.IMAGES[606] = ImageIO.read(new File("Resources/Textures/Units/Knight/Climb/Climb_005.png"));
			Constants.IMAGES[607] = ImageIO.read(new File("Resources/Textures/Units/Knight/Climb/Climb_006.png"));
			Constants.IMAGES[608] = ImageIO.read(new File("Resources/Textures/Units/Knight/Climb/Climb_007.png"));
			Constants.IMAGES[609] = ImageIO.read(new File("Resources/Textures/Units/Knight/Climb/Climb_008.png"));
			Constants.IMAGES[610] = ImageIO.read(new File("Resources/Textures/Units/Knight/Climb/Climb_009.png"));

			Constants.IMAGES[611] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideLeft/Glidel_000.png"));
			Constants.IMAGES[612] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideLeft/Glidel_001.png"));
			Constants.IMAGES[613] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideLeft/Glidel_002.png"));
			Constants.IMAGES[614] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideLeft/Glidel_003.png"));
			Constants.IMAGES[615] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideLeft/Glidel_004.png"));
			Constants.IMAGES[616] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideLeft/Glidel_005.png"));
			Constants.IMAGES[617] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideLeft/Glidel_006.png"));
			Constants.IMAGES[618] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideLeft/Glidel_007.png"));
			Constants.IMAGES[619] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideLeft/Glidel_008.png"));
			Constants.IMAGES[620] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideLeft/Glidel_009.png"));

			Constants.IMAGES[621] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideRight/Glide_000.png"));
			Constants.IMAGES[622] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideRight/Glide_001.png"));
			Constants.IMAGES[623] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideRight/Glide_002.png"));
			Constants.IMAGES[624] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideRight/Glide_003.png"));
			Constants.IMAGES[625] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideRight/Glide_004.png"));
			Constants.IMAGES[626] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideRight/Glide_005.png"));
			Constants.IMAGES[627] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideRight/Glide_006.png"));
			Constants.IMAGES[628] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideRight/Glide_007.png"));
			Constants.IMAGES[629] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideRight/Glide_008.png"));
			Constants.IMAGES[630] = ImageIO.read(new File("Resources/Textures/Units/Knight/GlideRight/Glide_009.png"));

			Constants.IMAGES[631] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleLeft/Idlel__000.png"));
			Constants.IMAGES[632] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleLeft/Idlel__001.png"));
			Constants.IMAGES[633] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleLeft/Idlel__002.png"));
			Constants.IMAGES[634] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleLeft/Idlel__003.png"));
			Constants.IMAGES[635] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleLeft/Idlel__004.png"));
			Constants.IMAGES[636] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleLeft/Idlel__005.png"));
			Constants.IMAGES[637] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleLeft/Idlel__006.png"));
			Constants.IMAGES[638] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleLeft/Idlel__007.png"));
			Constants.IMAGES[639] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleLeft/Idlel__008.png"));
			Constants.IMAGES[640] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleLeft/Idlel__009.png"));

			Constants.IMAGES[641] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleRight/Idle__000.png"));
			Constants.IMAGES[642] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleRight/Idle__001.png"));
			Constants.IMAGES[643] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleRight/Idle__002.png"));
			Constants.IMAGES[644] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleRight/Idle__003.png"));
			Constants.IMAGES[645] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleRight/Idle__004.png"));
			Constants.IMAGES[646] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleRight/Idle__005.png"));
			Constants.IMAGES[647] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleRight/Idle__006.png"));
			Constants.IMAGES[648] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleRight/Idle__007.png"));
			Constants.IMAGES[649] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleRight/Idle__008.png"));
			Constants.IMAGES[650] = ImageIO.read(new File("Resources/Textures/Units/Knight/IdleRight/Idle__009.png"));

			Constants.IMAGES[651] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpLeft/Jumpl__000.png"));
			Constants.IMAGES[652] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpLeft/Jumpl__001.png"));
			Constants.IMAGES[653] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpLeft/Jumpl__002.png"));
			Constants.IMAGES[654] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpLeft/Jumpl__003.png"));
			Constants.IMAGES[655] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpLeft/Jumpl__004.png"));
			Constants.IMAGES[656] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpLeft/Jumpl__005.png"));
			Constants.IMAGES[657] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpLeft/Jumpl__006.png"));
			Constants.IMAGES[658] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpLeft/Jumpl__007.png"));
			Constants.IMAGES[659] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpLeft/Jumpl__008.png"));
			Constants.IMAGES[660] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpLeft/Jumpl__009.png"));

			Constants.IMAGES[661] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpRight/Jump__000.png"));
			Constants.IMAGES[662] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpRight/Jump__001.png"));
			Constants.IMAGES[663] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpRight/Jump__002.png"));
			Constants.IMAGES[664] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpRight/Jump__003.png"));
			Constants.IMAGES[665] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpRight/Jump__004.png"));
			Constants.IMAGES[666] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpRight/Jump__005.png"));
			Constants.IMAGES[667] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpRight/Jump__006.png"));
			Constants.IMAGES[668] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpRight/Jump__007.png"));
			Constants.IMAGES[669] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpRight/Jump__008.png"));
			Constants.IMAGES[670] = ImageIO.read(new File("Resources/Textures/Units/Knight/JumpRight/Jump__009.png"));

			Constants.IMAGES[671] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunLeft/Runl__000.png"));
			Constants.IMAGES[672] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunLeft/Runl__001.png"));
			Constants.IMAGES[673] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunLeft/Runl__002.png"));
			Constants.IMAGES[674] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunLeft/Runl__003.png"));
			Constants.IMAGES[675] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunLeft/Runl__004.png"));
			Constants.IMAGES[676] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunLeft/Runl__005.png"));
			Constants.IMAGES[677] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunLeft/Runl__006.png"));
			Constants.IMAGES[678] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunLeft/Runl__007.png"));
			Constants.IMAGES[679] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunLeft/Runl__008.png"));
			Constants.IMAGES[680] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunLeft/Runl__009.png"));

			Constants.IMAGES[681] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunRight/Run__000.png"));
			Constants.IMAGES[682] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunRight/Run__001.png"));
			Constants.IMAGES[683] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunRight/Run__002.png"));
			Constants.IMAGES[684] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunRight/Run__003.png"));
			Constants.IMAGES[685] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunRight/Run__004.png"));
			Constants.IMAGES[686] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunRight/Run__005.png"));
			Constants.IMAGES[687] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunRight/Run__006.png"));
			Constants.IMAGES[688] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunRight/Run__007.png"));
			Constants.IMAGES[689] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunRight/Run__008.png"));
			Constants.IMAGES[690] = ImageIO.read(new File("Resources/Textures/Units/Knight/RunRight/Run__009.png"));

			Constants.IMAGES[691] = ImageIO.read(new File("Resources/Textures/Units/RobotFireBall/Bullet_000.png"));
			Constants.IMAGES[692] = ImageIO.read(new File("Resources/Textures/Units/RobotFireBall/Bullet_001.png"));
			Constants.IMAGES[693] = ImageIO.read(new File("Resources/Textures/Units/RobotFireBall/Bullet_002.png"));
			Constants.IMAGES[694] = ImageIO.read(new File("Resources/Textures/Units/RobotFireBall/Bullet_003.png"));
			Constants.IMAGES[695] = ImageIO.read(new File("Resources/Textures/Units/RobotFireBall/Bullet_004.png"));
			Constants.IMAGES[696] = ImageIO.read(new File("Resources/Textures/Units/RobotFireBall/Bullet_005.png"));
			Constants.IMAGES[697] = ImageIO.read(new File("Resources/Textures/Units/RobotFireBall/Bullet_006.png"));
			Constants.IMAGES[698] = ImageIO.read(new File("Resources/Textures/Units/RobotFireBall/Bullet_007.png"));
			Constants.IMAGES[699] = ImageIO.read(new File("Resources/Textures/Units/RobotFireBall/Bullet_008.png"));
			Constants.IMAGES[700] = ImageIO.read(new File("Resources/Textures/Units/RobotFireBall/Bullet_009.png"));

			
			Constants.IMAGES[701] = ImageIO.read(new File("Resources/Textures/Decor/foliage01.png"));
			Constants.IMAGES[702] = ImageIO.read(new File("Resources/Textures/Decor/foliage02.png"));
			Constants.IMAGES[703] = ImageIO.read(new File("Resources/Textures/Decor/foliage03.png"));
			Constants.IMAGES[704] = ImageIO.read(new File("Resources/Textures/Decor/foliage04.png"));
			Constants.IMAGES[705] = ImageIO.read(new File("Resources/Textures/Decor/foliage05.png"));
			Constants.IMAGES[706] = ImageIO.read(new File("Resources/Textures/Decor/foliage06.png"));
			Constants.IMAGES[707] = ImageIO.read(new File("Resources/Textures/Decor/foliage07.png"));
			Constants.IMAGES[708] = ImageIO.read(new File("Resources/Textures/Decor/foliage08.png"));
			
			Constants.IMAGES[709] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/1.png"));
			Constants.IMAGES[710] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/2.png"));
			Constants.IMAGES[711] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/3.png"));
			Constants.IMAGES[712] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/4.png"));
			Constants.IMAGES[713] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/5.png"));
			Constants.IMAGES[714] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/6.png"));
			Constants.IMAGES[715] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/7.png"));
			Constants.IMAGES[716] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/8.png"));
			Constants.IMAGES[717] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/9.png"));
			Constants.IMAGES[718] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/10.png"));
			Constants.IMAGES[719] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/11.png"));
			Constants.IMAGES[720] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/12.png"));
			Constants.IMAGES[721] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/13.png"));
			Constants.IMAGES[722] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/14.png"));
			Constants.IMAGES[723] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/15.png"));
			Constants.IMAGES[724] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/16.png"));
			Constants.IMAGES[725] = ImageIO.read(new File("Resources/Textures/Tiles/Castle/17.png"));
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
