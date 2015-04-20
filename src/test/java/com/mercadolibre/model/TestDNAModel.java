package com.mercadolibre.model;

import org.junit.Test;

import junit.framework.TestCase;

public class TestDNAModel extends TestCase {

	
	@Test
	public void testA(){
		
		String[][] humanDNA = {
									{"A", "T", "G", "C", "G", "A"},
									{"C", "A", "G", "T", "G", "C"},
									{"T", "T", "A", "T", "T", "T"},
									{"A", "G", "A", "C", "G", "G"},
									{"G", "C", "G", "T", "C", "A"},
									{"T", "C", "A", "C", "T", "G"}
								};

		String[][] mutantDNA = {
									{"A", "T", "G", "C", "A", "A", "C"},
									{"C", "A", "G", "T", "C", "C", "A"},
									{"B", "T", "G", "T", "G", "G", "X"},
									{"A", "G", "A", "A", "G", "G", "C"},
									{"A", "C", "C", "B", "T", "G", "C"},
									{"C", "C", "B", "C", "T", "X", "C"},
									{"C", "C", "B", "C", "T", "X", "C"}
								};
		System.out.println("testA..");
	}
	
	private static void print(String[][] dna){
		StringBuffer matrix = new StringBuffer();
		for(int i =0; i < dna.length; i++){
			for(int j = 0; j < dna.length; j++){
				matrix.append("[ " + dna[i][j] + " ]");
			}
			matrix.append("\n");
		}
		System.out.println(matrix);
	}
}
