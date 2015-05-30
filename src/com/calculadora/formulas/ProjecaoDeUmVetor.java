// ProjecaoDeUmVetor.java
package com.calculadora.formulas;

//importa a classe Scanner para ler numeros
import java.util.Scanner;

// importa classes para facilitar o calculo da formula
import com.calculadora.vetores.ModuloVetor;
import com.calculadora.vetores.ProdutoEscalar;
import com.calculadora.vetores.ProdutoNumeroVetor;

// Calcula a projecao de um vetor1 sobre o vetor2
// formula: ((vetor1*vetor2)/(|vetor2||vetor2|))*vetor2
public class ProjecaoDeUmVetor {
	
	final int INDICE_MAX = 3;
	
	private double[] vetor1 = new double[INDICE_MAX];
	private double[] vetor2 = new double[INDICE_MAX];
	private double[] projecao = new double[INDICE_MAX];
	
	
	// cria um construtor simples
	public ProjecaoDeUmVetor() {
		
	}
	
	// esse construtor inicializa dois vetores, vetor1 e vetor2
	public ProjecaoDeUmVetor(double[] vetor1, double[] vetor2) {
		this.vetor1 = vetor1;
		this.vetor2 = vetor2;
	}

	// retorna o valor de vetor1
	public double[] getVetor1() {
		return vetor1;
	}
	
	// obtem o valor de vetor1
	public void setVetor1(double[] vetor1) {
		this.vetor1 = vetor1;
	}

	// retorna o valor de vetor2
	public double[] getVetor2() {
		return vetor2;
	}
	
	// obtem o valor de vetor2
	public void setVetor2(double[] vetor2) {
		this.vetor2 = vetor2;
	}
	
	// retorna o valor do vetor projecao
	public double[] getProjecao() {
		return projecao;
	}
	
	// obtem o valor do vetor projecao
	public void setProjecao(double[] projecao) {
		this.projecao = projecao;
	}
	
	// le um vetor
	public double[] leVetores() {
		// cria um vetor auxiliar
		double[] vetor = new double[INDICE_MAX];
		Scanner input = new Scanner(System.in);
		
		// para cada indice do vetor ele le o valor que sera imputado
		for(int contador = 0; contador < INDICE_MAX; contador++){
			if(contador == 0) { System.out.printf("Entre com o valor de x: "); }
			if(contador == 1) { System.out.printf("Entre com o valor de y: "); }
			if(contador == 2) { System.out.printf("Entre com o valor de z: "); }
			vetor[contador] = input.nextDouble();
		}
		return vetor;
	}
	
	// faz o calculo da formula da projecao do vetor1 sobre o vetor2
	public double[] calcProjecao() {
		
		// cria vetores auxiliares que contem os valores de vetor1 e vetor2, respectivamente
		double[] vetor1Aux = this.getVetor1();
		double[] vetor2Aux = this.getVetor2();
		
		double[] vetorResult = new double[INDICE_MAX];
		
		// cria um objeto do tipo ProdutoEscalar já com os pparametros vetor1 e vetor2 e calcula o produto escalar entre eles
		ProdutoEscalar produtoEscalar = new ProdutoEscalar(vetor1Aux, vetor2Aux);
		produtoEscalar.fazProduto();
		
		// cria um objeto do tipo ModuloVetor já com o parametro vetor2 e calcula o modulo do vetor2
		ModuloVetor moduloVetor = new ModuloVetor(vetor2Aux);
		moduloVetor.calculaModulo();
		
		// cria um objeto do tipo ProdutoNumeroVetor com os parametros (vetor1*vetor2)/(|vetor2||vetor2|) e vetor2 e calcula o produto entre eles
		ProdutoNumeroVetor produtoNumeroVetor = new ProdutoNumeroVetor(produtoEscalar.getResult()/(moduloVetor.getModulo() * moduloVetor.getModulo()), vetor2Aux);
		produtoNumeroVetor.calcProduto();
		
		// o vetor auxiliar vetorResult é igual ao vallor do vetor resultante do produto entre o numero e vetor 
		vetorResult = produtoNumeroVetor.getVetorResultante();
		
		// o vetor projecao obtem o valor de vetorResult
		this.setProjecao(vetorResult);
		
		return getProjecao();
	}
	
	// printa o resultado da projecao na tela
	public void mostraProjecao() {
		
		// cria um vetor auxiliar vetorResult que contem o valor do vetor projecao
		double[] vetorResult = this.getProjecao();
		
		System.out.println("Resultado da operacao:"); 
		
		// para cada indice de vetorResult, o sistema printa seu valor na tela
		for(int indice = 0; indice < INDICE_MAX; indice++){
			System.out.printf("%.2f ", vetorResult[indice]);
		}		
		System.out.println("\n");
	}
	
	// insere dois vetores, calcula a projecao do vetor1 sobre o vetor2 e printa o resultado na tela
	public void projecao() {
		
		System.out.println("---vetor1---");
		this.setVetor1(this.leVetores());
		System.out.println("---vetor2---");
		this.setVetor2(this.leVetores());
		
		this.calcProjecao();
		this.mostraProjecao();
	}

}
