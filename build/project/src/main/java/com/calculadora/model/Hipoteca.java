package com.calculadora.model;

import java.math.BigDecimal;

public class Hipoteca {
	private BigDecimal valorTotal;
	private BigDecimal valorPrestacao;
	private BigDecimal juros;
	
	public Hipoteca() {
	}

	public Hipoteca(BigDecimal valorTotal, BigDecimal valorPrestacao, BigDecimal juros) {
		this.valorTotal = valorTotal;
		this.valorPrestacao = valorPrestacao;
		this.juros = juros;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorPrestacao() {
		return valorPrestacao;
	}

	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	public BigDecimal getJuros() {
		return juros;
	}

	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}
	
}
