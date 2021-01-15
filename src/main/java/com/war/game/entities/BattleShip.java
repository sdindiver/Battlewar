package com.war.game.entities;

import com.war.enums.ShipType;
import com.war.util.BattleGameUtil;

class BattleShip {

	private int width;
	private int height;
	private int startRowLocation;
	private int startColumnLocation;
	
	private ShipType shipType;


	public ShipType getShipType() {
		return shipType;
	}

	public BattleShip(ShipType shipType,int width, int height, String startingLocation) {
		this.shipType= shipType;
		this.width = width;
		this.height = height;
		String[] rowColumnLocations = startingLocation.split("");
		this.startRowLocation = BattleGameUtil.getNumericValueString(rowColumnLocations[0]);
		this.startColumnLocation = BattleGameUtil.getNumericValueString(rowColumnLocations[1]);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getStartRowLocation() {
		return startRowLocation;
	}

	public void setStartRowLocation(int startRowLocation) {
		this.startRowLocation = startRowLocation;
	}

	public int getStartColumnLocation() {
		return startColumnLocation;
	}

	public void setStartColumnLocation(int startColumnLocation) {
		this.startColumnLocation = startColumnLocation;
	}

}
