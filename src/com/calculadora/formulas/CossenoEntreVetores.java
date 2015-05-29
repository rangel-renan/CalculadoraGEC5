// CossenoEntreVetores.java
// Faz a fórmula do cálculo do cosseno do ângulo de dois vetores
package com.calculadora.formulas;

// importa a classe Scanner para ler numeros 
import java.util.Scanner;

// importando a classe ModuloVetor para poder utilizar o método calculaModuloVetor
import com.calculadora.vetores.ModuloVetor;

// importanto a classe ProdutoEscalar para poder utilizar o método fazProduto
import com.calculadora.vetores.ProdutoEscalar;

// representa o cálculo do cosseno entre dois vetores
public class CossenoEntreVetores {
	
	// o termo final é utilizado porque se trata de uma constante
	final int MAX_INDEX = 3;
	
	private double resultado;
	
	private double[] vetor1 = new double[MAX_INDEX];
	private double[] vetor2 = new double[MAX_INDEX];
	
	Scanner input = new Scanner(System.in);
	
	
	// Criação de um construtor simples
	public CossenoEntreVetores() {
		
	}
	
	// O construtor inicializa os valores dos vetores vetor1 e vetor2 com os parâmetros vetor1 e vetor2
	public CossenoEntreVetores(double[] vetor1, double[] vetor2) {
	   this.vetor1 = vetor1;
	   this.vetor2 = vetor2;
	}
	
	// Obtêm o valor de vetor1
	public void setVetor1(double[] vetor1) {
		this.vetor1 = vetor1;
	}
	
	// retorna o valor de vetor1
	public double[] getVetor1() {
		return vetor1;
	}
	
	// Obtêm o valor de vetor2
	public void setVetor2(double[] vetor2) {
		this.vetor2 = vetor2;
	}
	
	// retorna o valor de vetor2
	public double[] getVetor2() {
		return vetor2;
	}
	
	// Obtem o valor de resultado
	public void setResultado(double resultado) {
		this.resultado = resultado;
	}
	
	// retorna o valor de resultado
	public double getResultado() {
		return resultado;
	}
	
	// lê e retorna as tres coordenadas de um vetor 
	public double[] leVetor() {
		double[] vetor = new double[MAX_INDEX];
		
		for(int contador = 0; contador < MAX_INDEX; contador++){
			// para cada posição do vetor, uma coordenada diferente
			if(contador == 0) { System.out.printf("Entre com o valor de x: "); }
			if(contador == 1) { System.out.printf("Entre com o valor de y: "); }
			if(contador == 2) { System.out.printf("Entre com o valor de z: "); }
			
			vetor[contador] = input.nextDouble();
		} 
		return vetor;
	}
	
	// calcula e retorna a fórmula do cosseno = (U.V)/(|U|.|V|)
	public double calculaCosseno() {
		
		// cria uma variavel auxiliar
		double numeroResult;
		
		// cria dois objetos de ModuloVetor, um para cada vetor
		ModuloVetor moduloVetor1 = new ModuloVetor();
		ModuloVetor moduloVetor2 = new ModuloVetor();
		
		// cria um objeto de ProdutoEscalar 
		ProdutoEscalar produtoEscalar = new ProdutoEscalar();
		
		// (U.V)
		produtoEscalar.setVetor1(this.getVetor1());
		produtoEscalar.setVetor2(this.getVetor2());
		produtoEscalar.fazProduto();
		
		moduloVetor1.setVetor(this.getVetor1());
		moduloVetor2.setVetor(this.getVetor2());
		
		// Atribui a resultado a fórmula do cosseno = (U.V)/(|U|.|V|)
		numeroResult = produtoEscalar.getResult()/(moduloVetor2.calculaModulo()*(moduloVetor1.calculaModulo()));
		this.setResultado(numeroResult);
		
		return this.getResultado();
	}
	
	// printa o resultado do cálculo na tela
	public void mostraCosseno() {
		System.out.printf("O cosseno entre os vetores eh: %.2f", resultado);
	}
	
	// insere dois vetores, calcula e mostra o cosseno do angulo entre eles
	public void cosseno() {
		
		System.out.println("---Primeiro Vetor--- ");
		this.setVetor1(this.leVetor());
		System.out.println("---Segundo Vetor:--- ");
		this.setVetor2(this.leVetor());
		
		this.calculaCosseno();
		
		this.mostraCosseno();
		
	}
	
} // término da classe CossenoEntreVetores
