package com.mercadolibre.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mercadolibre.model.MutantHelper;

@Service(value="graphService")
public class GraphService implements IGraphService {

	public int[][] generateAdjacencyMatrix(String[][] g){
		
		int[][] A = null;
		int edgesQuantity = g.length;
		if(edgesQuantity % 2 == 0 && g[0].length == edgesQuantity){
			A = new int[edgesQuantity][edgesQuantity];
		}
		
		if(A != null){
			for(int row = 0; row < A.length; row++){
				for(int column = 0; column < A.length; column++){
					if(row == 0 && column == 0) continue;
					if(row <= A.length && column > 0){
						if(g[row][column].equals(g[row][(column-1)]) && MutantHelper.isMutantNitrogenBase(g[row][column])){
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
							if(g[row][column].equals(g[(row-1)][column]) && MutantHelper.isMutantNitrogenBase(g[row][column])){
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
							if(g[j][i].equals(g[(j-1)][(i-1)]) && MutantHelper.isMutantNitrogenBase(g[j][i])){
								A[j][i] = 1;
								A[(j-1)][(i-1)] = 1;
							}
						}
					}
				}
			}
			
		}
		
		return A;
	}
	
	public Map<String, List<Integer>> calculateMatrixDegrees(int[][] a){
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
}
