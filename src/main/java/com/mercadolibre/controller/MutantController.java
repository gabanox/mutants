package com.mercadolibre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mercadolibre.model.DNABase;
import com.mercadolibre.model.ProcesedDNA;
import com.mercadolibre.service.DNAService;

@Controller(value="mutantController")
public class MutantController {

	@Autowired
	DNAService dnaService = null;
	
	public void setDnaService(DNAService dnaService) {
		this.dnaService = dnaService;
	}

	@RequestMapping(value="validate/", method = RequestMethod.GET)
	@ResponseBody
	public ProcesedDNA validate(){
		return dnaService.processDna(new DNABase());
		
	}
}
