package com.calculadora.service;

import com.calculadora.model.Polinomio;
import com.calculadora.util.enums.TipoOperacao;

public interface PolinomioService {
	public Polinomio operacao(Polinomio firstPolinomio, Polinomio secondPolinomio, TipoOperacao tipoOperacao);
}
