package com.calculadora.vetores;

import java.util.Scanner;

public class ModuloVetor {
	final int MAX_INDEX = 3;
	
	private double[] vetor = new double[MAX_INDEX];
	 
	private double modulo; 

	Scanner input = new Scanner(System.in); 
	 
	public void setVetor(double[] vetor) {
		  this.vetor = vetor;	 
	 } 
	 
	 public double[] getVetor() {
		 return vetor;
	 }
	 
	 public double[] leValores() {
		 
		 for(int index = 0; index < MAX_INDEX; index++) {
				System.out.print("Insira o elemento: ");
				vetor[index] = input.nextInt();
			}
		 return vetor;
	 }
	 
	 public double calculaModulo() {
		 
		 ModuloVetor moduloVetor = new ModuloVetor();
		 
		 moduloVetor.getVetor();
		 
		 modulo = Math.sqrt((vetor[0]*vetor[0]) + (vetor[1]*vetor[1]) + (vetor[2]*vetor[2]));
		 
		 return modulo;
	 }

	 public void mostraModulo() {
		 System.out.println("O módulo do vetor eh:" + modulo);
	 }
	 
	 public void modulo() {
		 setVetor(this.leValores());
		 calculaModulo();
		 mostraModulo();
	 }
	 
}
