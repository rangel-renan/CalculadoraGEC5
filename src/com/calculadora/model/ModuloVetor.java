// ModuloVetor.java
package com.calculadora.model;

import java.util.ArrayList;
import java.util.List;

// calcula o módulo de um vetor
// formula: raiz((v[0] * v[0]) + (v[1] * v[1]) + (v[2] * v[2]))
public class ModuloVetor {
	public final int MAX_INDEX = 3;
	
	private List<Double> vetor;

	public ModuloVetor() {
		vetor = new ArrayList<Double>(MAX_INDEX);
	}
	
	public ModuloVetor(List<Double> vetor) {
		this.vetor = vetor;
	}

	 public List<Double> getVetor() {
		return vetor;
	}

	public void setVetor(List<Double> vetor) {
		this.vetor = vetor;
	}

} 
