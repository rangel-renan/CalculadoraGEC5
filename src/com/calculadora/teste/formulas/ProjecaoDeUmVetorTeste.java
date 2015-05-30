// ProjecaoDeUmVetorTeste.java
package com.calculadora.teste.formulas;

import com.calculadora.formulas.ProjecaoDeUmVetor;

// Testa a classe ProjecaoDeUmVetor
public class ProjecaoDeUmVetorTeste {
	
	public static void main(String args[]) {
		final int INDICE_MAX = 3;
		
		double[] vetor1 = {2, 3, 4};
		double[] vetor2 = {4, 3, 0};
		
		// O termo final é utilizado pois é uma constante
		// Representa o resultado da prjeçao do vetor {2, 3, 4} sobre o vetor {4, 3, 0}
		final double[] VETOR_RESULTANTE = {2.72, 2.04, 0};
		
		// Cria um objeto do tipo ProjecaoDeUmVetor
		// Adiciona os valores de vetor1 e vetor2 como parâmetro do objeto 
		ProjecaoDeUmVetor projecaoDeUmVetor = new ProjecaoDeUmVetor(vetor1, vetor2);
		projecaoDeUmVetor.calcProjecao();
		
		int contAcerto = 0;
		
		// Cria vetor que é igual ao valor do vetor projecao
		double[] vetor = projecaoDeUmVetor.getProjecao();
		
		// Para cada indice do vetor, verifica se é igual ao respectivo indice de VETOR_RESULTANTE, se sim, incrementa um contador de acerto
		for(int indice = 0; indice < INDICE_MAX; indice++) {
			if (vetor[indice] == VETOR_RESULTANTE[indice])
				contAcerto++;
		}
		
		// Se o contador de acertos for igual ao numero de indices de vetor, printa que o teste ocorreu com sucesso
		if (contAcerto == 3)
			System.out.println("Teste realizado com sucesso!");
		else
			System.out.println("Sinto muito, nao deu certo!");
	}

}
