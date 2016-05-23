package com.calculadora.vetores;

import java.util.List;
import java.util.Scanner;

public class ProdutoEscalar {
	public static final int TAM_VETOR = 3;
	
	private List<Double> vetor1;
	private List<Double> vetor2;
	private Double result;
	
	public ProdutoEscalar() {
		
	}
	
	public ProdutoEscalar(List<Double> vetor1, List<Double> vetor2) {
		super();
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

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}
	
	public double[] levetor() {
		double[] vetor = new double[TAM_VETOR];
		Scanner input = new Scanner(System.in);
		
		for(int contador = 0; contador < TAM_VETOR; contador++){
			if(contador == 0) { System.out.printf("Entre com o valor de x: "); }
			if(contador == 1) { System.out.printf("Entre com o valor de y: "); }
			if(contador == 2) { System.out.printf("Entre com o valor de z: "); }
			vetor[contador] = input.nextDouble();
		}
		return vetor;
	}
	
	public void fazProduto(){
		Double soma = 0.0;
		
		for (int contador = 0; contador < TAM_VETOR; contador++) {
			soma += vetor1.get(contador) * vetor2.get(contador);
		}
		
		this.setResult(soma);
	}
	
	public void mostraResult(){
		System.out.println("Resultado da multiplicação escalar vetorial = " + this.getResult());
	}
	
//	public void produto() {
//		
//		System.out.println("*****Vetor1*****");
//		this.setVetor1(this.levetor());
//		System.out.println("*****Vetor2*****");
//		this.setVetor2(this.levetor());
//		this.fazProduto();
//		this.mostraResult();
//	}
}
