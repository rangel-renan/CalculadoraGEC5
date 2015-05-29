// ProdutoNumeroVetor.java
package com.calculadora.vetores;

// importa a classe Scanner para ler numeros
import java.util.Scanner;

// Calcula o produto entre um numero e um vetor
// Exemplo: 2(3, 4, 5) = (6, 8, 10)
public class ProdutoNumeroVetor {
	
	private final int INDICE_MAX = 3;
	
	private double numeroProduto;
	private double[] vetorResultante = new double[INDICE_MAX];
	private double[] vetorProduto = new double[INDICE_MAX];
	
	Scanner input = new Scanner(System.in);
	
	
	// criação de um construtor simples
	public ProdutoNumeroVetor() {
		
	}
	
	// este construtor já inicializa os valores de numeroProduto e VetorProduto 
	public ProdutoNumeroVetor(double numeroProduto, double[] vetorProduto) {
		this.numeroProduto = numeroProduto;
		this.vetorProduto = vetorProduto;
	}
	
	
	
	// retorna o valor do numero do produto
	public double getNumeroProduto() {
		return numeroProduto;
	}
	
	// obtem o valor do numero do produto
	public void setNumeroProduto(double numeroProduto) {
		this.numeroProduto = numeroProduto;
	}
	
	// retorna o valor do vetor do produto
	public double[] getVetorProduto() {
		return vetorProduto;
	}
	
	// obtem o valor do vetor do produto
	public void setVetorProduto(double[] vetorProduto) {
		this.vetorProduto = vetorProduto;
	}
	
	// retorna o valor do vetor resultante
	public double[] getVetorResultante() {
		return vetorResultante;
	}
	
	// obtem o valor do vetor resultante
	public void setVetorResultante(double[] vetorResultante) {
		this.vetorResultante = vetorResultante;
	}
	
	
	
	// le e retorna o valor de um numero
	public void leNumeroProduto() {
		System.out.println("Entre com o numero do produto : ");
		this.setNumeroProduto(input.nextDouble());
	}
	
	// le e retorna os valores de um vetor
	public void leVetorProduto() {
		// cria um vetor auxiliar
		double[] vetor = new double[INDICE_MAX];
		
		// pede que o usuario insira o numero para cada indice do vetor
		for(int indice = 0; indice < INDICE_MAX; indice++) {
			System.out.println("Insira o elemento do vetor: ");
			vetor[indice] = input.nextDouble();
		}
		// vetorProduto se torna igual a vetor
		this.setVetorProduto(vetor);
	}
	
	
	// faz o calculo da operação ProdutoNumeroVetor
	public double[] calcProduto() {
		
		// cria vetor, que é igual ao valor de vetorProduto
		double[] vetor = getVetorProduto();
		
		// cria numero, que é igual ao valor de numeroProduto
		double numero = getNumeroProduto();
		
		double[] vetorResult = new double[INDICE_MAX];
		
		// para cada valor de cada indice do vetorProduto, o vetorResultante o multiplica pelo NumeroProduto
		for(int indice = 0; indice < INDICE_MAX; indice++) {
			vetorResult[indice] = vetor[indice] * numero;
		}
		
		// vetorResultante se torna igual a vetorResult
		this.setVetorResultante(vetorResult);
		
		// retorna o valor de vetorResultante
		return getVetorResultante();
	}
	
	
	// printa na tela o vetorResultante
	public void mostraResultado() {
		
		// cria vetorResult, que é igual ao valor de vetorResultante
		double[] vetorResult = getVetorResultante();
		
		System.out.println("Resultado da operacao:"); 
		
		// o sistema printa cada valor de cada indice de vetorResult
		for(int indice = 0; indice < INDICE_MAX; indice++){
			System.out.printf("%f ", vetorResult[indice]);
		}		
		System.out.println("\n");
	}
	
	
	// insere um numero e um vetor, calcula e mostra o resultado do produto
	public void produtoNumeroVetor() {
		
		this.leNumeroProduto();
		this.leVetorProduto();
		
		this.calcProduto();
		
		this.mostraResultado();
	}
	

} // término da classe ProdutoNumeroVetor
