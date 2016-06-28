package com.calculadora.service;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

import com.calculadora.model.Polinomio;
import com.calculadora.util.enums.TipoOperacao;
import com.calculadora.util.excessoes.PolinomioFormatoInvalidoException;

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
		return new Polinomio(firstPol.add(new PolynomialFunction(secondPolinomio.parseToDouble())).getCoefficients());
	}

	private Polinomio subtracao(Polinomio firstPolinomio, Polinomio secondPolinomio) {
		PolynomialFunction firstPol = new PolynomialFunction(firstPolinomio.parseToDouble());
		return new Polinomio(
				firstPol.subtract(new PolynomialFunction(secondPolinomio.parseToDouble())).getCoefficients());
	}

	private Polinomio multiplicacao(Polinomio firstPolinomio, Polinomio secondPolinomio) {
		PolynomialFunction firstPol = new PolynomialFunction(firstPolinomio.parseToDouble());
		return new Polinomio(
				firstPol.multiply(new PolynomialFunction(secondPolinomio.parseToDouble())).getCoefficients());
	}

	public Polinomio derivada(Polinomio polinomio) {
		PolynomialFunction derivada = new PolynomialFunction(polinomio.parseToDouble()).polynomialDerivative();
		return new Polinomio(derivada.getCoefficients());
	}

	public Complex getRaizes(Polinomio polinomio) {
		return new LaguerreSolver().solveComplex(polinomio.parseToDouble(), 0);
	}

	public static void main(String[] args) {
		
		try {
			System.out.println(new PolinomioServiceImpl().getRaizes(new Polinomio("3x^2 + 3x + 2")));
		} catch (PolinomioFormatoInvalidoException e1) {
			e1.printStackTrace();
		}

	}

}
