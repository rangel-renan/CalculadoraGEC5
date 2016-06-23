package com.calculadora.service;

import java.math.BigDecimal;
import java.math.MathContext;

import com.calculadora.model.Emprestimo;
import com.calculadora.model.Financiamento;
import com.calculadora.util.TipoPrestacao;

@SuppressWarnings("unused")
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

	public Double calcularInvestimentoInicial(Double valorInvestPretendido, Double prazoEmAnos, Double taxaMensal,
			Double peridoAjustesPorAno) {
		double b, e;
		taxaMensal = taxaMensal / 100;
		b = (1 + taxaMensal / peridoAjustesPorAno);
		e = peridoAjustesPorAno * prazoEmAnos;

		return valorInvestPretendido / Math.pow(b, e);
	}

	/* Empréstimo - Juros */
	public BigDecimal calcularJuros(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal peridoTotalEmMeses) {
		return valorTotal.multiply(porcentagemEmDecimal(taxaJuros)).multiply(peridoTotalEmMeses);
	}

	/* Empréstimo - Valor Total */
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

	public Financiamento calcularFinanciamento(BigDecimal valorFinanciado, BigDecimal taxaJuros, BigDecimal numeroMeses,
			TipoPrestacao tipoPrestacao) {

		switch (tipoPrestacao) {
			case FIXAS:
				return financiamentoPrice(valorFinanciado, taxaJuros, numeroMeses);
			case DECRESCENTE:
				return financiamentoSac(valorFinanciado, taxaJuros, numeroMeses);
			default:
				return null;
		}

	}

	private Financiamento financiamentoPrice(BigDecimal valorFinanciado, BigDecimal taxaJuros, BigDecimal numeroMeses) {
		Financiamento financiamento = new Financiamento();
		BigDecimal totalJuros = new BigDecimal("0");
		BigDecimal totalAmort = new BigDecimal("0");
		BigDecimal totalParc = new BigDecimal("0");
		BigDecimal parcela = tablePrice(valorFinanciado, taxaJuros, numeroMeses);
		taxaJuros = porcentagemEmDecimal(taxaJuros);
		BigDecimal saldoDevedor;
		BigDecimal amortizacao;

		for (int i = 0; i < numeroMeses.intValue(); i++) {
			amortizacao = parcela.subtract((valorFinanciado.multiply(taxaJuros)));
			saldoDevedor = valorFinanciado.subtract(amortizacao);

			financiamento.addParcela(parcela);
			financiamento.addAmortizacao(amortizacao);
			financiamento.addJuro(valorFinanciado.multiply(taxaJuros));
			financiamento.addSaldoDevedor(saldoDevedor);

			totalJuros = totalJuros.add((valorFinanciado.multiply(taxaJuros)));
			totalAmort = totalAmort.add(amortizacao);
			totalParc = totalParc.add(parcela);
			valorFinanciado = valorFinanciado.subtract(amortizacao);
		}

		financiamento.setParcela(parcela.doubleValue());

		return financiamento;
	}
	
	private Financiamento financiamentoSac(BigDecimal valorFinanciado, BigDecimal taxaJuros, BigDecimal numeroMeses) {
		Financiamento financiamento = new Financiamento();
		BigDecimal amortizacao = valorFinanciado.divide(numeroMeses, MathContext.DECIMAL128);
		
		BigDecimal totalJuros = new BigDecimal("0");
		BigDecimal totalAmort = new BigDecimal("0");
		BigDecimal totalParc = new BigDecimal("0");
		BigDecimal parcela;
		taxaJuros = porcentagemEmDecimal(taxaJuros);
		BigDecimal saldoDevedor;
		
		for (int i = 0; i < numeroMeses.intValue(); i++) {
			parcela = amortizacao.add((valorFinanciado.multiply(taxaJuros)));
			saldoDevedor = valorFinanciado.subtract(amortizacao);

			financiamento.addParcela(parcela);
			financiamento.addAmortizacao(amortizacao);
			financiamento.addJuro(valorFinanciado.multiply(taxaJuros));
			financiamento.addSaldoDevedor(saldoDevedor);

			totalJuros = totalJuros.add((valorFinanciado.multiply(taxaJuros)));
			totalAmort = totalAmort.add(amortizacao);
			totalParc = totalParc.add(parcela);
			valorFinanciado = valorFinanciado.subtract(amortizacao);
		}
		
		return financiamento;
	}

	// Calculo de parcelas Método Tabela Price (Preço FIxo)
	private BigDecimal tablePrice(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal totalParcelas) {
		taxaJuros = porcentagemEmDecimal(taxaJuros);
		return valorTotal.multiply((((taxaJuros.add(UM)).pow(totalParcelas.intValue())).multiply(taxaJuros))
				.divide((((taxaJuros.add(UM)).pow(totalParcelas.intValue())).subtract(UM)), MathContext.DECIMAL128));

	}

	// Calculo de parcelas Método Tabela Sac (Decrescente)
	private BigDecimal tableSAC(BigDecimal valorTotalOrcamento, BigDecimal taxaJuros, BigDecimal quantidadePeriodo,
			BigDecimal peridoACalcular) {
		taxaJuros = porcentagemEmDecimal(taxaJuros);
		BigDecimal valorAmortizacao = valorTotalOrcamento.divide(quantidadePeriodo, MathContext.DECIMAL128);
		return (((quantidadePeriodo.subtract(peridoACalcular)).add(UM)).multiply(taxaJuros)).multiply(valorAmortizacao);
	}

	public Emprestimo calcularEmprestimo(BigDecimal saldoCartaoCredito, BigDecimal taxaJuros,
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

		return new Emprestimo(balancoInicial, meses, balancoInicial.add(totalJurosFinal), totalJurosFinal);
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
		// System.out.println(new
		// FinanceiraServiceImpl().calcularJurosComposto(new
		// BigDecimal("30000"), new BigDecimal("1.2"), 60));
		// System.out.println(new FinanceiraServiceImpl().tablePrice(new
		// BigDecimal("30000"), new BigDecimal("3.42"), new BigDecimal("12")));
		// new FinanceiraServiceImpl().calcularEmprestimo(30000.0, 3.42, 12.0);
//		Financiamento f = new FinanceiraServiceImpl().calcularFinanciamento(new BigDecimal("30000"),
//				new BigDecimal("3.42"), new BigDecimal("12"), TipoPrestacao.DECRESCENTE);
//
//		for (Double b1 : f.getJurosAoMes()) {
//			System.out.println(b1 + "\n");
//		}
//		System.out.println();
//		for (Double b1 : f.getAmortizacaoAoMes()) {
//			System.out.println(b1 + "\n");
//		}
//		System.out.println();
//		for (Double b1 : f.getSaldoDevedorAoMes()) {
//			System.out.println(b1 + "\n");
//		}
//		System.out.println();
//		for (Double b1 : f.getParcelas()) {
//			System.out.println(b1 + "\n");
//		}
//		System.out.println();
	}

}
