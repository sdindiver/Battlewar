package com.war.enums;

public enum ShipType {
	SINGLE_HIT("P", 1), DOUBLE_HIT("Q", 2);

	private String shipType;
	private int numberOfHits;

	ShipType(String shipType, int hitCount) {
		this.shipType = shipType;
		this.numberOfHits = hitCount;
	}

	public static ShipType getShipEnum(String shipType) {
		for (ShipType shipTypeEnum : values()) {
			if (shipTypeEnum.shipType.equalsIgnoreCase(shipType)) {
				return shipTypeEnum;
			}
		}
		return null;
	}

	public int getNumberOfHits() {
		return numberOfHits;
	}
}
