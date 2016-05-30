package com.calculadora.service;

import java.util.List;

public class CossenoEntreVetoresServiceImpl implements CossenoEntreVetoresService {
	
	@Override
	public Double calcularCosseno(List<Double> vetor1, List<Double> vetor2) {
		return calculaCosseno(vetor1, vetor2);
	}
	
	public Double calculaCosseno(List<Double> vetor1, List<Double> vetor2) {
		Double numeroResult;
		ModuloVetorService moduloVetorService = new ModuloVetorServiceImpl();
		ProdutoEscalarService produtoEscalarService = new ProdutoEscalarServiceImpl();
		
		Double produtoEscalar = produtoEscalarService.calcularProduto(vetor1, vetor2);
		
		Double moduloPrimeiroVetor = moduloVetorService.calculaModulo(vetor1);
		Double moduloSegundoVetor = moduloVetorService.calculaModulo(vetor2);
		
		numeroResult = produtoEscalar / (moduloPrimeiroVetor * moduloSegundoVetor);
		
		return numeroResult;
	}
	
}
