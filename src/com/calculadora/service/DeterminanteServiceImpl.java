package com.calculadora.service;

import java.util.List;

import com.calculadora.matriz.Determinante;

public class DeterminanteServiceImpl {
	private Determinante determinante;
	
	public DeterminanteServiceImpl(Determinante _determinante) {
		determinante = _determinante;
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
		List<List<Double>> matriz = determinante.getMatriz();		
		boolean flag = false;		
		
		for(int contL = 0; contL < matriz.size(); contL++){
			for(int contC = 0; contC < matriz.get(contL).size(); contC++){
				System.out.print(matriz.get(contL).get(contC)+" ");
			}
			
			System.out.println("");
		}
		
		double[] escalares = new double[matriz.length];
				
		int contLinha = 0;
		int contColuna = 0;
		int contColunaAux = 0;
		double determinante = 0;
		
		while(flag != true){
			double primeiraPos = matriz[0][contColuna];

			escalares[contColuna] = reduzMatriz(matriz, matriz.length, matriz[0].length, contLinha, contColuna, primeiraPos);			
			
			for(double escalar : escalares){
				//System.out.println("valor retornado: "+escalar);
			}
			
			/*if(matriz.length > 3){
				escalares[contColuna] *= primeiraPos;
			}*/
			
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
	
	public double determinanteMatriz2(int numeroLinhas, int numeroColunas, double[][] matrizLocal){
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
	
	public double reduzMatriz(double[][] matriz, int numeroLinhas, int numeroColunas, int contLinha, int contColuna, double primeiraPosAnterior){
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
		
		/*for(int contL = 0; contL < matrizLocal.length; contL++){
			for(int contC = 0; contC < matrizLocal[contL].length; contC++){
				System.out.print(matrizLocal[contL][contC]+"\t");
			}
			System.out.println("");
		}*/
		
		escalares[contColuna] = primeiraPosAnterior;
		
		contColuna = 0;
		double primeiraPos = 0;
		
		while(contColuna != matrizLocal.length){
			
			if(matrizLocal.length == 2){
				break;
			}else{
				primeiraPos = matrizLocal[0][contColuna];
				//System.out.println("Recursividade: "+primeiraPos);
				escalares[contColuna] = reduzMatriz(matrizLocal, matrizLocal.length, matrizLocal[0].length, contLinha, contColuna, primeiraPos);
				contColuna++;
			}
		}
		
		/*for(double escalarTeste : escalares){
			System.out.println("valor retornado: "+escalarTeste);
		}*/
		
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
		//System.out.println("pos anterior: "+primeiraPosAnterior);
		somaEscalar *= primeiraPosAnterior;
		if(matrizLocal.length == 2){
			//System.out.println("estou retornando: "+escalar);
			return escalar;
		}
		//System.out.println("Estou retoranando a soma que é: "+somaEscalar);
		return somaEscalar;
	}
}
