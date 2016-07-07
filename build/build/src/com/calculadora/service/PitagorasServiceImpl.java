package com.calculadora.service;

public class PitagorasServiceImpl implements PitagorasService {

	@Override
	public Double calcularHipotenusa(Double catetoOposto, Double catetoAdjacente) {
		return calcular(catetoOposto, catetoAdjacente);
	}
	
	public Double calcular(Double catetoOposto, Double catetoAdjacente){
		return Math.sqrt(Math.pow(catetoOposto, 2) + Math.pow(catetoAdjacente, 2));
	}

}
