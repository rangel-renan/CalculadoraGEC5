package com.calculadora.matriz;

import java.util.List;

public class Determinante {
	private List<List<Double>> matriz;
	private Integer numeroLinhas;
	private Integer numeroColunas;
	private Double result;
	
	public Determinante() {
	}
	
	public List<List<Double>> getMatriz() {
		return matriz;
	}

	public void setMatriz(List<List<Double>> matriz) {
		this.matriz = matriz;
	}

	public Integer getNumeroLinhas() {
		return numeroLinhas;
	}

	public void setNumeroLinhas(Integer numeroLinhas) {
		this.numeroLinhas = numeroLinhas;
	}

	public Integer getNumeroColunas() {
		return numeroColunas;
	}

	public void setNumeroColunas(Integer numeroColunas) {
		this.numeroColunas = numeroColunas;
	}
	
	public void setResult(Double result) {
		this.result = result;
	}
	
	public Double getResult() {
		return result;
	}
	
}