package org.test;

public class TowerAirDefence extends Tower {
    public TowerAirDefence(MainGame game) {
        super(game, TowerType.AirDefence, Capability.AirGround, "assets/towers/air_tower.png");
        super.setTowerLevels(new TowerLevel[]{
                new TowerLevel(10, 80f, 20, 0.25f, 0.25f, 0f),
                new TowerLevel(10, 90f, 40, 0.25f, 0.25f, 5f),
                new TowerLevel(15, 100f, 60, 0.25f, 0.25f, 5f),
                new TowerLevel(20, 110f, 80, 0.25f, 0.25f, 5f),
                new TowerLevel(0x19, 120f, 100, 0.25f, 0.25f, 5f)
        });
        super.SetValuesFromTowerLevel(0);
        super.SetInitialValue();
    }
}