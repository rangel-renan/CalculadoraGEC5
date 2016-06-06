package com.calculadora.service;

import java.util.List;

public class ModuloVetorServiceImpl implements ModuloVetorService {

	public Double calculaModulo(List<Double> vetor) {
		// cria um numero auxiliar
		Double moduloAux = 0.0;

		// cria vetor auxiliar que obtem o valor do vetor a ser calculado
		List<Double> vetorAux = vetor;

		// calcula a formula do módulo de um vetor
		moduloAux = Math.sqrt((vetorAux.get(0) * vetorAux.get(0)) + (vetorAux.get(1) * vetorAux.get(1))
				+ (vetorAux.get(2) * vetorAux.get(2)));

		return moduloAux;
	}
}
