package org.test;

public class TowerSpear extends Tower {
    public TowerSpear(MainGame game) {
        super(game, TowerType.Spear, Capability.AirGround, "assets/towers/turbo_tower.png");
        super.setTowerLevels(new TowerLevel[]{
                new TowerLevel(15, 80f, 10, 0.25f, 0.2f, 0f),
                new TowerLevel(12, 85f, 20, 0.24f, 0.2f, 5f),
                new TowerLevel(0x17, 90f, 48, 0.23f, 0.2f, 5f),
                new TowerLevel(0x23, 95f, 80, 0.22f, 0.2f, 5f),
                new TowerLevel(0x4b, 100f, 130, 0.21f, 0.2f, 5f)
        });
        super.SetValuesFromTowerLevel(0);
        super.SetInitialValue();
    }
}