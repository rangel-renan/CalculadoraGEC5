package com.calculadora.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.calculadora.model.Emprestimo;

public class FinanceiraServiceImpl implements FinanceiraService {
	private final BigDecimal CEM = new BigDecimal("100");
	private final BigDecimal UM = BigDecimal.ONE;
	private final BigDecimal DOZE_MESES = new BigDecimal("12");
	
	public Double calcularLimiteRetirada(Double valorAtualInvestimento, Double prazoEmAnos, Double taxaMensal,
			Integer numRetiradasPorAno) {
		double t1, t2;
		valorAtualInvestimento = valorAtualInvestimento / 100;
		t1 = valorAtualInvestimento / prazoEmAnos;

		double b = (1 + t1);
		double e = prazoEmAnos * taxaMensal;

		t2 = Math.pow(b, e) - 1;

		return numRetiradasPorAno * (t1 / t2 + t1);
	}
	
	public Double calcularInvestimentoInicial(Double valorInvestPretendido, Double prazoEmAnos, Double taxaMensal, Double peridoAjustesPorAno) {
		double b, e;
		taxaMensal = taxaMensal / 100;
        b = (1 + taxaMensal/peridoAjustesPorAno);
        e = peridoAjustesPorAno * prazoEmAnos;
               
        return valorInvestPretendido / Math.pow(b, e);
	}
	
	/* Empréstimo - Juros*/
	public BigDecimal calcularJuros(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal peridoTotalEmMeses) {
		return valorTotal.multiply(porcentagemEmDecimal(taxaJuros)).multiply(peridoTotalEmMeses);
	}
	
	/* Empréstimo - Valor Total*/
	public BigDecimal calcularJurosComposto(BigDecimal valorTotal, BigDecimal taxaJuros, Integer peridoTotalEmMeses) {
		BigDecimal juro = (porcentagemEmDecimal(taxaJuros)).add(UM);
		return valorTotal.multiply((juro.pow(peridoTotalEmMeses)));
	}
	
	public BigDecimal calcularMontante(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal peridoTotalEmMeses) {
		return valorTotal.add(calcularJuros(valorTotal, taxaJuros, peridoTotalEmMeses));
	}
	
	public Double calcularValorPrestacao(Double valorTotal, Double taxaJuros, Double periodoFinanciamentoEmMeses) {
		taxaJuros = taxaJuros / 100;
		return valorTotal * (taxaJuros / (1 - Math.pow((1 + taxaJuros), -periodoFinanciamentoEmMeses)));
	}
	
	public Emprestimo calcularContaCartaoCredito(BigDecimal saldoCartaoCredito, BigDecimal taxaJuros, BigDecimal valorParcela) {
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
		
		return new Emprestimo(balancoInicial, meses, balancoInicial.add(totalJurosFinal), totalJurosFinal);
	}
	
	private BigDecimal porcentagemEmDecimal(BigDecimal valor) {
		return valor.divide(CEM);
	}
	
	public static void main(String[] args) {
		Emprestimo e = new FinanceiraServiceImpl().calcularContaCartaoCredito(new BigDecimal("500"), new BigDecimal("2"), new BigDecimal(50));
		System.out.println(e.getTotalMeses() + "\n" + e.getBalancoFinal() + "\n" + e.getTotalDeJuros());
		//System.out.println(new FinanceiraServiceImpl().calcularCartaoCredito(500.0, 2.0, 50.0));
	}

}
