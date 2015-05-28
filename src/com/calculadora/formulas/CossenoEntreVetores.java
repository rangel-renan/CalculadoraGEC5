package com.calculadora.formulas;

import com.calculadora.vetores.ModuloVetor;
import com.calculadora.vetores.ProdutoEscalar;

import java.util.Scanner;


public class CossenoEntreVetores {
	
	final int MAX_INDEX = 3;
	
	double vetorResultante;
	
	double[] vetor1 = new double[MAX_INDEX];
	double[] vetor2 = new double[MAX_INDEX];
	
	ProdutoEscalar produtoEscalar = new ProdutoEscalar();
	
	ModuloVetor moduloVetor = new ModuloVetor();
	
	Scanner input = new Scanner(System.in);
	
	
	
	public void setVetor1(double[] vetor1) {
		this.vetor1 = vetor1;
	}
	
	public double[] getVetor1() {
		return vetor1;
	}
	
	public void setVetor2(double[] vetor2) {
		this.vetor2 = vetor2;
	}
	
	public double[] getVetor2() {
		return vetor2;
	}
	
	public double[] leVetor() {
		double[] vetor = new double[MAX_INDEX];
		
		Scanner input = new Scanner(System.in);
		
		for(int contador = 0; contador < MAX_INDEX; contador++){
			if(contador == 0) { System.out.printf("Entre com o valor de x: "); }
			if(contador == 1) { System.out.printf("Entre com o valor de y: "); }
			if(contador == 2) { System.out.printf("Entre com o valor de z: "); }
			vetor[contador] = input.nextDouble();
		} return vetor;
	}
	
	public double calculaCosseno() {
		
		ModuloVetor moduloVetor1 = new ModuloVetor();
		ModuloVetor moduloVetor2 = new ModuloVetor();
		
		ProdutoEscalar produtoEscalar = new ProdutoEscalar();
		
		this.getVetor1();
		this.getVetor2();
		
		produtoEscalar.setVetor1(vetor1);
		produtoEscalar.setVetor2(vetor2);
		produtoEscalar.fazProduto();
		
		moduloVetor1.setVetor(vetor1);
		
		moduloVetor2.setVetor(vetor2);
		
		vetorResultante = (produtoEscalar.getResult())/((moduloVetor2.calculaModulo()*(moduloVetor1.calculaModulo())));
		
		return vetorResultante;
	}
	
	public void mostraCosseno() {
		System.out.println("O cosseno entre os vetores eh: " + vetorResultante);
	}
	
	public void cosseno() {
		
		this.setVetor1(this.leVetor());
		this.setVetor2(this.leVetor());
		
		this.calculaCosseno();
		
		this.mostraCosseno();
		
	}
}
