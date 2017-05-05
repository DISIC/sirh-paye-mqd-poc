package com.thalesgroup.stif.bouchon.producteur.redis.util;

import java.util.Random;

/**
 * Utilss
 * 
 * @author adile
 * 
 */
public class BouchonUtils {

	/**
	 * Retourne un nombre aleatoire compris dans la fourchette min/max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randInt(final int min, final int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

}
