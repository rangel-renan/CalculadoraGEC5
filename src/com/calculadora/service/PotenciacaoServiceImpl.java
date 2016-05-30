package com.calculadora.service;

public class PotenciacaoServiceImpl implements PotenciacaoService {
	
	public Integer calcular(Integer numero, Integer expoente) {
		Integer resultado = 0;
		
		if(expoente == 0)
			expoente = 1;
		
		for(int contador = 1; contador < expoente; contador++) { 
			resultado *= numero;
		}
		
		return resultado;
	}
}
