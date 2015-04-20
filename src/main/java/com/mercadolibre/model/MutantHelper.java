package com.mercadolibre.model;

public class MutantHelper {

	public static boolean isMutantNitrogenBase(String base){
		return base.matches("[ACG]");
	}
	
	public static boolean hasSequence(String nitrogenSequence){
		String p1 = "(.*)([A]){4}(.*)";
		String p2 = "(.*)([G]){4}(.*)";
		String p3 = "(.*)([C]){4}(.*)";
		return 	nitrogenSequence.matches(p1) ||
				nitrogenSequence.matches(p2) ||
				nitrogenSequence.matches(p3);
	}
}
