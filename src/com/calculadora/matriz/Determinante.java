package com.calculadora.matriz;

import java.util.Scanner;

public class Determinante {

	private double[][] matriz;
	private double determinante;
	private int numeroLinhas;
	private int numeroColunas;
	
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

	public double[] lelinha(int numeroLinha){
		double[] linha = new double[(int) this.getNumeroColunas()];
		
		for(int contador = 0; contador < getNumeroColunas(); contador++){
			System.out.print("Entre com o numero da linha " + numeroLinha + " e Coluna " + (contador + 1) + ": ");
			linha[contador] = this.lenum();
		}
		return linha;
	}
	
	public void calculaDeterminante(){
		double[][] matriz;
		
		System.out.print("Entre com o numero de linhas da matriz: ");
		this.setNumeroLinhas(this.lenum());
		System.out.print("Entre com o numero de coluas da matriz: ");
		this.setNumeroColunas(this.lenum());
		double[] linha1 = this.lelinha(1);
		double[] linha2 = this.lelinha(2);
		double[] linha3 = new double[(int) getNumeroColunas()];
		
	}
}
