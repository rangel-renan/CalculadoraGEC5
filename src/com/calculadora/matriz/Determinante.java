package com.calculadora.matriz;

import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class Determinante {
	private int numeroLinhas;
	private int numeroColunas;
	
	private double[][] matriz;
	private double determinante;

	
	public double[][] getMatriz() {
		return matriz;
	}
	public void setMatriz(double[][] matriz) {
		this.matriz = matriz;
	}
	public double getDeterminante() {
		return determinante;
	}
	public void setDeterminante(double determinante) {
		this.determinante = determinante;
	}
	
	public int getNumeroLinhas() {
		return numeroLinhas;
	}
	public void setNumeroLinhas(int numeroLinhas) {
		this.numeroLinhas = numeroLinhas;
	}
	public int getNumeroColunas() {
		return numeroColunas;
	}
	public void setNumeroColunas(int numeroColunas) {
		this.numeroColunas = numeroColunas;
	}
	
	public int lenum(){
		Scanner input = new Scanner(System.in);
		
		return Integer.parseInt(input.nextLine());
	}
	public boolean leMatriz(){
		setNumeroLinhas(LeMatriz.leLinhas());
		setNumeroColunas(LeMatriz.leColunas());
		int numLinhas = getNumeroLinhas();
		int numColunas = getNumeroColunas();
		
		if(numLinhas != numColunas){
			System.out.println("Não possui determinante");
			return false;
		}else{
			setMatriz(LeMatriz.leMatriz(getNumeroLinhas(), getNumeroColunas()));
			return true;
		}
		
	}
	
	public double[] determinanteMatriz2(int numeroLinhas, int numeroColunas, double[][] matrizLocal){
		double[] vetorRetorno = new double[2];
		int verificacao = 0;
		double determinante = 0;
		double multiplicacao = 1;
		double somaDiagonalPrincipal = 0;
		double somaDiagonalInversa = 0;

		if(numeroLinhas != numeroColunas){
			System.out.println("Não é possivel tirar determinante.");
			vetorRetorno[0] = verificacao;
			vetorRetorno[1] = determinante;
			return vetorRetorno;
		}
		else{
			for(int numLinhas = 0; numLinhas < numeroLinhas; numLinhas++){
				for(int numColunas = 0; numColunas < numeroColunas; numColunas++){
					int auxDeterminante = (numLinhas + numColunas) % numeroLinhas;
					multiplicacao *= matrizLocal[numColunas][auxDeterminante];
				}
				somaDiagonalPrincipal += multiplicacao;
			}
			multiplicacao = 1;
			for(int numLinhas = (numeroLinhas-1); numLinhas >= 0; numLinhas--){
				for(int numColunas = (numeroColunas-1); numColunas >= 0; numColunas--){
					int auxDeterminante = (numLinhas + numColunas) % numeroLinhas;
					multiplicacao *= matrizLocal[numColunas][auxDeterminante];
				}
			somaDiagonalInversa+= multiplicacao;
			}
			determinante = somaDiagonalPrincipal - somaDiagonalInversa;
			verificacao = 1;
			vetorRetorno[0] = verificacao;
			vetorRetorno[1] = determinante;
			return vetorRetorno;
		}
	}

	public double determinante(){
		
		
		double determinante;		
		int numeroLinhas;
		int numeroColunas;
		double[] vetorDet;
		
		if(leMatriz()){
			numeroLinhas = getNumeroLinhas();
			numeroColunas = getNumeroColunas();
			double[][] matrizLocal = getMatriz();
			if(numeroLinhas == 1){
				determinante = matrizLocal[0][0];
				return determinante;
			}else{
				while(numeroLinhas > 2){
					//reduzMatriz(matrizLocal);
					numeroLinhas--;
					numeroColunas--;
				}
				vetorDet = determinanteMatriz2(numeroLinhas, numeroColunas, matrizLocal);
				if(vetorDet[0] == 1){
					determinante = vetorDet[1];
					System.out.println(determinante);
					return determinante;
				}		
			}
		}
		return -0.71;
	}	
}
