package com.mercadolibre.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component(value="mutantModel")
public class Mutant {

	public boolean isMutant(Map m, String[][] dna){
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
				isMutant = MutantHelper.hasSequence(sb.toString());
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
				isMutant = MutantHelper.hasSequence(sb.toString());
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
			isMutant = MutantHelper.hasSequence(sb.toString());
		}
		
		return isMutant;
	}
}
