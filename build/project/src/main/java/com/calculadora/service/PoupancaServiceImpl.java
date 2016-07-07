package com.calculadora.service;

import java.math.BigDecimal;
import java.math.MathContext;

public class PoupancaServiceImpl implements PoupancaService {
	private final BigDecimal CEM = new BigDecimal("100");
	private final BigDecimal UM = BigDecimal.ONE;
	
	public BigDecimal calcularValorDepositos(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal numeroDepositos, BigDecimal duracao) {
		taxaJuros = taxaJuros.divide((numeroDepositos.multiply(CEM)), MathContext.DECIMAL128);
		return  (valorTotal.multiply(taxaJuros)).divide(((taxaJuros.add(UM).pow( (numeroDepositos.multiply(duracao)).intValue(), MathContext.DECIMAL128) ) .subtract(UM)), MathContext.DECIMAL128) ;
	}
	
	public BigDecimal calcularValorTotal(BigDecimal valorDepositos, BigDecimal taxaJuros, BigDecimal numeroDepositos, BigDecimal duracao) {
		taxaJuros = taxaJuros.divide((numeroDepositos.multiply(CEM)), MathContext.DECIMAL128);
		return  valorDepositos.divide(((taxaJuros)).divide(((taxaJuros.add(UM).pow( (numeroDepositos.multiply(duracao)).intValue(), MathContext.DECIMAL128) ) .subtract(UM)), MathContext.DECIMAL128), MathContext.DECIMAL128) ;
	}
	
}
