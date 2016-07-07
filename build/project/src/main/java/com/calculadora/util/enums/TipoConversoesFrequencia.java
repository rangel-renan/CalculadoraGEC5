package com.calculadora.util.enums;

import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesFrequencia {
	HERTZ("Hertz", SI.HERTZ),
	QUILO_HERTZ("Quilo-hertz", SI.KILO(SI.HERTZ)),
	MEGA_HERTZ("Mega-hertz", SI.MEGA(SI.HERTZ)),
	GIGA_HERTZ("Giga-hertz", SI.GIGA(SI.HERTZ));
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesFrequencia(String nome, Unit<?> tipo) {
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
