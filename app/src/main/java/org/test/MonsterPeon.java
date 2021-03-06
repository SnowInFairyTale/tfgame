package org.test;

import loon.core.geom.Vector2f;

public class MonsterPeon extends Monster {
    public MonsterPeon(MainGame game, Wave wave, float speed,
                       int startHitPoints, int value, int huJia, int geDang) {
        super(game, wave, startHitPoints, speed, value, huJia, geDang, "assets/peon.png", 8,
                8, 0x18, 0x18);
        this.Init();
    }

    public MonsterPeon(MainGame game, Wave wave, float speed,
                       int startHitPoints, int value, int huJia, int geDang, Vector2f gridPosition) {
        super(game, wave, startHitPoints, speed, value, huJia, geDang, "assets/peon.png", 8,
                8, 0x18, 0x18, gridPosition);
        this.Init();
    }

    private void Init() {
        super.setMonsterType(MonsterType.Peon);
        super.setRadius(5f);
        super.setAnimationSpeedRatio(3);
    }
}