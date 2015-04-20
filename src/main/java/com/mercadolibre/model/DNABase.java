package com.mercadolibre.model;

public class DNABase {

	private String dna;
	private String[][] data;
	private String kind;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public String[][] getData() {
		return data;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
		try {
			data = sanitizeAndNormalizeData(dna);
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String[][] sanitizeAndNormalizeData(String dna) throws DataFormatException{
		String[] arr = dna.split("],");
		String[][]data = null;
		
		if(arr.length % 2 == 0){
			data = new String[arr.length][arr.length];
		
			for(int i = 0; i < arr.length; i++){
				arr[i] = arr[i]
								.replace("[", "")
								.replace("]", "")
								.replace(",", "");
				data[i] = arr[i].split(" ");
			}
		
		}else {
			throw new DataFormatException("No fue posible normalizar el arreglo, revisar los datos de entrada");
		}
			
		return data;
	}
}
