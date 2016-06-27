package com.calculadora.service;

import java.math.BigDecimal;

import com.calculadora.model.Financiamento;
import com.calculadora.util.enums.TipoPrestacao;

public interface FinanciamentoService {

	public Financiamento calcularFinanciamento(BigDecimal valorFinanciado, BigDecimal taxaJuros, BigDecimal numeroMeses,
			TipoPrestacao tipoPrestacao);
}
