package com.calculadora.raizquadra;

import java.util.Scanner;

public class RaizQuadrada {
	
	private int number;
	private int resultadoRaiz;
	private Scanner input = new Scanner(System.in);

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double raizQuadrada(){			
		
		System.out.print("Entre com o numero: ");
		number = input.nextInt();
		
		resultadoRaiz = (int) Math.sqrt(number);
		
		System.out.println("Resultado da Raiz: " + resultadoRaiz);
		
		return resultadoRaiz;
	}
}
