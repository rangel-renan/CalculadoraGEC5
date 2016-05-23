// ModuloVetor.java
package com.calculadora.vetores;

import java.util.ArrayList;
import java.util.List;

// calcula o módulo de um vetor
// formula: raiz((v[0] * v[0]) + (v[1] * v[1]) + (v[2] * v[2]))
public class ModuloVetor {
	public final int MAX_INDEX = 3;
	
	private List<Double> vetor;
	private Double modulo; 

	// Cria um construtor simples
	public ModuloVetor() {
		vetor = new ArrayList<Double>(MAX_INDEX);
	}
	
	public ModuloVetor(List<Double> vetor) {
		this.vetor = vetor;
	}

//	 // le os valores de um vetor
//	 public double[] leValores() {
//		 double[] vetorAux = new double[MAX_INDEX];
//		 
//		 // para cada indice o vetor auxiliar obtem o valor que será imputado
//		 for(int index = 0; index < MAX_INDEX; index++) {
//				System.out.print("Insira o elemento: ");
//				vetorAux[index] = input.nextDouble();
//			}
//		 // o vetor obtem o valor de vetor auxiliar
//		 this.setVetor(vetorAux);
//		 
//		 // retorna o valor do vetor
//		 return this.getVetor();
//	 }
	 
	 public List<Double> getVetor() {
		return vetor;
	}

	public void setVetor(List<Double> vetor) {
		this.vetor = vetor;
	}

	public Double getModulo() {
		return modulo;
	}

	public void setModulo(Double modulo) {
		this.modulo = modulo;
	}

	// Faz e retorna o calculo do modulo de um vetor 
	 public double calculaModulo() {
		 // cria um numero auxiliar 
		 double moduloAux = 0;
		 
		 // cria vetor auxiliar que obtem o valor do vetor a ser calculado 
		 List<Double> vetorAux = getVetor();
		 
		 // calcula a formula do módulo de um vetor
		 moduloAux = Math.sqrt((vetorAux.get(0) * vetorAux.get(0)) + (vetorAux.get(1) * vetorAux.get(1)) + (vetorAux.get(2) * vetorAux.get(2)));
		 
		 // modulo obtem o valor de moduloAux
		 this.setModulo(moduloAux);
		 
		 return moduloAux;
	 }
	 
	 // printa na tela o resultado do modulo
	 public void mostraModulo() {
		 System.out.println("O módulo do vetor eh:" + this.getModulo());
	 }
	 
//	 // insere os valores de um vetor, calcula e printa na tela o resultado
//	 public void modulo() {
//		 
//		 this.setVetor(this.leValores());
//		 this.calculaModulo();
//		 this.mostraModulo();
//	 }
//	 
} // termino da classe ModuloVetor
