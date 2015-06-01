package com.calculadora.matriz;

import java.util.Scanner;

public class LeMatriz {
	
	static Scanner input = new Scanner(System.in);
	
	public static int leLinhas(){
		
		System.out.print("Entre com o numero de linhas: ");
		int numLinhas = input.nextInt();
		return numLinhas;
	}
	
	public static int leColunas(){
		
		System.out.print("Entre com o numero de colunas: ");
		int numColunas = input.nextInt();
		return numColunas;
	}
	
	public static double[][] leMatriz(int numLinhas, int numColunas){		
		
		double[][] matriz = new double[numLinhas][numColunas];
				
		for(int contadorLinhas = 0; contadorLinhas < numLinhas; contadorLinhas++){
			for(int contadorColunas = 0; contadorColunas < numColunas; contadorColunas++){
				System.out.print("Entre com um elemento da linha: "+ contadorLinhas+ ", coluna: "+contadorColunas+ ": ");
				matriz[contadorLinhas][contadorColunas] = input.nextDouble();
				//matriz[contadorLinhas][contadorColunas] =(int) (Math.random() * 11);
				//gera matriz randomica
			}
		}
		return matriz;
	}
}
