package com.calculadora.model;

public class Financiamento {
	private String numeroParcela;
	private String parcela;
	private String amortizacaoAoMes;
	private String jurosAoMes;
	private String saldoDevedorAoMes;
	
	public Financiamento() {
		// TODO Auto-generated constructor stub
	}
	
	public Financiamento(String numeroParcela, String parcela, String amortizacaoAoMes, String jurosAoMes, String saldoDevedorAoMes) {
		this.numeroParcela = numeroParcela;
		this.parcela = parcela;
		this.amortizacaoAoMes = amortizacaoAoMes;
		this.jurosAoMes = jurosAoMes;
		this.saldoDevedorAoMes = saldoDevedorAoMes;
	}
	
	public String getNumeroParcela() {
		return numeroParcela;
	}
	public void setNumeroParcela(String numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	public String getParcela() {
		return parcela;
	}
	public void setParcela(String parcela) {
		this.parcela = parcela;
	}
	public String getAmortizacaoAoMes() {
		return amortizacaoAoMes;
	}
	public void setAmortizacaoAoMes(String amortizacaoAoMes) {
		this.amortizacaoAoMes = amortizacaoAoMes;
	}
	public String getJurosAoMes() {
		return jurosAoMes;
	}
	public void setJurosAoMes(String jurosAoMes) {
		this.jurosAoMes = jurosAoMes;
	}
	public String getSaldoDevedorAoMes() {
		return saldoDevedorAoMes;
	}
	public void setSaldoDevedorAoMes(String saldoDevedorAoMes) {
		this.saldoDevedorAoMes = saldoDevedorAoMes;
	}
	
	
//	public Financiamento() {
//		parcelas = new ArrayList<Double>();
//		amortizacaoAoMes = new ArrayList<Double>();
//		jurosAoMes = new ArrayList<Double>();
//		saldoDevedorAoMes = new ArrayList<Double>();
//	}
//	
//	public void addParcela(BigDecimal parcela) {
//		parcelas.add(parcela.doubleValue());
//	}
//	
//	public void addAmortizacao(BigDecimal amortizacao) {
//		amortizacaoAoMes.add(amortizacao.doubleValue());
//	}
//	
//	public void addJuro(BigDecimal juro) {
//		jurosAoMes.add(juro.doubleValue());
//	}
//	
//	public void addSaldoDevedor(BigDecimal saldoDevedor) {
//		if (saldoDevedor.equals(BigDecimal.ZERO)) 
//			saldoDevedorAoMes.add(0.0);
//		else
//			saldoDevedorAoMes.add(saldoDevedor.doubleValue());
//	}
//
//	public List<Double> getParcelas() {
//		return parcelas;
//	}
//
//	public void setParcelas(List<Double> parcelas) {
//		this.parcelas = parcelas;
//	}
//
//	public Double getParcela() {
//		return parcela;
//	}
//
//	public void setParcela(Double parcela) {
//		this.parcela = parcela;
//	}
//
//	public List<Double> getAmortizacaoAoMes() {
//		return amortizacaoAoMes;
//	}
//
//	public void setAmortizacaoAoMes(List<Double> amortizacaoAoMes) {
//		this.amortizacaoAoMes = amortizacaoAoMes;
//	}
//
//	public List<Double> getJurosAoMes() {
//		return jurosAoMes;
//	}
//
//	public void setJurosAoMes(List<Double> jurosAoMes) {
//		this.jurosAoMes = jurosAoMes;
//	}
//
//	public List<Double> getSaldoDevedorAoMes() {
//		return saldoDevedorAoMes;
//	}
//
//	public void setSaldoDevedorAoMes(List<Double> saldoDevedorAoMes) {
//		this.saldoDevedorAoMes = saldoDevedorAoMes;
//	}
	
	
	
}
