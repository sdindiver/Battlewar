package com.war.game.entities;

import com.war.util.BattleGameUtil;

class Location {
	private int row;
	private int column;
	private String location;

	public Location(String location) {
		this.location=location;
		String[] locationArr = location.split("");
		row = BattleGameUtil.getNumericValueString(locationArr[0]);
		column = BattleGameUtil.getNumericValueString(locationArr[1]);
	}

	public int getRow() {
		return this.row-1;
	}

	public int getColumn() {
		return this.column-1;
	}
	
	@Override
	public String toString() {
		return this.location;
	}

}
