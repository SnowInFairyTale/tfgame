package org.test;

public class MonsterBerserker extends Monster {
    public MonsterBerserker(MainGame game, Wave wave, float speed, int startHitPoints, int value, int huJia, int geDang) {
        super(game, wave, startHitPoints, speed, value, huJia, geDang,
                "assets/berserker.png", 8, 8, 0x20, 0x20);
        super.setMonsterType(MonsterType.Berserker);
        super.setRadius(5f);
        super.setAnimationSpeedRatio(3);
    }
}