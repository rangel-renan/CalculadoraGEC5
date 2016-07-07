package com.calculadora.util.enums;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesArmaDados {
	BYTE("Byte", NonSI.BYTE), 
	BIT("Bit", SI.BIT),
	YOTTA("Yottabyte", SI.YOTTA(NonSI.BYTE)), 
	YOTTA_BIT("Yottabit", SI.YOTTA(SI.BIT)), 
	ZETTA("Zettabyte", SI.ZETTA(NonSI.BYTE)), 
	ZETTA_BIT("Zettabit", SI.ZETTA(SI.BIT)), 
	EXA("Exabyte", SI.EXA(NonSI.BYTE)), 
	EXA_BIT("Exabit", SI.EXA(SI.BIT)), 
	PETA("Petabyte", SI.PETA(NonSI.BYTE)), 
	PETA_BIT("Petabit", SI.PETA(SI.BIT)), 
	TERA("Terabytes", SI.TERA(NonSI.BYTE)), 
	TERA_BIT("Terabit", SI.TERA(SI.BIT)), 
	GIGA("Gigabytes", SI.GIGA(NonSI.BYTE)), 
	GIGA_BIT("Gigabit", SI.GIGA(SI.BIT)), 
	MEGA("Megabytes", SI.MEGA(NonSI.BYTE)), 
	MEGA_BIT("Megabit", SI.MEGA(SI.BIT)), 
	KILO("Kilobit", SI.KILO(NonSI.BYTE));
	
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
