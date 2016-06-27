package com.calculadora.util.enums;

import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesArmaDados {
	YOTTA("Yottabyte", SI.YOTTA(SI.METER)), 
	ZETTA("Zettabyte", SI.ZETTA(SI.METER)), 
	EXA("Exabyte", SI.EXA(SI.METER)), 
	PETA("Petabyte", SI.PETA(SI.METER)), 
	TERA("Terabytes", SI.TERA(SI.METER)), 
	GIGA("Gigabytes", SI.GIGA(SI.METER)), 
	MEGA("Megabytes", SI.MEGA(SI.METER)), 
	KILO("Kilobytes", SI.KILO(SI.METER));
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesArmaDados(String nome, Unit<?> tipo) {
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
		
		for (TipoConversoesArmaDados t : TipoConversoesArmaDados.values()) {
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
