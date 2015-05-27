package com.calculadora.vetores;

public class ModuloVetor {
	final int MAX_INDEX = 3;
	
	private double[] vetor = new double[MAX_INDEX];
	 
	private double modulo; 
	 
	 public void setVetor(double[] vetor) {
		this.vetor = vetor; 
	 } 
	 
	 public double[] getVetor() {
		 return vetor;
	 }
	 
	 public double calculaModulo() {
		 
		 ModuloVetor moduloVetor = new ModuloVetor();
		 
		 moduloVetor.getVetor();
		 
		 modulo = Math.sqrt((vetor[0]*vetor[0]) + (vetor[1]*vetor[1]) + (vetor[2]*vetor[2]));
		 
		 
		 return modulo;
	 }
	

}// end class VectorModule
