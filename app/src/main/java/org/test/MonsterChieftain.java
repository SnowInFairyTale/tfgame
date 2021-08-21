package org.test;

public class MonsterChieftain extends Monster {
    public MonsterChieftain(MainGame game, Wave wave, float speed,
                            int startHitPoints, int value, int huJia, int geDang) {
        super(game, wave, startHitPoints, speed, value, huJia, geDang, "assets/chieftain.png",
                8, 13, 0x27, 0x27);
        super.setMonsterType(MonsterType.Chieftain);
        super.setRadius(8f);
        super.setAnimationSpeedRatio(3);
    }
}