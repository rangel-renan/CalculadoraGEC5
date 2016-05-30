package com.calculadora.service;

import java.util.Arrays;
import java.util.List;

public class ProdutoVetorialServiceImpl implements ProdutoVetorialService {
	
	public List<Double> produto(List<Double> vetor1, List<Double> vetor2) {
		Double produto1 = 1.0;
		Double produto2 = 1.0;
		Double produto3 = 1.0;
		Double produto4 = 1.0;
		Double produto5 = 1.0;
		Double produto6 = 1.0;
		
		Double[] arrayVetor1 = (Double[]) vetor1.toArray();
		Double[] arrayVetor2 = (Double[]) vetor1.toArray();
		Double[][] matrizLocal = {arrayVetor1, arrayVetor2};
		
		for(int contadorLinhas = 0; contadorLinhas < 2; contadorLinhas++){
			for(int contadorColunas = 0; contadorColunas < 3; contadorColunas++){
				if(contadorLinhas == contadorColunas){
					produto3 *= matrizLocal[contadorLinhas][contadorColunas];
					if(contadorLinhas == 1 && contadorColunas == 1){
						produto4 *= matrizLocal[contadorLinhas][contadorColunas];
					}
					if(contadorLinhas == 0 && contadorColunas == 0){
						produto5 *= matrizLocal[contadorLinhas][contadorColunas];
					}
				}
				if(contadorLinhas == (contadorColunas - 1)){
					produto1 *= matrizLocal[contadorLinhas][contadorColunas];
					if(contadorLinhas == 1 && contadorColunas == 2){
						produto5 *= matrizLocal[contadorLinhas][contadorColunas];
					}
					if(contadorLinhas == 0 && contadorColunas == 1){
						produto6 *= matrizLocal[contadorLinhas][contadorColunas];
					}
				}
				if(contadorLinhas == (contadorColunas + 1) || contadorLinhas == (contadorColunas - 2)){
					produto2 *= matrizLocal[contadorLinhas][contadorColunas];
					if(contadorLinhas == 1 && contadorColunas == 0){
						produto6 *= matrizLocal[contadorLinhas][contadorColunas];
					}
					if(contadorLinhas == 0 && contadorColunas == 2){
						produto4 *= matrizLocal[contadorLinhas][contadorColunas];
					}
				}
			}
		}
		
		Double[] vetor = {produto1 - produto4, produto2 - produto5, produto3 - produto6 };
		
		return Arrays.asList(vetor);	
	}

}
