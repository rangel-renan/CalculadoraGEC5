package com.calculadora.service;

import java.math.BigDecimal;
import java.math.MathContext;

public class InvestimentoServiceImpl implements InvestimentoService {
	private final BigDecimal CEM = new BigDecimal("100");
	private final BigDecimal UM = BigDecimal.ONE;

	public BigDecimal calcularInvestimentoInicial(BigDecimal valorInvestPretendido, BigDecimal prazoEmAnos,
			BigDecimal taxaMensal, BigDecimal peridoAjustesPorAno) {
		taxaMensal = porcentagemEmDecimal(taxaMensal);
		BigDecimal b = (taxaMensal.divide(peridoAjustesPorAno, MathContext.DECIMAL128)).add(UM);
		BigDecimal e = peridoAjustesPorAno.multiply(prazoEmAnos);
		return valorInvestPretendido.divide((b.pow(e.intValue())), MathContext.DECIMAL128);
	}

	public BigDecimal calcularValorFuturoInvestimento(BigDecimal valorInicial, BigDecimal prazoEmAnos,
			BigDecimal taxaMensal, BigDecimal peridoAjustesPorAno) {
		BigDecimal juro = taxaMensal.divide((peridoAjustesPorAno.multiply(CEM)), MathContext.DECIMAL128);
		return valorInicial.multiply((juro.add(UM)).pow((peridoAjustesPorAno.multiply(prazoEmAnos)).intValue()));
	}

	public Double calcularTaxaJurosNominal(Double investimentoInicial, Double valorFinal, Double prazoEmAnos,
			Double peridoAjustesPorAno) {
		return ((prazoEmAnos
				* (Math.pow((valorFinal / investimentoInicial), (1 / (peridoAjustesPorAno * prazoEmAnos)))))
				- prazoEmAnos) * 100;
	}

	private BigDecimal porcentagemEmDecimal(BigDecimal valor) {
		return valor.divide(CEM);
	}

}
