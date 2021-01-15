package com.war.util;
public final class BattleGameUtil {
	public static int getNumericValueString(String alphabetString) {
		try {
			return Integer.parseInt(alphabetString);
		} catch (Exception e) {
			return (int) (alphabetString).charAt(0) - 64;
		}
	}

}
