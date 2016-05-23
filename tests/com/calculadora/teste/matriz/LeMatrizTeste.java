package com.calculadora.teste.matriz;

import com.calculadora.matriz.LeMatriz;

public class LeMatrizTeste {

	public static void main(String args[]){
		
		double[][] matriz = LeMatriz.leMatriz(LeMatriz.leLinhas(), LeMatriz.leColunas());
		for(double[] linhas : matriz){
			for(double colunas : linhas){
				System.out.print(colunas + " \t");
			}
			System.out.println(" ");
		}
	}
}
