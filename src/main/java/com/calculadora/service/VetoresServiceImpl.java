package com.calculadora.service;

import com.calculadora.util.VetorTamanhoExcedidoException;
import com.calculadora.util.VetoresTamanhosDiferentesException;

public class VetoresServiceImpl implements VetoresService {

	@Override
	public Double calcularProdutoEscalar(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException {
		if (vetor1.length != vetor2.length)
			throw new VetoresTamanhosDiferentesException("Vetores de Tamanhos Diferentes");

		Double sum = 0.0;

		for (int i = 0; i < vetor1.length; i++)
			sum = sum + (vetor1[i] * vetor2[i]);

		return sum;
	}

	@Override
	public Double[] calcularProjecao(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException {
		if (vetor1.length != vetor2.length)
			throw new VetoresTamanhosDiferentesException("Vetores de Tamanhos Diferentes");
		
		Double produtoEscalar = calcularProdutoEscalar(vetor1, vetor2);
		Double modulo = calcularModulo(vetor2);
		Double numeroProduto = produtoEscalar / (modulo * modulo);

		return calcularProdutoPorX(numeroProduto, vetor2);
	}

	@Override
	public Double[] calcularProdutoPorX(Double valor, Double[] vetor) {
		Double[] vetorResultante = new Double[vetor.length];

		for (int cont = 0; cont < vetor.length; cont++) {
			vetorResultante[cont] += (vetor[cont] * valor);
		}

		return vetorResultante;
	}

	@Override
	public Double calcularModulo(Double[] vetor) {
		try {
			return Math.sqrt(calcularProdutoEscalar(vetor, vetor));
		} catch (VetoresTamanhosDiferentesException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Double calcularCosseno(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException {
		if (vetor1.length != vetor2.length)
			throw new VetoresTamanhosDiferentesException("Vetores de Tamanhos Diferentes");
		
		Double produtoEscalar = calcularProdutoEscalar(vetor1, vetor2);
		Double moduloPrimeiroVetor = calcularModulo(vetor1);
		Double moduloSegundoVetor = calcularModulo(vetor2);
		
		return produtoEscalar / (moduloPrimeiroVetor * moduloSegundoVetor);
	}

	@Override
	public Double[] calcularProdutoVetorial(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException, VetorTamanhoExcedidoException {
		if (vetor1.length != vetor2.length) throw new VetoresTamanhosDiferentesException("Vetores de Tamanhos Diferentes");
		if ((vetor1.length > 0 && vetor1.length < 3) || vetor2.length > 0 && vetor2.length < 3) throw new VetorTamanhoExcedidoException("Vetor deve ter 3 Valores.");
		
		Double produto1 = 1.0;
		Double produto2 = 1.0;
		Double produto3 = 1.0;
		Double produto4 = 1.0;
		Double produto5 = 1.0;
		Double produto6 = 1.0;
		
		Double[][] matrizLocal = {vetor1, vetor2};
		
		for(int contadorLinhas = 0; contadorLinhas < 2; contadorLinhas++){
			for(int contadorColunas = 0; contadorColunas < 3; contadorColunas++){
				if(contadorLinhas == contadorColunas){
					produto3 *= matrizLocal[contadorLinhas][contadorColunas];
					if(contadorLinhas == 1 && contadorColunas == 1){
						produto4 *= matrizLocal[contadorLinhas][contadorColunas];
					}
					if(contadorLinhas == 0 && contadorColunas == 0){
						produto5 *= matrizLocal[contadorLinhas][contadorColunas];
					}
				}
				if(contadorLinhas == (contadorColunas - 1)){
					produto1 *= matrizLocal[contadorLinhas][contadorColunas];
					if(contadorLinhas == 1 && contadorColunas == 2){
						produto5 *= matrizLocal[contadorLinhas][contadorColunas];
					}
					if(contadorLinhas == 0 && contadorColunas == 1){
						produto6 *= matrizLocal[contadorLinhas][contadorColunas];
					}
				}
				if(contadorLinhas == (contadorColunas + 1) || contadorLinhas == (contadorColunas - 2)){
					produto2 *= matrizLocal[contadorLinhas][contadorColunas];
					if(contadorLinhas == 1 && contadorColunas == 0){
						produto6 *= matrizLocal[contadorLinhas][contadorColunas];
					}
					if(contadorLinhas == 0 && contadorColunas == 2){
						produto4 *= matrizLocal[contadorLinhas][contadorColunas];
					}
				}
			}
		}
		
		Double[] vetor = {produto1 - produto4, produto2 - produto5, produto3 - produto6 };
		
		return vetor;	
	}

	@Override
	public Double[] calcularSoma(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException {
		if (vetor1.length != vetor2.length) throw new VetoresTamanhosDiferentesException("Vetores de Tamanhos Diferentes");
		
		Double[] vetorResultante = new Double[vetor1.length];

		for (int cont = 0; cont < vetor1.length; cont++) {
			vetorResultante[cont] = vetor1[cont] + vetor2[cont];
		}

		return vetorResultante;
	}

	@Override
	public Double[] calcularSubtracao(Double[] vetor1, Double[] vetor2) throws VetoresTamanhosDiferentesException {
if (vetor1.length != vetor2.length) throw new VetoresTamanhosDiferentesException("Vetores de Tamanhos Diferentes");
		
		Double[] vetorResultante = new Double[vetor1.length];

		for (int cont = 0; cont < vetor1.length; cont++) {
			vetorResultante[cont] = vetor1[cont] - vetor2[cont];
		}

		return vetorResultante;
	}

}
