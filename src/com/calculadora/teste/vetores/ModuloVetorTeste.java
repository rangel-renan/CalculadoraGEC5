package com.calculadora.teste.vetores;

import com.calculadora.vetores.ModuloVetor;

import java.util.Scanner;

public class ModuloVetorTeste {

	public static void main(String[] args) {
		final int MAX_INDEX = 3;
		
		ModuloVetor moduloVetor = new ModuloVetor();
		
		double[] vetor = {3, 4, 0};
		
		moduloVetor.setVetor(vetor);
		
		if(moduloVetor.calculaModulo() == 5)
			System.out.println("Teste realizado com sucesso!");
	
		else 
			System.out.println("Sinto muito, nao deu certo :'(");	

	}
	
}
