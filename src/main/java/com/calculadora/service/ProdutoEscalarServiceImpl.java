package com.calculadora.service;

import java.util.List;

public class ProdutoEscalarServiceImpl implements ProdutoEscalarService {
	public static final int TAM_VETOR = 3;
	
	public Double calcularProduto(List<Double> vetor1, List<Double> vetor2) {
		Double produtoEscalar = 0.0;
		
		for (int contador = 0; contador < TAM_VETOR; contador++) {
			produtoEscalar += vetor1.get(contador) * vetor2.get(contador);
		}
		
		return produtoEscalar;
	}
}
