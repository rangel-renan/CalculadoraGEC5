package com.calculadora.service;

import java.math.BigDecimal;

import com.calculadora.model.Financiamento;
import com.calculadora.util.enums.TipoPrestacao;

import javafx.collections.ObservableList;

public interface FinanciamentoService {

	public ObservableList<Financiamento> calcularFinanciamento(BigDecimal valorFinanciado, BigDecimal taxaJuros, BigDecimal numeroMeses,
			TipoPrestacao tipoPrestacao);
}
