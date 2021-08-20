package org.test;

public class TowerAxe extends Tower
{
	public TowerAxe(MainGame game)
	{
		super(game, TowerType.Axe, Capability.AirGround, "assets/towers/bash_tower.png");
		super.setTowerLevels(new TowerLevel[] {
				new TowerLevel(5, 60f, 20, 0.7f, 0.6f, 0f),
				new TowerLevel(5, 60f, 40, 0.7f, 0.6f, 5f),
				new TowerLevel(10, 70f, 0x46, 0.7f, 0.6f, 5f),
				new TowerLevel(30, 70f, 120, 0.7f, 0.6f, 5f),
				new TowerLevel(70, 90f, 160, 0.7f, 0.6f, 5f)
		});
		super.SetValuesFromTowerLevel(0);
		super.SetInitialValue();
	}
}