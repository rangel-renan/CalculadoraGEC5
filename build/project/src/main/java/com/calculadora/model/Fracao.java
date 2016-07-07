package com.calculadora.model;

import java.math.BigDecimal;

public class Fracao {
	private BigDecimal numerador; 
	private BigDecimal denominador;
	
	public Fracao() {
	}
	
	public Fracao(BigDecimal numerador, BigDecimal denominador) {
		this.numerador = numerador;
		this.denominador = denominador;
	}

	public BigDecimal getNumerador() {
		return numerador;
	}

	public void setNumerador(BigDecimal numerador) {
		this.numerador = numerador;
	}

	public BigDecimal getDenominador() {
		return denominador;
	}

	public void setDenominador(BigDecimal denominador) {
		if (!denominador.equals(new BigDecimal("0")))
			this.denominador = denominador;
	}
	
	
	
}
