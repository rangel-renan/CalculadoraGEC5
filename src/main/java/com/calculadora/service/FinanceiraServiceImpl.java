package com.calculadora.service;

public class FinanceiraServiceImpl implements FinanceiraService {
	
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
	
	/* Empréstimo - Juros*/
	public Double calcularJuros(Double valorTotal, Double taxaJuros, Double peridoTotalEmMeses) {
		taxaJuros = taxaJuros / 100;
		peridoTotalEmMeses = peridoTotalEmMeses / 12;
		return valorTotal * taxaJuros * peridoTotalEmMeses;
	}
	
	/* Empréstimo - Valor Total*/
	public Double calcularJurosComposto(Double valorTotal, Double taxaJuros, Double peridoTotalEmMeses) {
		taxaJuros = taxaJuros / 100;
		peridoTotalEmMeses = peridoTotalEmMeses / 12;
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
	
    //remainingBalance=cwBalance1;
    //var startBalance=cwBalance1
    //cwMonthlyAmount
	public Double calcularContaCartaoCredito(Double saldoCartaoCredito, Double taxaJuros, Double valorParcela) {
		taxaJuros= (taxaJuros / 100) / 12;
		Double balancoInicial = saldoCartaoCredito;
		Double pagamentoMinimo  = taxaJuros * saldoCartaoCredito;
		Double meses = 0.0;
		Double ultimoPagamento = 0.0;
		Double juro = 0.0;
		
		if (pagamentoMinimo > valorParcela) {
			System.out.println("Your monthly payment is less than the monthly interest charged by this card.");
			return null;
		}
		
		while (saldoCartaoCredito > 0) {
			meses++;
			juro += saldoCartaoCredito * taxaJuros;
			saldoCartaoCredito = saldoCartaoCredito * (1 + taxaJuros) - valorParcela;
		}
		
		Double valorTotal = 0.0;
		valorTotal = balancoInicial;
		valorTotal = valorTotal / 100;
		valorTotal=valorTotal + juro;
		System.out.println(meses);
		System.out.println(balancoInicial + valorTotal);
		return valorTotal;
		
	}
	
	public static void main(String[] args) {
		System.out.println(new FinanceiraServiceImpl().calcularContaCartaoCredito(1000.0, 2.0, 3.0));
	}

}
