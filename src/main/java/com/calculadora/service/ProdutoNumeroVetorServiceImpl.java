package com.calculadora.service;

import java.util.ArrayList;
import java.util.List;

public class ProdutoNumeroVetorServiceImpl implements ProdutoNumeroVetorService {
	
	public List<Double> calcularProduto(Double numeroProduto, List<Double> listaValores) {
		
		List<Double> vetorResultante = new ArrayList<Double>();
		
		for (Double valor : listaValores) {
			vetorResultante.add(valor * numeroProduto);
		}
		
		return vetorResultante;
	}
	
}
