package com.calculadora.service;

import java.math.BigDecimal;
import java.math.MathContext;

import com.calculadora.model.CartaoCredito;
import com.calculadora.model.Hipoteca;
import com.calculadora.util.excessoes.PagamentoMinimoMaiorParcelaException;

public class FinanceiraServiceImpl implements FinanceiraService {
	private final BigDecimal CEM = new BigDecimal("100");
	private final BigDecimal UM = BigDecimal.ONE;
	private final BigDecimal DOZE_MESES = new BigDecimal("12");

	public BigDecimal calcularJuros(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal peridoTotalEmMeses) {
		return valorTotal.multiply(porcentagemEmDecimal(taxaJuros)).multiply(peridoTotalEmMeses);
	}

	public BigDecimal calcularJurosComposto(BigDecimal valorTotal, BigDecimal taxaJuros, Integer peridoTotalEmMeses) {
		BigDecimal juro = (porcentagemEmDecimal(taxaJuros)).add(UM);
		return valorTotal.multiply((juro.pow(peridoTotalEmMeses)));
	}

	public BigDecimal calcularValorPrestacao(BigDecimal valorTotal, BigDecimal taxaJuros,
			BigDecimal periodoFinanciamentoEmMeses) {
		taxaJuros = porcentagemEmDecimal(taxaJuros);
		BigDecimal divisor = BigDecimal.ONE.subtract(
				new BigDecimal(Math.pow((taxaJuros.add(UM).doubleValue()), -periodoFinanciamentoEmMeses.doubleValue())));
		return valorTotal.multiply((taxaJuros.divide(divisor, MathContext.DECIMAL128)));
	}

	public Hipoteca calcularHipoteca(BigDecimal precoDoImovel, BigDecimal taxaJuros, BigDecimal prazo) {
		BigDecimal juros = calcularJuros(precoDoImovel, taxaJuros, prazo);
		return new Hipoteca(precoDoImovel.add(juros), calcularValorPrestacao(precoDoImovel, taxaJuros, prazo), juros);
	}

	public CartaoCredito calcularCartaoCredito(BigDecimal saldoCartaoCredito, BigDecimal taxaJuros,
			BigDecimal valorParcela) throws PagamentoMinimoMaiorParcelaException {
		taxaJuros = (porcentagemEmDecimal(taxaJuros)).divide(DOZE_MESES, MathContext.DECIMAL128);
		BigDecimal balancoInicial = saldoCartaoCredito;
		BigDecimal pagamentoMinimo = taxaJuros.multiply(saldoCartaoCredito);

		if (pagamentoMinimo.compareTo(valorParcela) > 0) {
			throw new PagamentoMinimoMaiorParcelaException("O Pagamento mensal é menor que o Valor da parcela.");
		}

		Integer meses = 0;
		BigDecimal totalJurosFinal = BigDecimal.ZERO;
		while (saldoCartaoCredito.compareTo(BigDecimal.ZERO) > 0) {
			meses++;
			totalJurosFinal = totalJurosFinal.add((saldoCartaoCredito.multiply(taxaJuros)));
			saldoCartaoCredito = saldoCartaoCredito.multiply((taxaJuros.add(UM)));
			saldoCartaoCredito = saldoCartaoCredito.subtract(valorParcela);
		}

		return new CartaoCredito(balancoInicial, meses, balancoInicial.add(totalJurosFinal), totalJurosFinal);
	}
	
	public BigDecimal calcularAnuidade(BigDecimal pagamentoMensal, BigDecimal taxaAnual, BigDecimal periodo) {
		taxaAnual = porcentagemEmDecimal(taxaAnual);
		return (pagamentoMensal.divide((taxaAnual.divide(DOZE_MESES, MathContext.DECIMAL128)), MathContext.DECIMAL128)).multiply((UM.subtract(new BigDecimal((Math.pow((UM.add((taxaAnual.divide(DOZE_MESES, MathContext.DECIMAL128)))).doubleValue(), -periodo.doubleValue()))))));
	}
	
	private BigDecimal porcentagemEmDecimal(BigDecimal valor) {
		return valor.divide(CEM);
	}
	
}
