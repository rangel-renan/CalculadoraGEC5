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
	
	public double[] determinanteMatriz3(int numeroLinhas, int numeroColunas, double[][] matrizLocal){
		double produto1 = 1;
		double produto2 = 1;
		double produto3 = 1;
		double produto4 = 1;
		double produto5 = 1;
		double produto6 = 1;
		double[] vetorRetorno = new double[2];
		double verificacao = 0;
		
		if(numeroLinhas != numeroColunas){
			System.out.println("Não é possivel tirar determinante.");
			vetorRetorno[0] = verificacao;
			vetorRetorno[1] = determinante;
			return vetorRetorno;
		} else {
			verificacao = 1;
			for(int contadorLinhas = 0; contadorLinhas < numeroLinhas; contadorLinhas++){
				for (int contadorColunas = 0; contadorColunas < numeroColunas; contadorColunas++) {
					if(contadorLinhas == contadorColunas) { 
						produto1 *= matrizLocal[contadorLinhas][contadorColunas];
						if(contadorLinhas == 1 && 1 == contadorColunas){
							produto4 *= matrizLocal[contadorLinhas][contadorColunas];
						}
						if(contadorLinhas == 0 && 0 == contadorColunas){
							produto5 *= matrizLocal[contadorLinhas][contadorColunas];
						}
						if(contadorLinhas == 2 && 2 == contadorColunas){
							produto6 *= matrizLocal[contadorLinhas][contadorColunas];
						}
					}
					if((contadorLinhas == (contadorColunas + 1)) || (contadorLinhas == (contadorColunas - 2))) {
						produto2 *= matrizLocal[contadorLinhas][contadorColunas];
						if(contadorLinhas == 0 && contadorColunas == 2){
							produto4 *= matrizLocal[contadorLinhas][contadorColunas];
						}
						if(contadorLinhas == 2 && contadorColunas == 1){
							produto5 *= matrizLocal[contadorLinhas][contadorColunas];
						}
						if(contadorLinhas == 1 && contadorColunas == 0){
							produto6 *= matrizLocal[contadorLinhas][contadorColunas];
						}
					}
					if((contadorLinhas == (contadorColunas - 1)) || (contadorLinhas == (contadorColunas + 2))) {
						produto3 *= matrizLocal[contadorLinhas][contadorColunas];
						if(contadorLinhas == 2 && contadorColunas == 0){
							produto4 *= matrizLocal[contadorLinhas][contadorColunas];
						}
						if(contadorLinhas == 1 && contadorColunas == 2){
							produto5 *= matrizLocal[contadorLinhas][contadorColunas];
						}
						if(contadorLinhas == 0 && contadorColunas == 1){
							produto6 *= matrizLocal[contadorLinhas][contadorColunas];
						}
					}
				}
			}
			vetorRetorno[0] = verificacao;
			vetorRetorno[1]= produto1 + produto2 + produto3 - (produto4 + produto5 + produto6);
			return vetorRetorno;
		}
	}

	public void determinante(){
		
		
		double determinante;		
		int numeroLinhas;
		int numeroColunas;
		double[] vetorDet = {0, 0};
		
		if(leMatriz()){
			numeroLinhas = getNumeroLinhas();
			numeroColunas = getNumeroColunas();
			double[][] matrizLocal = getMatriz();
			if(numeroLinhas == 1){
				determinante = matrizLocal[0][0];
			} else {
				if(numeroLinhas == 2){
					vetorDet = determinanteMatriz2(numeroLinhas, numeroColunas, matrizLocal);
				} else {
					if(numeroLinhas == 3){
						vetorDet = determinanteMatriz3(numeroLinhas, numeroColunas, matrizLocal);
					}
				}
			}
			if(vetorDet[0] == 1){
				determinante = vetorDet[1];
				System.out.println(determinante);
			}			
		}
	}	
}
