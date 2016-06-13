package com.calculadora.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import com.calculadora.util.TipoOperacao;

public class OperacoesBasicasServiceImpl implements OperacoesBasicasService {
	
	@Override
	public BigDecimal calcular(BigDecimal valor1, BigDecimal valor2, String operacao) {
		TipoOperacao tipoOperacao = TipoOperacao.getOperacao(operacao);
		
		switch (tipoOperacao) {
			case SOMA:
				return operacaodeSoma(valor1, valor2);
			case SUBTRACAO:
				return operacaodeSubtracao(valor1, valor2);
			case MULTIPLICACAO:
				return operacaodeMultiplicacao(valor1, valor2);
			case DIVISAO:
				return operacaodeDivisao(valor1, valor2);
			case RESTO:
				return operacaoResto(valor1, valor2);
			case ELEVADO_Y:
				return operacaoElevadoY(valor1, valor2);
			case RAIZ_QUADRADA_Y:
				return operacaoRaizQuadradoY(valor1, valor2);
			default:
				return null;
		}
		
	}
	
	public BigDecimal calcularRaizQuadrada(BigDecimal valor) {
		return operacaoRaizQuadrada(valor);
	}
	
	public BigDecimal calcularPI() {
		return new BigDecimal(Math.PI);
	}
	
	public BigDecimal changeSinal(BigDecimal valor) {
		return valor.negate();
	}

	public BigDecimal valorEuler() {
		return new BigDecimal(Math.E);
	}
	
	public BigDecimal calcularRegraTres(BigDecimal valorA, BigDecimal valorB, BigDecimal valorC) throws NumberFormatException {
		return (valorB.multiply(valorC)).divide(valorA, MathContext.DECIMAL128);
	}

	public boolean isPrimo(BigInteger valor) {
		return valor.isProbablePrime(1);
	}
	
	public BigDecimal calcularElevadoAoCubo(BigDecimal valor) {
		return new BigDecimal(Math.pow(valor.doubleValue(), 3.0));
	}

	public BigDecimal calcularElevadoAoQuadrado(BigDecimal valor) {
		return new BigDecimal(Math.pow(valor.doubleValue(), 2.0));
	}
	
	public BigDecimal calcularFatorial(BigDecimal n, BigDecimal acc) {
		if (n.equals(BigDecimal.ONE)) {
			return acc;
	    }
        BigDecimal lessOne = n.subtract(BigDecimal.ONE);
        return calcularFatorial(lessOne, acc.multiply(lessOne));
	}
	
	public BigDecimal calcularDms(BigDecimal valor) {
        BigDecimal in =  valor;
        BigDecimal part = valor.remainder(BigDecimal.ONE);
        BigDecimal numberCem = new BigDecimal(100);
        return (((((in.multiply(numberCem)).divide(numberCem)).add(part)).multiply(new BigDecimal(60))).divide(numberCem));
	}
	
	public BigDecimal calcularDezElevadoX(BigDecimal valor) {
		return new BigDecimal(Math.pow(10.0, valor.doubleValue()));
	}

	
	public BigDecimal calcularLog(BigDecimal valor) {
		return new BigDecimal(Math.log10(valor.doubleValue()));
	}

	public BigDecimal calcularLn(BigDecimal valor) {
		return new BigDecimal(Math.log(valor.doubleValue()));
	}
	
	private BigDecimal operacaodeSoma(BigDecimal valor1, BigDecimal valor2) {
		return valor1.add(valor2);
	}
	
	private BigDecimal operacaodeSubtracao(BigDecimal valor1, BigDecimal valor2) {
		return valor1.subtract(valor2);
	}
	
	private BigDecimal operacaodeMultiplicacao(BigDecimal valor1, BigDecimal valor2) {
		return valor1.multiply(valor2);
	}
	
	private BigDecimal operacaodeDivisao(BigDecimal valor1, BigDecimal valor2) {
		return valor1.divide(valor2, MathContext.DECIMAL128);
	}
	
	private BigDecimal operacaoRaizQuadrada(BigDecimal valor1) {
		return new BigDecimal(Math.sqrt(valor1.doubleValue()));
	}

	public BigDecimal operacaoResto(BigDecimal valor, BigDecimal divisor) {
		return valor.remainder(divisor);
	}
	
	public BigDecimal operacaoElevadoY(BigDecimal valor, BigDecimal elevado) {
		return new BigDecimal(Math.pow(valor.doubleValue(), elevado.doubleValue()));
	}
	
	public BigDecimal operacaoRaizQuadradoY(BigDecimal valor, BigDecimal yRoot) {
		return new BigDecimal(Math.pow(yRoot.doubleValue(), 1 / valor.doubleValue()));
	}
	
	@Override
	public BigDecimal calcularUnder(BigDecimal valor) {
		return BigDecimal.ONE.divide(valor, MathContext.DECIMAL128);
	}	

}
