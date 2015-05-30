package com.calculadora.vetores;

import java.util.Scanner;

public class ProdutoEscalar {
	
	public static final int TAM_VETOR = 3;
	double[] vetor1;
	double[] vetor2;
	double result;
	
	public ProdutoEscalar() {
		
	}
	public ProdutoEscalar(double[] vetor1, double[] vetor2) {
		this.vetor1 = vetor1;
		this.vetor2 = vetor2;
	}
	
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public double[] getVetor1() {
		return vetor1;
	}
	public void setVetor1(double[] vetor1) {
		this.vetor1 = vetor1;
	}
	public double[] getVetor2() {
		return vetor2;
	}
	public void setVetor2(double[] vetor2) {
		this.vetor2 = vetor2;
	}
	
	public double pegaElemento1(int indice){
		double[] vetor = getVetor1();
		return vetor[indice];
	}
	
	public double pegaElemento2(int indice){
		double[] vetor = getVetor2();
		return vetor[indice];
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
		
		int soma = 0;
		
		for (int contador = 0; contador < TAM_VETOR; contador++) {
			soma += (this.pegaElemento1(contador) * this.pegaElemento2(contador));
		}
		this.setResult(soma);
	}
	
	public void mostraResult(){
		System.out.println("Resultado da multiplicação escalar vetorial = " + this.getResult());
	}
	
	public void produto() {
		
		System.out.println("*****Vetor1*****");
		this.setVetor1(this.levetor());
		System.out.println("*****Vetor2*****");
		this.setVetor2(this.levetor());
		this.fazProduto();
		this.mostraResult();
	}
}
