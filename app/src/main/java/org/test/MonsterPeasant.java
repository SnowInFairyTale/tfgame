package org.test;

public class MonsterPeasant extends Monster {
	public MonsterPeasant(MainGame game, Wave wave, float speed,
			int startHitPoints, int value, int huJia, int geDang) {
		super(game, wave, startHitPoints, speed, value, huJia, geDang, "assets/peasant.png",
				8, 8, 0x18, 0x18);
		super.setMonsterType(MonsterType.Peasant);
		super.setRadius(5f);
		super.setAnimationSpeedRatio(3);
	}
}