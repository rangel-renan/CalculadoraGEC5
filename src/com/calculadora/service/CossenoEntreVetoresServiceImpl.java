package com.calculadora.service;

import java.util.List;

import com.calculadora.model.CossenoEntreVetores;
import com.calculadora.vetores.ModuloVetor;
import com.calculadora.vetores.ProdutoEscalar;

public class CossenoEntreVetoresServiceImpl implements CossenoEntreVotores {
	private CossenoEntreVetores cossenoEntreVetores;
	
	public CossenoEntreVetoresServiceImpl(CossenoEntreVetores _cossenoEntreVetores) {
		cossenoEntreVetores = _cossenoEntreVetores;
	}

	@Override
	public Double calcularCosseno() {
		return calculaCosseno();
	}
	
	public Double calculaCosseno() {
		Double numeroResult;
		
		ModuloVetor moduloVetor1 = new ModuloVetor();
		ModuloVetor moduloVetor2 = new ModuloVetor();
		
		ProdutoEscalar produtoEscalar = new ProdutoEscalar();
		
		produtoEscalar.setVetor1(cossenoEntreVetores.getVetor1());
		produtoEscalar.setVetor2(cossenoEntreVetores.getVetor2());
		produtoEscalar.fazProduto();
		
		moduloVetor1.setVetor(cossenoEntreVetores.getVetor1());
		moduloVetor2.setVetor(cossenoEntreVetores.getVetor2());
		
		numeroResult = produtoEscalar.getResult() / (moduloVetor2.calculaModulo()*(moduloVetor1.calculaModulo()));
		cossenoEntreVetores.setResultado(numeroResult);
		
		return numeroResult;
	}
	
//	// insere dois vetores, calcula e mostra o cosseno do angulo entre eles
//	public void cosseno() {
//		
//		System.out.println("---Primeiro Vetor--- ");
//		this.setVetor1(this.leVetor());
//		System.out.println("---Segundo Vetor:--- ");
//		this.setVetor2(this.leVetor());
//		
//		this.calculaCosseno();
//		
//		this.mostraCosseno();
//		
//	}
	
//
//	// lê e retorna as tres coordenadas de um vetor 
//	public double[] leVetor() {
//		double[] vetor = new double[MAX_INDEX];
//		
//		for(int contador = 0; contador < MAX_INDEX; contador++){
//			// para cada posição do vetor, uma coordenada diferente
//			if(contador == 0) { System.out.printf("Entre com o valor de x: "); }
//			if(contador == 1) { System.out.printf("Entre com o valor de y: "); }
//			if(contador == 2) { System.out.printf("Entre com o valor de z: "); }
//			
//			vetor[contador] = input.nextDouble();
//		} 
//		return vetor;
//	}
	
}
