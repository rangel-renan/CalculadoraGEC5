package com.calculadora.service;

import java.math.BigDecimal;
import java.math.MathContext;

import com.calculadora.model.CartaoCredito;
import com.calculadora.model.Hipoteca;

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

	public BigDecimal calcularValorPrestacao(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal periodoFinanciamentoEmMeses) {
		taxaJuros = porcentagemEmDecimal(taxaJuros);
		BigDecimal divisor = BigDecimal.ONE.subtract(new BigDecimal(Math.pow((taxaJuros.add(UM).doubleValue()), -periodoFinanciamentoEmMeses.intValue())));
		return valorTotal.multiply((taxaJuros.divide(divisor, MathContext.DECIMAL128)));
	}
	
	public Hipoteca calcularHipoteca(BigDecimal precoDoImovel, BigDecimal taxaJuros, BigDecimal prazo) {
		BigDecimal juros = calcularJuros(precoDoImovel, taxaJuros, prazo);
		return new Hipoteca(precoDoImovel.add(juros), calcularValorPrestacao(precoDoImovel, taxaJuros, prazo), juros);
	}
	
	public CartaoCredito calcularCartaoCredito(BigDecimal saldoCartaoCredito, BigDecimal taxaJuros,
			BigDecimal valorParcela) {
		taxaJuros = (porcentagemEmDecimal(taxaJuros)).divide(DOZE_MESES, MathContext.DECIMAL128);
		BigDecimal balancoInicial = saldoCartaoCredito;
		BigDecimal pagamentoMinimo = taxaJuros.multiply(saldoCartaoCredito);

		if (pagamentoMinimo.compareTo(valorParcela) > 0) {
			System.out.println("Your monthly payment is less than the monthly interest charged by this card.");
			return null;
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

	private BigDecimal porcentagemEmDecimal(BigDecimal valor) {
		return valor.divide(CEM);
	}

	public static void main(String[] args) {
		// Emprestimo e = new
		// FinanceiraServiceImpl().calcularContaCartaoCredito(new
		// BigDecimal("500"), new BigDecimal("2"), new BigDecimal(50));
		// System.out.println(e.getTotalMeses() + "\n" + e.getBalancoFinal() +
		// "\n" + e.getTotalDeJuros());
		
		// System.out.println(new FinanceiraServiceImpl().tablePrice(new
		// BigDecimal("30000"), new BigDecimal("3.42"), new BigDecimal("12")));
		// new FinanceiraServiceImpl().calcularEmprestimo(30000.0, 3.42, 12.0);
		Hipoteca h = new FinanceiraServiceImpl().calcularHipoteca(new BigDecimal("50000"), new BigDecimal("2"), new BigDecimal("45"));
		System.out.println(h.getJuros());
		System.out.println(h.getValorTotal());
		System.out.println(h.getValorPrestacao());
	}

}
