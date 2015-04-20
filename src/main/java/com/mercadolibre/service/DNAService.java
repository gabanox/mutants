package com.mercadolibre.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.model.DNABase;
import com.mercadolibre.model.IMutant;
import com.mercadolibre.model.ProcesedDNA;

@Service(value="DNAService")
public class DNAService implements IDNAService {

	@Autowired
	IGraphService graphService = null;
	
	@Autowired
	IMutant mutantModel = null;
	
	public void setMutantModel(IMutant mutantModel) {
		this.mutantModel = mutantModel;
	}

	public void setGraphService(GraphService graphService) {
		this.graphService = graphService;
	}

	public ProcesedDNA processDna(DNABase dna) {
		ProcesedDNA procesedDNA = new ProcesedDNA();
		
		int[][] a = graphService.generateAdjacencyMatrix(procesedDNA.getData());
		Map<String, List<Integer>> m = new HashMap<String, List<Integer>>();
		m = graphService.calculateMatrixDegrees(a);
		boolean isMutant = mutantModel.isMutant(m,procesedDNA.getData());
		
		if(isMutant){
			procesedDNA.setKind("MUTANTE");
		}else {
			procesedDNA.setKind("POSIBLEMENTE HUMANO");
		}
		return procesedDNA;
	}

}
