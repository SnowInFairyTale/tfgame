package org.test;

public class TowerSpear extends Tower
{
	public TowerSpear(MainGame game)
	{
		super(game, TowerType.Spear, Capability.AirGround, "assets/towers/turbo_tower.png");
		super.setTowerLevels( new TowerLevel[] {
				new TowerLevel(15, 70f, 10, 0.3f, 0.2f, 0f),
				new TowerLevel(12, 70f, 20, 0.3f, 0.2f, 5f),
				new TowerLevel(0x17, 70f, 0x24, 0.3f, 0.2f, 5f),
				new TowerLevel(0x23, 70f, 0x44, 0.3f, 0.2f, 5f),
				new TowerLevel(0x4b, 70f, 0x82, 0.3f, 0.2f, 5f)
		});
		super.SetValuesFromTowerLevel(0);
		super.SetInitialValue();
	}
}