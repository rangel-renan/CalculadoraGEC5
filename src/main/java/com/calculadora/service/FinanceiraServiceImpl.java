package com.calculadora.service;

public class FinanceiraServiceImpl implements FinanceiraService {
	private final int pagamentosPorAno = 12;

	@Override
	public Double calcularEmprestimo(Double valorFinanciado, Double prazo, Double taxaMensal) {
		taxaMensal = taxaMensal / 100;
		double numer = taxaMensal * valorFinanciado / pagamentosPorAno;
		double e = -(pagamentosPorAno * prazo);
		double b = (taxaMensal / pagamentosPorAno) + 1.0;

		double denom = 1.0 - Math.pow(b, e);
		return (numer / denom);
	}

	public Double calcularValorFuturoInvestimento(Double valorAtualInvestimento, Double prazoEmAnos, Double taxaMensal,
			Double ajustesPorAno) {
		taxaMensal = taxaMensal / 100;
		double e = ajustesPorAno * prazoEmAnos;
		double b = (1 + taxaMensal / ajustesPorAno);

		return valorAtualInvestimento * Math.pow(b, e);
	}

	public Double calcularLimiteRetirada(Double valorAtualInvestimento, Double prazoEmAnos, Double taxaMensal,
			Integer numRetiradasPorAno) {
		double t1, t2;
		valorAtualInvestimento = valorAtualInvestimento / 100;
		t1 = valorAtualInvestimento / prazoEmAnos;

		double b = (1 + t1);
		double e = prazoEmAnos * taxaMensal;

		t2 = Math.pow(b, e) - 1;

		return numRetiradasPorAno * (t1 / t2 + t1);
	}
	
	public Double calcularInvestimentoInicial(Double valorInvestPretendido, Double prazoEmAnos, Double taxaMensal, Double peridoAjustesPorAno) {
		double b, e;
		taxaMensal = taxaMensal / 100;
        b = (1 + taxaMensal/peridoAjustesPorAno);
        e = peridoAjustesPorAno * prazoEmAnos;
               
        return valorInvestPretendido / Math.pow(b, e);
	}
	
	public Double calcularJuros(Double valorTotal, Double taxaJuros, Double peridoTotalEmMeses) {
		taxaJuros = taxaJuros / 100;
		return valorTotal * taxaJuros * peridoTotalEmMeses;
	}
	
	public Double calcularJurosComposto(Double valorTotal, Double taxaJuros, Double peridoTotalEmMeses) {
		taxaJuros = taxaJuros / 100;
		return valorTotal * Math.pow((1 + taxaJuros), peridoTotalEmMeses);
	}
	
	public Double calcularMontante(Double valorTotal, Double taxaJuros, Double peridoTotalEmMeses) {
		taxaJuros = taxaJuros / 100;
		return valorTotal * (1 + (taxaJuros * peridoTotalEmMeses));
	}
	
	public Double calcularValorPrestacao(Double valorTotal, Double taxaJuros, Double periodoFinanciamentoEmMeses) {
		taxaJuros = taxaJuros / 100;
		return valorTotal * (taxaJuros / (1 - Math.pow((1 + taxaJuros), -periodoFinanciamentoEmMeses)));
	}
	
	public static void main(String[] args) {
		System.out.println(new FinanceiraServiceImpl().calcularValorPrestacao(10000.0, 1.5, 12.0));
	}

}
