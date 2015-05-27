package com.calculadora.teste.vetores;

import com.calculadora.vetores.ModuloVetor;

import java.util.Scanner;

public class ModuloVetorTeste {

	public static void main(String[] args) {
		final int MAX_INDEX = 3;
		
		ModuloVetor moduloVetor = new ModuloVetor();
		
		double[] vetor = new double[MAX_INDEX];
		
		Scanner input = new Scanner(System.in); 
		
		for(int index = 0; index < MAX_INDEX; index++) {
			System.out.print("Insira o elemento: ");
			vetor[index] = input.nextInt();
		} moduloVetor.setVetor(vetor);
		
		System.out.println("O módulo do vetor eh:" + moduloVetor.calculaModulo());

	}
	
}
