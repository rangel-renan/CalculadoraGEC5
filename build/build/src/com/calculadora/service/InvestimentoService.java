package com.calculadora.service;

import java.math.BigDecimal;

public interface InvestimentoService {
	
	public BigDecimal calcularInvestimentoInicial(BigDecimal valorInvestPretendido, BigDecimal prazoEmAnos,
			BigDecimal taxaMensal, BigDecimal peridoAjustesPorAno);
	public BigDecimal calcularValorFuturoInvestimento(BigDecimal valorInicial, BigDecimal prazoEmAnos,
			BigDecimal taxaMensal, BigDecimal peridoAjustesPorAno);
	public Double calcularTaxaJurosNominal(Double investimentoInicial, Double valorFinal, Double prazoEmAnos,
			Double peridoAjustesPorAno);
}
