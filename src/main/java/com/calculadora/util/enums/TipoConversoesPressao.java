package com.calculadora.util.enums;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesPressao {
	ATMOSFERA("Atmosfera", NonSI.ATMOSPHERE),
	BAR("Bar", NonSI.BAR),
	PASCAL("Pascal", SI.PASCAL),
	POLEGADA_MERCURIO("Polegadas de mercúrio", NonSI.INCH_OF_MERCURY),
	MILIMETROS_MERCURIO("Milimetros de mercúrio", NonSI.MILLIMETER_OF_MERCURY);
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesPressao(String nome, Unit<?> tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Unit<?> getTipo() {
		return tipo;
	}
	
	public Unit<?> getTipo(String nome) {
		
		for (TipoConversoesComprimento t : TipoConversoesComprimento.values()) {
			if (t.getNome().equals(nome))
				return t.getTipo();
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
