package com.calculadora.service;

import java.util.ArrayList;
import java.util.List;

import com.calculadora.model.ProjecaoDeUmVetor;
import com.calculadora.vetores.ModuloVetor;
import com.calculadora.vetores.ProdutoEscalar;
import com.calculadora.vetores.ProdutoNumeroVetor;

public class ProjecaoDeUmVetorServiceImpl implements ProjecaoDeUmVetorService {
	private ProjecaoDeUmVetor projecaoDeUmVetor;

	public ProjecaoDeUmVetorServiceImpl(ProjecaoDeUmVetor _projecaoDeUmVetor) {
		projecaoDeUmVetor = _projecaoDeUmVetor;
	}

	public <T extends Number> List<Double> calcularProjecao(List<T> vetor1, List<T> vetor2, List<T> vetorAux) {
		return calcProjecao();
	}

	// faz o calculo da formula da projecao do vetor1 sobre o vetor2
	public List<Double> calcProjecao() {

		List<Double> vetorResult = new ArrayList<Double>(ProjecaoDeUmVetor.INDICE_MAX);

		// cria um objeto do tipo ProdutoEscalar já com os pparametros vetor1 e
		// vetor2 e calcula o produto escalar entre eles
		ProdutoEscalar produtoEscalar = new ProdutoEscalar(projecaoDeUmVetor.getVetor1(),
				projecaoDeUmVetor.getVetor2());
		produtoEscalar.fazProduto();

		// cria um objeto do tipo ModuloVetor já com o parametro vetor2 e
		// calcula o modulo do vetor2
		ModuloVetor moduloVetor = new ModuloVetor(projecaoDeUmVetor.getVetor2());
		moduloVetor.calculaModulo();

		// cria um objeto do tipo ProdutoNumeroVetor com os parametros
		// (vetor1*vetor2)/(|vetor2||vetor2|) e vetor2 e calcula o produto entre
		// eles
		ProdutoNumeroVetor produtoNumeroVetor = new ProdutoNumeroVetor(
				produtoEscalar.getResult() / (moduloVetor.getModulo() * moduloVetor.getModulo()),
				projecaoDeUmVetor.getVetor2());
		produtoNumeroVetor.calcProduto();

		// o vetor auxiliar vetorResult é igual ao vallor do vetor resultante do
		// produto entre o numero e vetor
		vetorResult = produtoNumeroVetor.getVetorResultante();

		// o vetor projecao obtem o valor de vetorResult
		projecaoDeUmVetor.setProjecao(vetorResult);

		return vetorResult;
	}

	// // printa o resultado da projecao na tela
	// public void mostraProjecao() {
	//
	// // cria um vetor auxiliar vetorResult que contem o valor do vetor
	// projecao
	// double[] vetorResult = this.getProjecao();
	//
	// System.out.println("Resultado da operacao:");
	//
	// // para cada indice de vetorResult, o sistema printa seu valor na tela
	// for(int indice = 0; indice < INDICE_MAX; indice++){
	// System.out.printf("%.2f ", vetorResult[indice]);
	// }
	// System.out.println("\n");
	// }
	//
	// // insere dois vetores, calcula a projecao do vetor1 sobre o vetor2 e
	// printa o resultado na tela
	// public void projecao() {
	//
	// System.out.println("---vetor1---");
	// this.setVetor1(this.leVetores());
	// System.out.println("---vetor2---");
	// this.setVetor2(this.leVetores());
	//
	// this.calcProjecao();
	// this.mostraProjecao();
	// }
	//
	// // le um vetor
	// public double[] leVetores() {
	// // cria um vetor auxiliar
	// double[] vetor = new double[INDICE_MAX];
	// Scanner input = new Scanner(System.in);
	//
	// // para cada indice do vetor ele le o valor que sera imputado
	// for(int contador = 0; contador < INDICE_MAX; contador++){
	// if(contador == 0) { System.out.printf("Entre com o valor de x: "); }
	// if(contador == 1) { System.out.printf("Entre com o valor de y: "); }
	// if(contador == 2) { System.out.printf("Entre com o valor de z: "); }
	// vetor[contador] = input.nextDouble();
	// }
	// return vetor;
	// }

}
