package org.test;

public class TowerButtonAirDefence extends TowerButton
{
	public TowerButtonAirDefence(MainGame game)
	{
		super(game, TowerType.AirDefence);
		super.setTowerPrice(10);
	}
}