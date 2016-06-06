// ProdutoNumeroVetor.java
package com.calculadora.model;

import java.util.ArrayList;
import java.util.List;

	
// Calcula o produto entre um numero e um vetor
// Exemplo: 2(3, 4, 5) = (6, 8, 10)
public class ProdutoNumeroVetor {
	public static final int INDICE_MAX = 3;
	
	private Double numeroProduto;
	private List<Double> vetorResultante;
	private List<Double> vetorProduto;
	
	// criação de um construtor simples
	public ProdutoNumeroVetor() {
		vetorResultante = new ArrayList<Double>(INDICE_MAX);
		vetorProduto = new ArrayList<Double>(INDICE_MAX);
	}
	
	public ProdutoNumeroVetor(List<Double> vetorResultante, List<Double> vetorProduto) {
		this.vetorResultante = vetorResultante;
		this.vetorProduto = vetorProduto;
	}
	
	public ProdutoNumeroVetor(Double numeroProduto, List<Double> vetorProduto) {
		this.numeroProduto = numeroProduto;
		this.vetorProduto = vetorProduto;
	}

	public Double getNumeroProduto() {
		return numeroProduto;
	}

	public void setNumeroProduto(Double numeroProduto) {
		this.numeroProduto = numeroProduto;
	}

	public List<Double> getVetorResultante() {
		return vetorResultante;
	}

	public void setVetorResultante(List<Double> vetorResultante) {
		this.vetorResultante = vetorResultante;
	}

	public List<Double> getVetorProduto() {
		return vetorProduto;
	}

	public void setVetorProduto(List<Double> vetorProduto) {
		this.vetorProduto = vetorProduto;
	}

//	// le e retorna o valor de um numero
//	public void leNumeroProduto() {
//		System.out.println("Entre com o numero do produto : ");
//		this.setNumeroProduto(input.nextDouble());
//	}
//	
//	// le e retorna os valores de um vetor
//	public void leVetorProduto() {
//		// cria um vetor auxiliar
//		double[] vetor = new double[INDICE_MAX];
//		
//		// pede que o usuario insira o numero para cada indice do vetor
//		for(int indice = 0; indice < INDICE_MAX; indice++) {
//			System.out.println("Insira o elemento do vetor: ");
//			vetor[indice] = input.nextDouble();
//		}
//		// vetorProduto se torna igual a vetor
//		this.setVetorProduto(vetor);
//	}
//	
	
	// faz o calculo da operação ProdutoNumeroVetor
	
//	
//	// printa na tela o vetorResultante
//	public void mostraResultado() {
//		
//		// cria vetorResult, que é igual ao valor de vetorResultante
//		double[] vetorResult = getVetorResultante();
//		
//		System.out.println("Resultado da operacao:"); 
//		
//		// o sistema printa cada valor de cada indice de vetorResult
//		for(int indice = 0; indice < INDICE_MAX; indice++){
//			System.out.printf("%f ", vetorResult[indice]);
//		}		
//		System.out.println("\n");
//	}
//	
//	
//	// insere um numero e um vetor, calcula e mostra o resultado do produto
//	public void produtoNumeroVetor() {
//		
//		this.leNumeroProduto();
//		this.leVetorProduto();
//		
//		this.calcProduto();
//		
//		this.mostraResultado();
//	}
//	

} // término da classe ProdutoNumeroVetor
