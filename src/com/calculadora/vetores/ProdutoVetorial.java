package com.calculadora.vetores;

public class ProdutoVetorial {
	
	double[] vetor1;
	double[] vetor2;
	double[] vetorResposta;
	double[][] matriz;
	double i = 1, j = 1, k = 1;
	
	public double[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(double[][] matriz) {
		this.matriz = matriz;
	}

	public double[] getVetor1() {
		return vetor1;
	}

	public void setVetor1(double[] vetor1) {
		this.vetor1 = vetor1;
	}

	public double[] getVetor2() {
		return vetor2;
	}

	public void setVetor2(double[] vetor2) {
		this.vetor2 = vetor2;
	}

	public double[] getVetorResposta() {
		return vetorResposta;
	}

	public void setVetorResposta(double[] vetorResposta) {
		this.vetorResposta = vetorResposta;
	}

	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	public double getJ() {
		return j;
	}

	public void setJ(double j) {
		this.j = j;
	}

	public double getK() {
		return k;
	}

	public void setK(double k) {
		this.k = k;
	}
	
	public double[][] fazMatriz(){
		double[][] matriz = {getVetor1(), getVetor2()};
		return matriz;
	}
	
	public void produto(){
		
		double produto1 = 1;
		double produto2 = 1;
		double produto3 = 1;
		double produto4 = 1;
		double produto5 = 1;
		double produto6 = 1;
		double[][] matrizLocal;
		
		System.out.println("-----Vetor 1------");
		setVetor1(levetor.leitura());
		System.out.println("-----Vetor 2------");
		setVetor2(levetor.leitura());
		matrizLocal = fazMatriz();
		for(int contadorLinhas = 0; contadorLinhas < 2; contadorLinhas++){
			for(int contadorColunas = 0; contadorColunas < 3; contadorColunas++){
				if(contadorLinhas == contadorColunas){
					produto3 *= matrizLocal[contadorLinhas][contadorColunas];
					if(contadorLinhas == 1 && contadorColunas == 1){
						produto4 *= matrizLocal[contadorLinhas][contadorColunas];
					}
					if(contadorLinhas == 0 && contadorColunas == 0){
						produto5 *= matrizLocal[contadorLinhas][contadorColunas];
					}
				}
				if(contadorLinhas == (contadorColunas - 1)){
					produto1 *= matrizLocal[contadorLinhas][contadorColunas];
					if(contadorLinhas == 1 && contadorColunas == 2){
						produto5 *= matrizLocal[contadorLinhas][contadorColunas];
					}
					if(contadorLinhas == 0 && contadorColunas == 1){
						produto6 *= matrizLocal[contadorLinhas][contadorColunas];
					}
				}
				if(contadorLinhas == (contadorColunas + 1) || contadorLinhas == (contadorColunas - 2)){
					produto2 *= matrizLocal[contadorLinhas][contadorColunas];
					if(contadorLinhas == 1 && contadorColunas == 0){
						produto6 *= matrizLocal[contadorLinhas][contadorColunas];
					}
					if(contadorLinhas == 0 && contadorColunas == 2){
						produto4 *= matrizLocal[contadorLinhas][contadorColunas];
					}
				}
			}
		}
		setI(produto1 - produto4);
		setJ(produto2 - produto5);
		setK(produto3 - produto6);
		double[] vetor = { getI(), getJ(), getK() };
		setVetorResposta(vetor);
		for(double num : vetorResposta){
			System.out.print(num + " ");
		}
		System.out.println(" \n");
	}
	
}
