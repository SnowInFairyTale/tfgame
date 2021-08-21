package org.test;

public class TowerAxe extends Tower {
    public TowerAxe(MainGame game) {
        super(game, TowerType.Axe, Capability.AirGround, "assets/towers/bash_tower.png");
        super.setTowerLevels(new TowerLevel[]{
                new TowerLevel(5, 70f, 20, 0.75f, 0.6f, 0f),
                new TowerLevel(5, 75f, 40, 0.7f, 0.6f, 5f),
                new TowerLevel(10, 80f, 70, 0.65f, 0.6f, 5f),
                new TowerLevel(30, 85f, 120, 0.6f, 0.6f, 5f),
                new TowerLevel(70, 90f, 160, 0.55f, 0.6f, 5f)
        });
        super.SetValuesFromTowerLevel(0);
        super.SetInitialValue();
    }
}