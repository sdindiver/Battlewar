package com.war.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.war.enums.AttackResult;
import com.war.enums.ShipType;
import com.war.game.entities.BattleBuilder;
import com.war.game.entities.PlayerBuilder;
import com.war.util.BattleGameUtil;

public class BattleGame {
	
	private PlayerBuilder.Player firstPlayer = null;
	private PlayerBuilder.Player secondPlayer =null;
	
	
	public BattleGame() {
		init();
	}

	private void init() {
		System.out.println("Game Initializing....");
		System.out.println("BatteArea Manufacturing Starting....");
		BattleBuilder battleBuilder = BattleBuilder.createFreshBattleAreaBuilder();
		System.out.println("BatteArea Manufacturing Completed....");
		PlayerBuilder firstPlayerBuilder = new PlayerBuilder("Player-1",battleBuilder);
		PlayerBuilder secondPlayerBuilder=new PlayerBuilder("Player-2",battleBuilder.copy());
		System.out.println("BattleShip Manufacturing Starting....");
		System.out.println("Enter number of BattleShip to be created");
		int battleShipCnt = Integer.parseInt(readInputLine());
		firstPlayerBuilder.addShipCount(battleShipCnt);
		secondPlayerBuilder.addShipCount(battleShipCnt);
		
		for(int i=0 ; i<battleShipCnt; i++) {
			System.out.println("BattleShip  Information Gathering....");
			String location = readInputLine();
			String[] locationArr = location.split(" ");
			ShipType shipType = ShipType.getShipEnum(locationArr[0]);
			int shipWidth = BattleGameUtil.getNumericValueString(locationArr[1]);
			int shipHeight = BattleGameUtil.getNumericValueString(locationArr[2]);
			String firstPlayerShipLocation = locationArr[3];
			String secondPlayerShipLocation = locationArr[4];
			firstPlayerBuilder.addShip(shipType,shipWidth,shipHeight,firstPlayerShipLocation);
			secondPlayerBuilder.addShip(shipType,shipWidth,shipHeight,secondPlayerShipLocation);
		}
		System.out.println("BattleShip Manufacturing Finished....");
		System.out.println("First Player Attacking Location Information Gathering....");
		String firstPlayerFightingLocation = readInputLine();
		firstPlayerBuilder.addFightingSequence(firstPlayerFightingLocation);
		System.out.println("Second Player Attacking Location Information Gathering....");
		String secondPlayerFightingLocation = readInputLine();
		secondPlayerBuilder.addFightingSequence(secondPlayerFightingLocation);
		
		firstPlayer=firstPlayerBuilder.build();
		secondPlayer=secondPlayerBuilder.build();
		System.out.println("Game Initialised....");

	}
	
	public void start() {
		System.out.println("Game Started....");
		AttackResult hitType=AttackResult.HIT;
		boolean byPlayerOne=true;
		while(hitType!=AttackResult.WON) {
			if((hitType==AttackResult.HIT && byPlayerOne) || (hitType==AttackResult.MISS && !byPlayerOne)) {
				hitType = playerOneHittingPlayerTwo();
				byPlayerOne=true;
			}else {
				hitType = playerTwoHittingPlayerOne();
				byPlayerOne=false;
			}
		}
		System.out.println("Game Over....");
	}
	
	
	private AttackResult playerOneHittingPlayerTwo() {
		return firstPlayer.hit(secondPlayer);
	}
	
	private AttackResult playerTwoHittingPlayerOne() {
		return secondPlayer.hit(firstPlayer);
		
	}



	private BufferedReader getInputReader() {
		return new BufferedReader(new InputStreamReader(System.in));
	}
	
	private String readInputLine() {
		try {
			return getInputReader().readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
