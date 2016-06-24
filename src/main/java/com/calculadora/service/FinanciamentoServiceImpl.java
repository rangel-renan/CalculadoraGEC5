package com.calculadora.service;

import java.math.BigDecimal;
import java.math.MathContext;

import com.calculadora.model.Financiamento;
import com.calculadora.util.TipoPrestacao;

public class FinanciamentoServiceImpl implements FinanciamentoService {
	private final BigDecimal CEM = new BigDecimal("100");
	private final BigDecimal UM = BigDecimal.ONE;
	
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
	public BigDecimal tablePrice(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal totalParcelas) {
		taxaJuros = porcentagemEmDecimal(taxaJuros);
		return valorTotal.multiply((((taxaJuros.add(UM)).pow(totalParcelas.intValue())).multiply(taxaJuros))
				.divide((((taxaJuros.add(UM)).pow(totalParcelas.intValue())).subtract(UM)), MathContext.DECIMAL128));

	}

	// Calculo de parcelas Método Tabela Sac (Decrescente)
	public BigDecimal tableSAC(BigDecimal valorTotalOrcamento, BigDecimal taxaJuros, BigDecimal quantidadePeriodo,
			BigDecimal peridoACalcular) {
		taxaJuros = porcentagemEmDecimal(taxaJuros);
		BigDecimal valorAmortizacao = valorTotalOrcamento.divide(quantidadePeriodo, MathContext.DECIMAL128);
		return (((quantidadePeriodo.subtract(peridoACalcular)).add(UM)).multiply(taxaJuros)).multiply(valorAmortizacao);
	}
	
	private BigDecimal porcentagemEmDecimal(BigDecimal valor) {
		return valor.divide(CEM);
	}

}
