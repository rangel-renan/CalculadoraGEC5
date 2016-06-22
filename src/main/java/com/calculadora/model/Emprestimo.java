package com.calculadora.model;

import java.math.BigDecimal;

public class Emprestimo {
	private BigDecimal balancoInicial;
	private int totalMeses;
	private BigDecimal balancoFinal;
	private BigDecimal totalDeJuros;
	
	public Emprestimo() {
	}
	
	public Emprestimo(BigDecimal balancoInicial, int totalMeses, BigDecimal balancoFinal, BigDecimal totalDeJuros) {
		this.balancoInicial = balancoInicial;
		this.totalMeses = totalMeses;
		this.balancoFinal = balancoFinal;
		this.totalDeJuros = totalDeJuros;
	}

	public BigDecimal getBalancoInicial() {
		return balancoInicial;
	}
	public void setBalancoInicial(BigDecimal balancoInicial) {
		this.balancoInicial = balancoInicial;
	}
	public int getTotalMeses() {
		return totalMeses;
	}
	public void setTotalMeses(int totalMeses) {
		this.totalMeses = totalMeses;
	}
	public BigDecimal getBalancoFinal() {
		return balancoFinal;
	}
	public void setBalancoFinal(BigDecimal balancoFinal) {
		this.balancoFinal = balancoFinal;
	}
	public void setTotalDeJuros(BigDecimal totalDeJuros) {
		this.totalDeJuros = totalDeJuros;
	}
	public BigDecimal getTotalDeJuros() {
		return totalDeJuros;
	}
}
