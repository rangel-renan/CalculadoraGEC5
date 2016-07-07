package com.calculadora.util.enums;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesArea {
	HECTARE("Hectare", NonSI.HECTARE),
	QUILOM_QUADR("Quilômetro Quadrado", SI.KILO(SI.SQUARE_METRE)),
	METRO_QUADR("Metro Quadrado", SI.SQUARE_METRE);
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesArea(String nome, Unit<?> tipo) {
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
