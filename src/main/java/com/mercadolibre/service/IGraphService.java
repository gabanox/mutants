package com.mercadolibre.service;

import java.util.List;
import java.util.Map;

public interface IGraphService {
	
	public int[][] generateAdjacencyMatrix(String[][] g);
	public Map<String, List<Integer>> calculateMatrixDegrees(int[][] a);
}