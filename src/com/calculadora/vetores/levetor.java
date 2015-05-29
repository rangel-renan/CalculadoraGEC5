package com.calculadora.vetores;

import java.util.Scanner;

public class levetor {
	public static final int TAM_VETOR = 3;
	
	public static double[] leitura(){
		double[] vetor = new double[TAM_VETOR];
		Scanner input = new Scanner(System.in);
		
		for(int contador = 0; contador < TAM_VETOR; contador++){
			if(contador == 0) { System.out.printf("Entre com o valor de x: "); }
			if(contador == 1) { System.out.printf("Entre com o valor de y: "); }
			if(contador == 2) { System.out.printf("Entre com o valor de z: "); }
			vetor[contador] = input.nextDouble();
		}
		return vetor;
	}
}
