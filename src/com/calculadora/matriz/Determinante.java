package com.calculadora.matriz;

public class Determinante {
	private double matriz[][];
	private int numeroLinhas;
	private int numeroColunas;
	
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

	public double[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(double[][] matriz) {
		this.matriz = matriz;
	}

	public void leMatriz(){
		setNumeroLinhas(LeMatriz.leLinhas());
		setNumeroColunas(LeMatriz.leColunas());
		if(getNumeroLinhas() == getNumeroColunas()){
			setMatriz(LeMatriz.leMatriz(getNumeroLinhas(), getNumeroColunas()));
		}		
	}
	
	public void imprimeDeterminante(){
		double determinante;
		
		determinante = determinanteMatrizNxN();
		if(determinante != -5){
			System.out.println("Determinante é: "+determinante);
		}		
	}
	
	//Se for preciso tirar o determinante, mas sem ser que seja impresso, chame esta função
	public double determinanteMatrizNxN(){
		double[][] matriz;
		boolean flag = false;		
		
		leMatriz();
		
		matriz = getMatriz();
		
		if(getNumeroLinhas() != getNumeroColunas()){
			System.out.println("Não é possível tirar o determinante de uma matriz que não é quadrada.");
		}else{
			if(getNumeroLinhas() == 2){
				double determinante = determinanteMatriz2(getNumeroLinhas(), getNumeroColunas(), matriz);
				return determinante;
			}else{
				double[] escalares = new double[matriz.length];				
				int contLinha = 0;
				int contColuna = 0;
				int contColunaAux = 0;
				double determinante = 0;
				
				while(flag != true){
					double primeiraPos = matriz[0][contColuna];
					escalares[contColuna] = reduzMatriz(matriz, matriz.length, matriz[0].length, contLinha, contColuna, primeiraPos);
					if(matriz.length > 3){
						escalares[contColuna] *= primeiraPos;
					}
					
					contColuna++;
					if(contColuna == matriz.length){
						flag = true;
					}
				}
				
				for(int contador = 0; contador < escalares.length; contador++){
					
					double escalar = escalares[contador];
					if(contador % 2 == 0){
						determinante+= escalar;
					}
					else{
						determinante-= escalar;
					}
				}
				return determinante;
			}
		}
		return -5;
		/*se não tiver como tirar a determinante por enquanto vai retornar -5, até eu descobrir como retornar 
		outra coisa*/
	}
	
	private double determinanteMatriz2(int numeroLinhas, int numeroColunas, double[][] matrizLocal){
		double determinante = 0;
		double multiplicacao = 1;
		double somaDiagonalPrincipal = 0;
		double somaDiagonalInversa = 0;
		
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
		return determinante;
	}
	
	private double reduzMatriz(double[][] matriz, int numeroLinhas, int numeroColunas, int contLinha, int contColuna, double primeiraPosAnterior){
		double[][] matrizLocal = new double[(numeroLinhas-1)][(numeroColunas-1)];
		double[] escalares = new double[numeroColunas];
		int auxLinha = 0;
		double escalar = 0;
		double somaEscalar = 0;
		
		
		if(contLinha < (matriz.length-1)){
			for(int contL = 0; contL < numeroLinhas; contL++){
				if(contL == contLinha){
					auxLinha = contL;
					continue;
				}
				for(int contC = 0; contC < numeroColunas; contC++){
					if(contC == contColuna){
						escalar = matriz[auxLinha][contC];
						continue;
					}else{
						if(contC < contColuna){
							matrizLocal[contL-1][contC] = matriz[contL][contC];
						}
						else{
							matrizLocal[contL-1][contC-1] = matriz[contL][contC];
						}	
					}
				
				}
			}
		}
		
		escalares[contColuna] = primeiraPosAnterior;
		
		contColuna = 0;
		double primeiraPos = 0;
		
		while(contColuna != matrizLocal.length){
			
			if(matrizLocal.length == 2){
				break;
			}else{
				primeiraPos = matrizLocal[0][contColuna];
				
				escalares[contColuna] = reduzMatriz(matrizLocal, matrizLocal.length, matrizLocal[0].length, contLinha, contColuna, primeiraPos);
				contColuna++;
			}
		}
		
		int contador = 0;
		for(double escalarSoma : escalares){
			
			if(contador % 2 == 0){
				somaEscalar+= escalarSoma;
			}
			else{
				somaEscalar-= escalarSoma;				
			}
			contador++;
			if(contador == (escalares.length-1)){
				break;
			}
		}
		
		double determinante = determinanteMatriz2(matrizLocal.length, matrizLocal.length, matrizLocal);
		escalar = determinante * primeiraPosAnterior;
		if(matrizLocal.length == 2){
			return escalar;
		}
		return somaEscalar;
	}
}