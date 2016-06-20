package com.calculadora.service;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import com.calculadora.model.Polinomio;
import com.calculadora.util.TipoOperacao;

public class PolinomioServiceImpl implements PolinomioService {

	@Override
	public Polinomio operacao(Polinomio firstPolinomio, Polinomio secondPolinomio, TipoOperacao tipoOperacao) {
		
		switch (tipoOperacao) {
			case SOMA:
				return soma(firstPolinomio, secondPolinomio);
			case SUBTRACAO:
				return subtracao(firstPolinomio, secondPolinomio);
			case MULTIPLICACAO:
				return multiplicacao(firstPolinomio, secondPolinomio);
			default:
				return null;
		}
	}
	
	private Polinomio soma(Polinomio firstPolinomio, Polinomio secondPolinomio) {
		PolynomialFunction firstPol = new PolynomialFunction(firstPolinomio.parseToDouble());
		return new Polinomio(firstPol.add(new PolynomialFunction(secondPolinomio.parseToDouble()))
										  .getCoefficients());
	}
	
	private Polinomio subtracao(Polinomio firstPolinomio, Polinomio secondPolinomio) {
		PolynomialFunction firstPol = new PolynomialFunction(firstPolinomio.parseToDouble());
		return new Polinomio(firstPol.subtract(new PolynomialFunction(secondPolinomio.parseToDouble()))
										  .getCoefficients());
	}
	
	private Polinomio multiplicacao(Polinomio firstPolinomio, Polinomio secondPolinomio) {
		PolynomialFunction firstPol = new PolynomialFunction(firstPolinomio.parseToDouble());
		return new Polinomio(firstPol.multiply(new PolynomialFunction(secondPolinomio.parseToDouble()))
										  .getCoefficients());
	}
	
}
