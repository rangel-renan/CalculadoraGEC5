package com.calculadora.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//parcela + " " + amortizacao + " " + valorFinanciado * taxaJuros + " " + saldoDevedor
public class Financiamento {
	private List<Double> parcelas;
	private Double parcela;
	private List<Double> amortizacaoAoMes;
	private List<Double> jurosAoMes;
	private List<Double> saldoDevedorAoMes;
	
	public Financiamento() {
		parcelas = new ArrayList<Double>();
		amortizacaoAoMes = new ArrayList<Double>();
		jurosAoMes = new ArrayList<Double>();
		saldoDevedorAoMes = new ArrayList<Double>();
	}
	
	public void addParcela(BigDecimal parcela) {
		parcelas.add(parcela.doubleValue());
	}
	
	public void addAmortizacao(BigDecimal amortizacao) {
		amortizacaoAoMes.add(amortizacao.doubleValue());
	}
	
	public void addJuro(BigDecimal juro) {
		jurosAoMes.add(juro.doubleValue());
	}
	
	public void addSaldoDevedor(BigDecimal saldoDevedor) {
		if (saldoDevedor.equals(BigDecimal.ZERO)) 
			saldoDevedorAoMes.add(0.0);
		else
			saldoDevedorAoMes.add(saldoDevedor.doubleValue());
	}

	public List<Double> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Double> parcelas) {
		this.parcelas = parcelas;
	}

	public Double getParcela() {
		return parcela;
	}

	public void setParcela(Double parcela) {
		this.parcela = parcela;
	}

	public List<Double> getAmortizacaoAoMes() {
		return amortizacaoAoMes;
	}

	public void setAmortizacaoAoMes(List<Double> amortizacaoAoMes) {
		this.amortizacaoAoMes = amortizacaoAoMes;
	}

	public List<Double> getJurosAoMes() {
		return jurosAoMes;
	}

	public void setJurosAoMes(List<Double> jurosAoMes) {
		this.jurosAoMes = jurosAoMes;
	}

	public List<Double> getSaldoDevedorAoMes() {
		return saldoDevedorAoMes;
	}

	public void setSaldoDevedorAoMes(List<Double> saldoDevedorAoMes) {
		this.saldoDevedorAoMes = saldoDevedorAoMes;
	}
	
	
	
}
