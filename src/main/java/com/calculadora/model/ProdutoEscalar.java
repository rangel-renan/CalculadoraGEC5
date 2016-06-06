package com.calculadora.model;

import java.util.List;

public class ProdutoEscalar {
	public static final int TAM_VETOR = 3;
	
	private List<Double> vetor1;
	private List<Double> vetor2;
	
	public ProdutoEscalar() {
	}
	
	public ProdutoEscalar(List<Double> vetor1, List<Double> vetor2) {
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
