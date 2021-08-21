package org.test;

public class TowerAirDefence extends Tower
{
	public TowerAirDefence(MainGame game)
	{
		super(game, TowerType.AirDefence, Capability.AirGround, "assets/towers/air_tower.png");
		super.setTowerLevels(new TowerLevel[] {
				new TowerLevel(10, 70f, 20, 0.25f, 0.2f, 0f),
				new TowerLevel(10, 80f, 0x24, 0.25f, 0.2f, 5f),
				new TowerLevel(15, 90f, 0x38, 0.25f, 0.2f, 5f),
				new TowerLevel(20, 100f, 0x46, 0.25f, 0.2f, 5f),
				new TowerLevel(0x19, 100f, 100, 0.25f, 0.2f, 5f)
		});
		super.SetValuesFromTowerLevel(0);
		super.SetInitialValue();
	}
}