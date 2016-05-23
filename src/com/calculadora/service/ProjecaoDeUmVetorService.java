package com.calculadora.service;

import java.util.List;

public interface ProjecaoDeUmVetorService {
	
	public <T extends Number> List<Double> calcularProjecao(List<T> vetor1, List<T> vetor2, List<T> vetorAux);
}
