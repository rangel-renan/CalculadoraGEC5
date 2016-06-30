package com.calculadora.service;

import java.math.BigDecimal;

public interface PoupancaService {
	public BigDecimal calcularValorDepositos(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal numeroDepositos, BigDecimal duracao);
	public BigDecimal calcularValorTotal(BigDecimal valorDepositos, BigDecimal taxaJuros, BigDecimal numeroDepositos, BigDecimal duracao);
}
