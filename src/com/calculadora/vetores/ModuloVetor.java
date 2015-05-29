// ModuloVetor.java
package com.calculadora.vetores;

//importa a classe Scanner para ler numeros 
import java.util.Scanner;

// calcula o módulo de um vetor
// formula: raiz((v[0] * v[0]) + (v[1] * v[1]) + (v[2] * v[2]))
public class ModuloVetor {
	final int MAX_INDEX = 3;
	
	private double[] vetor = new double[MAX_INDEX];
	 
	private double modulo; 

	Scanner input = new Scanner(System.in); 
	
	// Cria um construtor simples
	public ModuloVetor() {
		
	}
	
	// Cria um construtor que obtem um vetor
	public ModuloVetor (double[] vetor) {
		this.vetor = vetor;
	}
	
	// obtem o valor de vetor
	public void setVetor(double[] vetor) {
		  this.vetor = vetor;	 
	 } 
	 
	// retorna o valor de vetor
	 public double[] getVetor() {
		 return vetor;
	 }
	 
	 // obtem o valor de modulo
	 public void setModulo(double modulo) {
		 this.modulo = modulo;
	 }
	 
	 // retorna o valor de modulo
	 public double getModulo() {
		 return modulo;
	 }
	 
	 // le os valores de um vetor
	 public double[] leValores() {
		 double[] vetorAux = new double[MAX_INDEX];
		 
		 // para cada indice o vetor auxiliar obtem o valor que será imputado
		 for(int index = 0; index < MAX_INDEX; index++) {
				System.out.print("Insira o elemento: ");
				vetorAux[index] = input.nextDouble();
			}
		 // o vetor obtem o valor de vetor auxiliar
		 this.setVetor(vetorAux);
		 
		 // retorna o valor do vetor
		 return this.getVetor();
	 }
	 
	 // Faz e retorna o calculo do modulo de um vetor 
	 public double calculaModulo() {
		 // cria um numero auxiliar 
		 double moduloAux = 0;
		 
		 // cria vetor auxiliar que obtem o valor do vetor a ser calculado 
		 double[] vetorAux = getVetor();
		 
		 // calcula a formula do módulo de um vetor
		 moduloAux = Math.sqrt((vetorAux[0] * vetorAux[0]) + (vetorAux[1] * vetorAux[1]) + (vetorAux[2] * vetorAux[2]));
		 
		 // modulo obtem o valor de moduloAux
		 this.setModulo(moduloAux);
		 
		 return moduloAux;
	 }
	 
	 // printa na tela o resultado do modulo
	 public void mostraModulo() {
		 System.out.println("O módulo do vetor eh:" + this.getModulo());
	 }
	 
	 // insere os valores de um vetor, calcula e printa na tela o resultado
	 public void modulo() {
		 
		 this.setVetor(this.leValores());
		 this.calculaModulo();
		 this.mostraModulo();
	 }
	 
} // termino da classe ModuloVetor
