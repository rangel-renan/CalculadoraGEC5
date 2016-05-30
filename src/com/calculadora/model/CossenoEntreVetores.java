// CossenoEntreVetores.java
// Faz a fórmula do cálculo do cosseno do ângulo de dois vetores
package com.calculadora.model;

import java.util.ArrayList;
import java.util.List;

// representa o cálculo do cosseno entre dois vetores
public class CossenoEntreVetores {
	public final int MAX_INDEX = 3;
	
	private List<Double> vetor1;
	private List<Double> vetor2;
	
	public CossenoEntreVetores() {
		vetor1 = new ArrayList<Double>(MAX_INDEX);
		vetor2 = new ArrayList<Double>(MAX_INDEX);
	}
	
	public CossenoEntreVetores(List<Double> vetor1, List<Double> vetor2) {
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
	
	
} // término da classe CossenoEntreVetores
