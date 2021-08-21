package org.test;

public class TowerLur extends Tower {
    public TowerLur(MainGame game) {
        super(game, TowerType.Lur, Capability.Bash, "assets/towers/normal_tower.png");
        super.setTowerLevels(new TowerLevel[]{
                new TowerLevel(15, 45f, 30, 1.5f, 0.8f, 0f),
                new TowerLevel(15, 50f, 60, 1.4f, 0.8f, 5f),
                new TowerLevel(0x19, 55f, 120, 1.3f, 0.8f, 5f),
                new TowerLevel(40, 60f, 170, 1.2f, 0.8f, 5f),
                new TowerLevel(100, 65f, 300, 1.1f, 0.8f, 5f)
        });
        super.SetValuesFromTowerLevel(0);
        super.SetInitialValue();
    }
}