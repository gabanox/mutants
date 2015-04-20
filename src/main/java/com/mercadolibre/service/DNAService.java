package com.mercadolibre.service;

import org.springframework.stereotype.Service;

import com.mercadolibre.model.DNABase;
import com.mercadolibre.model.ProcesedDNA;

@Service(value="DNAService")
public class DNAService implements IDNAService {

	public ProcesedDNA processDna(DNABase dna) {
		ProcesedDNA procesedDNA = new ProcesedDNA();
		//logic
		return procesedDNA;
	}

}
