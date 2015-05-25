package com.calculadora.calculadora;

public class OperacoesBasicas {
	
	public static int operacaodeSoma(int primeiroNumero, int segundoNumero) {
		return (primeiroNumero + segundoNumero);
	}
	
	public static double operacaodeSoma(double primeiroNumero, double segundoNumero) {
		return (primeiroNumero + segundoNumero);
	}
	
	public static int operacaodeSubtracao(int primeiroNumero, int segundoNumero) {
		return (primeiroNumero - segundoNumero);
	}
	
	public static double operacaodeSubtracao(double primeiroNumero, double segundoNumero) {
		return (primeiroNumero - segundoNumero);
	}
	
	public static int operacaodeMultiplicacao(int multiplicando, int multiplicador) {
		return (multiplicando * multiplicador);
	}
	
	public static double operacaodeMultiplicacao(double multiplicando, double multiplicador) {
		return (multiplicando * multiplicador);
	}
	
	public static int operacaodeDivisao(int dividendo, int divisor) {
		return (dividendo / divisor);
	}
	
	public static double operacaodeDivisao(double dividendo, double divisor) {
		return (dividendo / divisor);
	}
	
	public static double raizQuadrada(double numero) {
		return Math.sqrt(numero);
	}
	
	public static double raizQuadrada(int numero) {
		return Math.sqrt(numero);
	}
}
