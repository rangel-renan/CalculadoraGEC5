package com.calculadora.calculadora;

import java.util.Scanner;

public class OperacoesBasicas {
	private double valorA;
	private double valorB;
	
	public double getValorA() {
		return valorA;
	}

	public void setValorA(double valorA) {
		this.valorA = valorA;
	}

	public double getValorB() {
		return valorB;
	}

	public void setValorB(double valorB) {
		this.valorB = valorB;
	}

	public void lerValores() {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Entre com o Valor de A: ");
		setValorA(input.nextDouble());
		System.out.print("Entre com o Valor de B: ");
		setValorB(input.nextDouble());
	}
	
	public double operacaodeSoma() {
		return (getValorA() + getValorB());
	}
	
	public double operacaodeSubtracao() {
		return (getValorA() - getValorB());
	}
	
	public double operacaodeMultiplicacao() {
		return (getValorA() * getValorB());
	}
	
	public double operacaodeDivisao() {
		return (getValorA() / getValorB());
	}
	
	public double raizQuadrada() {
		return Math.sqrt(getValorA());
	}
}
