package com.war.game.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.war.util.BattleGameUtil;

public class BattleBuilder {

	private int[][] area;

	private int battleShipCount;

	private static final int MAX_AREA_WIDTH = 9;
	private static final String MIN_AREA_HEIGHT = "A";
	private static final String MAX_AREA_HEIGHT = "Z";

	private BattleBuilder(int row, int column) {
		area = new int[row][column];
	}

	public int[][] getArea() {
		return area;
	}

	public static BattleBuilder Of(int row, int column) {
		if (column < 1 || column > MAX_AREA_WIDTH) {
			throw new IllegalArgumentException("Battle Area width is not according to constraint");
		}
		if (column < BattleGameUtil.getNumericValueString(MIN_AREA_HEIGHT)
				|| column > BattleGameUtil.getNumericValueString(MAX_AREA_HEIGHT)) {
			throw new IllegalArgumentException("Battle Area height is not according to constraint");
		}
		return new BattleBuilder(row, column);
	}

	public static BattleBuilder createFreshBattleAreaBuilder() {
		System.out.println("Enter Battle Area");
		// Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String battleArea = null;
		try {
			battleArea = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] areaArr = battleArea.split(" ");
		int row = BattleGameUtil.getNumericValueString(areaArr[0]);
		int column = BattleGameUtil.getNumericValueString(areaArr[1]);
		return Of(row, column);
	}

	public BattleBuilder copy() {
		return Of(getArea().length, getArea()[0].length);
	}

	public BattleBuilder addBattleShipCount(int count) {
		int maxBattleShip = (this.area.length) * (this.area[0].length);
		if (count > maxBattleShip) {
			throw new IllegalArgumentException("Maximum Ship Can not exceed more than " + maxBattleShip);
		}
		battleShipCount = count;
		return this;
	}

	// 2 2 3 2
	public BattleBuilder placeShip(BattleShip ship) {
		checkShipDimension(ship);
		CheckEligiblityOfShipPlacement(ship);
		for (int i = 0; i < ship.getHeight(); i++) {
			for (int j = 0; j < ship.getWidth(); j++) {
				area[ship.getStartColumnLocation() + i - 1][ship.getStartRowLocation() + j - 1] = ship.getShipType()
						.getNumberOfHits();
			}
		}
		return this;
	}

	private void CheckEligiblityOfShipPlacement(BattleShip ship) {
		if ((ship.getStartRowLocation() < 1) || (ship.getStartRowLocation() > this.getArea().length)) {
			throw new IllegalArgumentException("Such Row does not exist to place ship");
		}
		if ((ship.getStartColumnLocation() < 1) || (ship.getStartColumnLocation() > this.getArea()[0].length)) {
			throw new IllegalArgumentException("Such Column does not exist to place ship");
		}
	}

	private void checkShipDimension(BattleShip ship) {
		int battleAreaHeight = this.getArea().length;
		int battleAreaWidth = this.getArea()[0].length;
		if (ship.getHeight() > battleAreaHeight) {
			throw new IllegalArgumentException("Ship Height can not be more than battle area height");
		}
		if (ship.getWidth() > battleAreaWidth) {
			throw new IllegalArgumentException("Ship width can not be more than battle area width");
		}
	}

	public BattleBuilder placeShip(List<BattleShip> battleShips) {
		for (BattleShip ship : battleShips) {
			placeShip(ship);
		}
		return this;
	}

	public BattleArea build() {
		return new BattleArea(this.area, this.battleShipCount);
	}

	static class BattleArea {
		private int[][] area;

		private int battleShipCount;

		private BattleArea(int[][] area, int battleShipCount) {
			super();
			this.area = area;
			this.battleShipCount = battleShipCount;
		}

		public int getBattleShipCount() {
			return battleShipCount;
		}

		public void printBattleArea() {
			System.out.println("###########################");
			for (int i = 0; i < area.length; i++) {
				for (int j = 0; j < area.length; j++) {
					System.out.print(area[i][j]);
				}
				System.out.println();
			}
			System.out.println("###########################");

		}

		private boolean checkIfShipExistOnLocation(Location attackLocation) {
			return area[attackLocation.getRow()][attackLocation.getColumn()] >= 1;
		}

		private boolean destroyShipOnLocation(Location attackLocation) {
			if (!checkIfShipExistOnLocation(attackLocation)) {
				return false;
			}
			area[attackLocation.getRow()][attackLocation
					.getColumn()] = area[attackLocation.getRow()][attackLocation.getColumn()] - 1;
			return true;
		}

		public boolean hit(Location attackLocation) {
			return destroyShipOnLocation(attackLocation);
		}

		public boolean hasMoreShip() {
			for (int i = 0; i < area.length; i++) {
				for (int j = 0; j < area.length; j++) {
					if (area[i][j] >= 1) {
						return true;
					}
				}
			}
			return false;
		}
	}

}
