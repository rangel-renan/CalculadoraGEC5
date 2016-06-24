package com.calculadora.service;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import com.calculadora.model.Polinomio;
import com.calculadora.util.PolinomioFormatoInvalidoException;
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
	
	public Polinomio derivada(Polinomio polinomio) {
		PolynomialFunction derivada = new PolynomialFunction(polinomio.parseToDouble()).polynomialDerivative();
		return new Polinomio(derivada.getCoefficients());
	}
	
	public static void main(String[] args) {
		
		try {
			System.out.println(new PolinomioServiceImpl().derivada(new Polinomio("log(20)")));
		} catch (PolinomioFormatoInvalidoException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
