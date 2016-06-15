package com.calculadora.service;

import com.calculadora.util.TipoOperacao;

public interface MatrizService {
	
	public Double[][] operacaoMatrizes(Double[][] firstMatriz, Double[][] secondMatriz, TipoOperacao tipoOperacao);
	public Double calcularDeterminante(Double[][] matriz);
	public Double[][] calcularMatrizTriangular(Double[][] matriz);
	public Double[][] calcularTransposta(Double[][] matriz);
	public Double[][] calcularMatrizInversa(Double[][] matriz);
	public Double[][] calcularMatrizAdjunta(Double[][] matriz);
	public Double[][] multiplicarPor(Double[][] matriz, Double valor);
	public Double[][] elevarPor(Double[][] matriz, Double valor);
}
