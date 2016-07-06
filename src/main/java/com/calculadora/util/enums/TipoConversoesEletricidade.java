package com.calculadora.util.enums;

import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public enum TipoConversoesEletricidade {
	AMPERE("Ampere", SI.AMPERE),
	COULOMB("Coulomb", SI.COULOMB),
	FARAD("Farad", SI.FARAD),
	HENRY("Henry", SI.HENRY),
	OHM("OHM", SI.OHM),
	SIEMENS("Siemens", SI.SIEMENS),
	VOLT("SIEMENS", SI.VOLT),
	TERA_AMPERE("Teraampere", SI.TERA(SI.AMPERE)),
	TERA_COULOMB("Teracoulomb", SI.TERA(SI.COULOMB)),
	TERA_FARAD("Terafarad", SI.TERA(SI.FARAD)),
	TERA_HENRY("Terahenry", SI.TERA(SI.HENRY)),
	TERA_OHM("Teraohm", SI.TERA(SI.OHM)),
	TERA_SIEMENS("Terasiemens", SI.TERA(SI.SIEMENS)),
	TERA_VOLT("Teravolt", SI.TERA(SI.VOLT)),
	GIGA_AMPERE("Gigaampere", SI.GIGA(SI.AMPERE)),
	GIGA_COULOMB("Gigacoulomb", SI.GIGA(SI.COULOMB)),
	GIGA_FARAD("Gigafarad", SI.GIGA(SI.FARAD)),
	GIGA_HENRY("Gigahenry", SI.GIGA(SI.HENRY)),
	GIGA_OHM("Gigaohm", SI.GIGA(SI.OHM)),
	GIGA_SIEMENS("Gigasiemens", SI.GIGA(SI.SIEMENS)),
	GIGA_VOLT("Gigavolt", SI.GIGA(SI.VOLT)),
	MEGA_AMPERE("Megaampere", SI.MEGA(SI.AMPERE)),
	MEGA_COULOMB("Megacoulomb", SI.MEGA(SI.COULOMB)),
	MEGA_FARAD("Megafarad", SI.MEGA(SI.FARAD)),
	MEGA_HENRY("Megahenry", SI.MEGA(SI.HENRY)),
	MEGA_OHM("Megaohm", SI.MEGA(SI.OHM)),
	MEGA_SIEMENS("Megasiemens", SI.MEGA(SI.SIEMENS)),
	MEGA_VOLT("Megavolt", SI.MEGA(SI.VOLT)),
	MICRO_AMPERE("Microampere", SI.MICRO(SI.AMPERE)),
	MICRO_COULOMB("Microcoulomb", SI.MICRO(SI.COULOMB)),
	MICRO_FARAD("Microfarad", SI.MICRO(SI.FARAD)),
	MICRO_HENRY("Microhenry", SI.MICRO(SI.HENRY)),
	MICRO_OHM("Microohm", SI.MICRO(SI.OHM)),
	MICRO_SIEMENS("Microsiemens", SI.MICRO(SI.SIEMENS)),
	MICRO_VOLT("Microvolt", SI.MICRO(SI.VOLT)),
	MILLI_AMPERE("miliampere", SI.MILLI(SI.AMPERE)),
	MILLI_COULOMB("milicoulomb", SI.MILLI(SI.COULOMB)),
	MILLI_FARAD("milifarad", SI.MILLI(SI.FARAD)),
	MILLI_HENRY("milihenry", SI.MILLI(SI.HENRY)),
	MILLI_OHM("miliohm", SI.MILLI(SI.OHM)),
	MILLI_SIEMENS("milisiemens", SI.MILLI(SI.SIEMENS)),
	MILLI_VOLT("milivolt", SI.MILLI(SI.VOLT)),
	NANO_AMPERE("Nanoampere", SI.NANO(SI.AMPERE)),
	NANO_COULOMB("Nanocoulomb", SI.NANO(SI.COULOMB)),
	NANO_FARAD("Nanofarad", SI.NANO(SI.FARAD)),
	NANO_HENRY("Nanohenry", SI.NANO(SI.HENRY)),
	NANO_OHM("Nanoohm", SI.NANO(SI.OHM)),
	NANO_SIEMENS("Nanosiemens", SI.NANO(SI.SIEMENS)),
	NANO_VOLT("Nanovolt", SI.NANO(SI.VOLT)),
	PETA_AMPERE("Petaampere", SI.PETA(SI.AMPERE)),
	PETA_COULOMB("Petacoulomb", SI.PETA(SI.COULOMB)),
	PETA_FARAD("Petafarad", SI.PETA(SI.FARAD)),
	PETA_HENRY("Petahenry", SI.PETA(SI.HENRY)),
	PETA_OHM("Petaohm", SI.PETA(SI.OHM)),
	PETA_SIEMENS("Petasiemens", SI.PETA(SI.SIEMENS)),
	PETA_VOLT("Petavolt", SI.PETA(SI.VOLT)),
	PICO_AMPERE("Picoampere", SI.PICO(SI.AMPERE)),
	PICO_COULOMB("Picocoulomb", SI.PICO(SI.COULOMB)),
	PICO_FARAD("Picofarad", SI.PICO(SI.FARAD)),
	PICO_HENRY("Picohenry", SI.PICO(SI.HENRY)),
	PICO_OHM("Picoohm", SI.PICO(SI.OHM)),
	PICO_SIEMENS("Picosiemens", SI.PICO(SI.SIEMENS)),
	PICO_VOLT("Picovolt", SI.PICO(SI.VOLT));
	
	private String nome;
	private Unit<?> tipo;
	
	private TipoConversoesEletricidade(String nome, Unit<?> tipo) {
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
