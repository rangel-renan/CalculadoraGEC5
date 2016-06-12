package com.calculadora.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface OperacoesBasicasService {
	
	public BigDecimal calcular(BigDecimal valor1, BigDecimal valor2, String operacao);
	public BigDecimal calcularRaizQuadrada(BigDecimal valor);
	public BigDecimal calcularRegraTres(BigDecimal valorA, BigDecimal valorB, BigDecimal valorC) throws NumberFormatException;
	public BigDecimal calcularPI();
	public BigDecimal changeSinal(BigDecimal valor);
	public BigDecimal valorEuler();
	public boolean isPrimo(BigInteger valor);
}
