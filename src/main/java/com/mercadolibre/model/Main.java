package com.mercadolibre.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

	private static String[][] humanDNA = {
												{"A", "T", "G", "C", "G", "A"},
												{"C", "A", "G", "T", "G", "C"},
												{"T", "T", "A", "T", "T", "T"},
												{"A", "G", "A", "C", "G", "G"},
												{"G", "C", "G", "T", "C", "A"},
												{"T", "C", "A", "C", "T", "G"}
											};

	private static String[][] mutantDNA = {
												{"A", "T", "G", "C", "A", "A", "C"},
												{"C", "A", "G", "T", "C", "C", "A"},
												{"B", "T", "G", "T", "G", "G", "X"},
												{"A", "G", "A", "A", "G", "G", "C"},
												{"A", "C", "C", "B", "T", "G", "C"},
												{"C", "C", "B", "C", "T", "X", "C"},
												{"C", "C", "B", "C", "T", "X", "C"}
											};
	
	public static void main(String args[]){
		processData(mutantDNA);
		print(mutantDNA);
	}
	
	private static void processData(String[][] data){
		int[][] a = generateAdjacencyMatrix(data);
		Map<String, List<Integer>> m = calculateMatrixDegrees(a);
		boolean isMutant = isMutant(m,data);
		System.out.println(isMutant);
	}

	private static boolean isMutant(Map m, String[][] dna){
		boolean isMutant = false;
		List<Integer> h = (List<Integer>)m.get("H");
		List<Integer> v = (List<Integer>)m.get("V");
		List<Integer> d = (List<Integer>) m.get("D");
		
		for(int indexH = 0; indexH < h.size(); indexH++){
			if(h.get(indexH) > 3 && isMutant != true){
				StringBuffer sb = new StringBuffer();
				for(String s : dna[indexH]){
					sb.append(s);
				}
				isMutant = hasSequence(sb.toString());
			}
		}
		
		for(int indexV = 0; indexV < v.size(); indexV++){
			if(v.get(indexV) > 3 && isMutant != true){
				String[] temp = new String[dna.length];
				for(int j= 0; j < dna.length; j++){
					temp[j] = dna[j][indexV];
				}
				
				StringBuffer sb = new StringBuffer();
				for(String s : temp){
					sb.append(s);
				}
				isMutant = hasSequence(sb.toString());
			}
		}
		
		String[] temp = new String[dna.length];
		for(int indexD = 0, index = 0; indexD < d.size(); indexD++){
			if(isMutant != true){
				for(int k = 0; k < dna.length; k++){
					if(indexD == k){
						temp[index++] = dna[k][indexD];
					}
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		for(String s : temp){
			sb.append(s);
		}
		if(isMutant != true){
			isMutant = hasSequence(sb.toString());
		}
		
		return isMutant;
	}
	
	private static boolean hasSequence(String nitrogenSequence){
		String p1 = "(.*)([A]){4}(.*)";
		String p2 = "(.*)([G]){4}(.*)";
		String p3 = "(.*)([C]){4}(.*)";
		return 	nitrogenSequence.matches(p1) ||
				nitrogenSequence.matches(p2) ||
				nitrogenSequence.matches(p3);
	}

	private static Map<String, List<Integer>> calculateMatrixDegrees(int[][] a){
		Map<String, List<Integer>> m = new HashMap<String, List<Integer>>();
		
		List<Integer> countH = new ArrayList<Integer>(a.length); 
		for(int i = 0; i < a.length; i++){
			int sumH = 0;
			for(int j = 0; j < a.length; j++){
				sumH += a[i][j];
			}
			countH.add(sumH);
		}
		
		List<Integer> countV = new ArrayList<Integer>(a.length);
		for(int column = 0; column < a.length; column++){
			int sumV = 0;
			for(int row = 0; row < a.length; row++){
				sumV += a[row][column];
			}
			countV.add(sumV);
		}
		
		List<Integer> countD = new ArrayList<Integer>(a.length);
		for(int column = 0; column < a.length; column++){
			int sumD = 0;
			for(int row = 0; row < a.length; row++){
				if(column == row){
					sumD += a[row][column];
				}
			}
			countD.add(sumD);
		}
		
		m.put("V", countV);
		m.put("H", countH);
		m.put("D", countD);
		return m;
	}
	
	
	private static int[][] generateAdjacencyMatrix(String[][] g){
		int edgesQuantity = g.length;
		int[][] A = new int[edgesQuantity][edgesQuantity];
		
		for(int row = 0; row < A.length; row++){
			for(int column = 0; column < A.length; column++){
				if(row == 0 && column == 0) continue;
				if(row <= A.length && column > 0){
					if(g[row][column] == g[row][(column-1)] && isMutantNitrogenBase(g[row][column])){
						A[row][column] = 1;
						A[row][(column-1)] = 1;
					}
				}
			}
		}
		
		for(int column = 0; column < A.length; column++){
			for(int row = 0; row < A.length; row++){
				if(column == 0 && row == 0) continue;
				if(column <= A.length && row > 0){
					if(row > 0){
						if(g[row][column] == g[(row-1)][column] && isMutantNitrogenBase(g[row][column])){
							A[row][column] = 1;
							A[(row-1)][column] = 1;
						}
					}
				}
			}
		}
		
		for(int i = 0; i < A.length; i++){
			for(int j = 0; j < A.length; j++){
				if(i == 0 && j == 0) continue;
				if(i <= A.length && j > 0){
					if(i == j){
						if(g[j][i] == g[(j-1)][(i-1)] && isMutantNitrogenBase(g[j][i])){
							A[j][i] = 1;
							A[(j-1)][(i-1)] = 1;
						}
					}
				}
			}
		}
		
		return A;
	}
	
	private static boolean isMutantNitrogenBase(String base){
		return base.matches("[ACG]");
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
