package com.calculadora.raizquadra;

import java.util.Scanner;

public class RaizQuadrada {
	
	private int number;
	private int resultadoRaiz;
	private Scanner input = new Scanner(System.in);

	public int getResultadoRaiz() {
		return resultadoRaiz;
	}

	public void setResultadoRaiz(int resultadoRaiz) {
		this.resultadoRaiz = resultadoRaiz;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double raizQuadrada(){			
		
		System.out.print("Entre com o numero: ");
		setNumber(input.nextInt());
		
		resultadoRaiz = (int) Math.sqrt(getNumber());
		
		System.out.println("Resultado da Raiz: " + getResultadoRaiz());
		
		return resultadoRaiz;
	}
}
