package com.mercadolibre.service;

import com.mercadolibre.model.DNABase;
import com.mercadolibre.model.ProcesedDNA;

public interface IDNAService {

	public abstract ProcesedDNA processDna(DNABase dna);
}
