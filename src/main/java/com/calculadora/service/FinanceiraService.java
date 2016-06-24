package com.calculadora.service;

import java.math.BigDecimal;

import com.calculadora.model.CartaoCredito;
import com.calculadora.model.Hipoteca;
import com.calculadora.util.PagamentoMinimoMaiorParcelaException;

public interface FinanceiraService {
	
	public BigDecimal calcularJuros(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal peridoTotalEmMeses);
	public BigDecimal calcularJurosComposto(BigDecimal valorTotal, BigDecimal taxaJuros, Integer peridoTotalEmMeses);
	public BigDecimal calcularValorPrestacao(BigDecimal valorTotal, BigDecimal taxaJuros, BigDecimal periodoFinanciamentoEmMeses);
	public Hipoteca calcularHipoteca(BigDecimal precoDoImovel, BigDecimal taxaJuros, BigDecimal prazo);
	public CartaoCredito calcularCartaoCredito(BigDecimal saldoCartaoCredito, BigDecimal taxaJuros, BigDecimal valorParcela) throws PagamentoMinimoMaiorParcelaException;
}
