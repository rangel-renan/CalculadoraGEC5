package com.calculadora.service;

import java.util.List;

public class ProjecaoDeUmVetorServiceImpl implements ProjecaoDeUmVetorService {
	
	public List<Double> calcularProjecao(List<Double> vetor1, List<Double> vetor2) {
		return calcProjecao(vetor1, vetor2);
	}

	public List<Double> calcProjecao(List<Double> vetor1, List<Double> vetor2) {
		ModuloVetorService moduloVetorService = new ModuloVetorServiceImpl();
		ProdutoNumeroVetorService produtoNumeroVetor = new ProdutoNumeroVetorServiceImpl();
		ProdutoEscalarService produtoEscalarService = new ProdutoEscalarServiceImpl();

		Double produtoEscalar = produtoEscalarService.calcularProduto(vetor1,
				vetor2);
		Double modulo = moduloVetorService.calculaModulo(vetor2);
		Double numeroProduto = produtoEscalar / (modulo * modulo);

		return produtoNumeroVetor.calcularProduto(numeroProduto, vetor2);
	}

}
