package com.mercadolibre.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.model.DNABase;
import com.mercadolibre.model.Mutant;

@Service(value="DNAService")
public class DNAService implements IDNAService {

	@Autowired
	GraphService graphService = null;
	
	@Autowired
	Mutant mutantModel = null;
	
	public void setMutantModel(Mutant mutantModel) {
		this.mutantModel = mutantModel;
	}

	public void setGraphService(GraphService graphService) {
		this.graphService = graphService;
	}

	public DNABase processDna(DNABase dna) {
		
		int[][] a = graphService.generateAdjacencyMatrix(dna.getData());
		Map<String, List<Integer>> m;
		boolean isMutant = false;
		if(a != null){
			m = new HashMap<String, List<Integer>>();
			m = graphService.calculateMatrixDegrees(a);
			isMutant = mutantModel.isMutant(m,dna.getData());
			
			if(isMutant){
				dna.setKind("MUTANTE");
			}else {
				dna.setKind("POSIBLEMENTE HUMANO");
			}
		}else {
			dna.setKind("ERORR DE ENTRADA");
		}
		
		return dna;
	}

}
