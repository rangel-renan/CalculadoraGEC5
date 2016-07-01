package com.calculadora.service;

import org.apache.commons.math3.complex.Complex;

import com.calculadora.model.Polinomio;
import com.calculadora.util.enums.TipoOperacao;

public interface PolinomioService {
	public Polinomio operacao(Polinomio firstPolinomio, Polinomio secondPolinomio, TipoOperacao tipoOperacao);
	public Polinomio derivada(Polinomio polinomio);
	public Complex getRaizes(Polinomio polinomio);
}
