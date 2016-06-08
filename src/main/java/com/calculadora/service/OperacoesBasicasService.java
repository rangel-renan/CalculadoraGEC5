package com.calculadora.service;

import java.math.BigDecimal;

public interface OperacoesBasicasService {
	
	public BigDecimal calcular(BigDecimal valor1, BigDecimal valor2, String operacao);
	public BigDecimal calcularRaizQuadrada(BigDecimal valor);
	public BigDecimal calcularPI();
	public BigDecimal changeSinal(BigDecimal valor);
	public BigDecimal valorEuler();
}
