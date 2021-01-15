package com.war.game.entities;

import java.util.LinkedList;
import java.util.Queue;

import com.war.enums.AttackResult;
import com.war.enums.ShipType;
import com.war.game.entities.BattleBuilder.BattleArea;

public class PlayerBuilder {
	private String playerName;
	private BattleBuilder battleBuilder;
	private Queue<Location> playerAttackLocationList = null;
	private int battleShipCount = 0;

	public PlayerBuilder(String playerName, BattleBuilder battleArea) {
		init(playerName, battleArea, null);
	}

	public PlayerBuilder(String playerName, BattleBuilder battleArea, String playerFightingSequence) {
		init(playerName, battleArea, playerFightingSequence);
	}

	public PlayerBuilder addFightingSequence(String playerFightingSequence) {
		if (playerFightingSequence != null) {
			playerAttackLocationList = new LinkedList<>();
			String[] fightingSequenceArr = playerFightingSequence.split(" ");

			for (String fightLocation : fightingSequenceArr) {
				playerAttackLocationList.add(new Location(fightLocation));
			}
		}
		return this;
	}
	
	
	public PlayerBuilder addShipCount(int battleShipCnt) {
		this.battleBuilder.addBattleShipCount(battleShipCount);
		return this;

	}


	private void init(String playerName, BattleBuilder battleArea, String playerFightingSequence) {
		this.playerName = playerName;
		this.battleBuilder = battleArea;
		addFightingSequence(playerFightingSequence);
	}

	public Player build() {
		
		return new Player(this.playerName, this.battleBuilder.build(),  this.playerAttackLocationList);
	}

	public static class Player {
		private String playerName;
		private Queue<Location> playerAttackLocationList = null;
		private BattleArea playerBattleArea; 

		private Player(String playerName,BattleArea playerBattleArea,Queue<Location> playerAttackLocationList) {
			this.playerName = playerName;
			this.playerAttackLocationList = playerAttackLocationList;
			this.playerBattleArea=playerBattleArea;
		}

		public AttackResult hit(Player secondPlayer) {
			if (!secondPlayer.hasMoreShip()) {
				System.out.println(this.playerName + " won the battle");
				return AttackResult.WON;
			}
			Location attackLocation = this.playerAttackLocationList.poll();
			boolean isHit = false;
			if (attackLocation != null) {
				isHit = secondPlayer.hit(attackLocation);
				String attackType = isHit ? "hit" : "miss";
				System.out.println(this.playerName + " fires a missile with target " + attackLocation + " which got "
						+ attackType);
			} else {
				System.out.println(this.playerName + " has no more missiles left to launch");

			}
			return isHit ? AttackResult.HIT : AttackResult.MISS;
		}

		private boolean hit(Location attackLocation) {
			return this.playerBattleArea.hit(attackLocation);
		}

		private boolean hasMoreShip() {
			return this.playerBattleArea.hasMoreShip();
		}

	}

	public PlayerBuilder addShip(ShipType shipType, int shipWidth, int shipHeight, String firstPlayerShipLocation) {
		this.battleBuilder.placeShip(new BattleShip(shipType, shipWidth, shipHeight, firstPlayerShipLocation));
		return this;
	}

}
