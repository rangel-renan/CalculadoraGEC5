// ProjecaoDeUmVetor.java
package com.calculadora.model;

import java.util.List;

// Calcula a projecao de um vetor1 sobre o vetor2
// formula: ((vetor1*vetor2)/(|vetor2||vetor2|))*vetor2
public class ProjecaoDeUmVetor {
	public static final int INDICE_MAX = 3;
	
	private List<Double> vetor1;
	private List<Double> vetor2;

	public ProjecaoDeUmVetor() {
		
	}

	public ProjecaoDeUmVetor(List<Double> vetor1, List<Double> vetor2) {
		this.vetor1 = vetor1;
		this.vetor2 = vetor2;
	}

	public List<Double> getVetor1() {
		return vetor1;
	}

	public void setVetor1(List<Double> vetor1) {
		this.vetor1 = vetor1;
	}

	public List<Double> getVetor2() {
		return vetor2;
	}

	public void setVetor2(List<Double> vetor2) {
		this.vetor2 = vetor2;
	}

}
