package com.calculadora.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;

import com.calculadora.model.Financiamento;
import com.calculadora.util.enums.TipoPrestacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FinanciamentoServiceImpl implements FinanciamentoService {
	private final BigDecimal CEM = new BigDecimal("100");
	private final BigDecimal UM = BigDecimal.ONE;
	
	public ObservableList<Financiamento> calcularFinanciamento(BigDecimal valorFinanciado, BigDecimal taxaJuros, BigDecimal numeroMeses,
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

	private ObservableList<Financiamento> financiamentoPrice(BigDecimal valorFinanciado, BigDecimal taxaJuros, BigDecimal numeroMeses) {
		ObservableList<Financiamento> listaFinanciamento = FXCollections.observableArrayList();
		NumberFormat formatter= NumberFormat.getInstance();
		
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		
		BigDecimal totalJuros = new BigDecimal("0");
		BigDecimal totalAmort = new BigDecimal("0");
		BigDecimal totalParc = new BigDecimal("0");
		BigDecimal parcela = tablePrice(valorFinanciado, taxaJuros, numeroMeses);
		taxaJuros = porcentagemEmDecimal(taxaJuros);
		BigDecimal saldoDevedor;
		BigDecimal amortizacao;
		int numeroParcela = 1;
		
		for (int i = 0; i < numeroMeses.intValue(); i++) {
			amortizacao = parcela.subtract((valorFinanciado.multiply(taxaJuros)));
			saldoDevedor = valorFinanciado.subtract(amortizacao);
			
			listaFinanciamento.add(new Financiamento(Integer.toString(numeroParcela), formatter.format(parcela.doubleValue()), formatter.format(amortizacao.doubleValue()), 
					formatter.format((valorFinanciado.multiply(taxaJuros)).doubleValue()), formatter.format(saldoDevedor.doubleValue())));
			
			totalJuros = totalJuros.add((valorFinanciado.multiply(taxaJuros)));
			totalAmort = totalAmort.add(amortizacao);
			totalParc = totalParc.add(parcela);
			valorFinanciado = valorFinanciado.subtract(amortizacao);
			numeroParcela++;
		}

		return listaFinanciamento;
	}
	
	private ObservableList<Financiamento> financiamentoSac(BigDecimal valorFinanciado, BigDecimal taxaJuros, BigDecimal numeroMeses) {
		ObservableList<Financiamento> listaFinanciamento = FXCollections.observableArrayList();
		BigDecimal amortizacao = valorFinanciado.divide(numeroMeses, MathContext.DECIMAL128);
		NumberFormat formatter= NumberFormat.getInstance();
		
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		
		BigDecimal totalJuros = new BigDecimal("0");
		BigDecimal totalAmort = new BigDecimal("0");
		BigDecimal totalParc = new BigDecimal("0");
		BigDecimal parcela;
		taxaJuros = porcentagemEmDecimal(taxaJuros);
		BigDecimal saldoDevedor;
		int numeroParcela = 1;
		
		for (int i = 0; i < numeroMeses.intValue(); i++) {
			parcela = amortizacao.add((valorFinanciado.multiply(taxaJuros)));
			saldoDevedor = valorFinanciado.subtract(amortizacao);
			
			listaFinanciamento.add(new Financiamento(Integer.toString(numeroParcela), formatter.format(parcela.doubleValue()), formatter.format(amortizacao.doubleValue()), 
					formatter.format(taxaJuros.doubleValue()), formatter.format(saldoDevedor.doubleValue())));
			
			totalJuros = totalJuros.add((valorFinanciado.multiply(taxaJuros)));
			totalAmort = totalAmort.add(amortizacao);
			totalParc = totalParc.add(parcela);
			valorFinanciado = valorFinanciado.subtract(amortizacao);
			numeroParcela++;
		}
		
		return listaFinanciamento;
	}

	// Calculo de parcelas Método Tabela Price (Preço FIxo)
	public BigDecimal tablePrice(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal totalParcelas) {
		System.out.println(totalParcelas);
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
