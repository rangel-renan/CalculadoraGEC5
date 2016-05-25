package com.calculadora.service;

/**
 * Classe de Implementação da Interface RazoesTrigonometricasService.
 * 
 * Classe <code>RazoesTrigonometricasServiceImpl</code>
 * 
 * @author TiagoHenrique
 * @author YasminRomi
 * @version 1.0(23/05/2016)
 * @version 1.2 (25/05/2016)
 */
public class RazaoTrigonometricaServiceImpl implements RazaoTrigonometricaService {

	/*
	 * (non-Javadoc)
	 * @see com.calculadora.service.RazaoTrigonometricaService#calcularCosseno(java.lang.Double)
	 */
	@Override
	public Double calcularCosseno(Double valor) {
		return Math.cos(valor);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.calculadora.service.RazaoTrigonometricaService#calcularArcoCosseno(java.lang.Double)
	 */
	@Override
	public Double calcularArcoCosseno(Double valor) {
		return Math.acos(valor);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.calculadora.service.RazaoTrigonometricaService#calcularSecante(java.lang.Double)
	 */
	@Override
	public Double calcularSecante(Double valor) {
		return (1 / Math.cos(valor));
	}

	/*
	 * (non-Javadoc)
	 * @see com.calculadora.service.RazaoTrigonometricaService#calcularSeno(java.lang.Double)
	 */
	@Override
	public Double calcularSeno(Double valor) {
		return Math.sin(valor);
	}

	/*
	 * (non-Javadoc)
	 * @see com.calculadora.service.RazaoTrigonometricaService#calcularArcoSeno(java.lang.Double)
	 */
	@Override
	public Double calcularArcoSeno(Double valor) {
		return Math.asin(valor);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.calculadora.service.RazaoTrigonometricaService#calcularCossecante(java.lang.Double)
	 */
	@Override
	public Double calcularCossecante(Double valor) {
		return (1 / Math.sin(valor));
	}

	/*
	 * (non-Javadoc)
	 * @see com.calculadora.service.RazaoTrigonometricaService#calcularTangente(java.lang.Double)
	 */
	@Override
	public Double calcularTangente(Double valor) {
		return Math.tan(valor);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.calculadora.service.RazaoTrigonometricaService#calcularArcoTangente(java.lang.Double)
	 */
	@Override
	public Double calcularArcoTangente(Double valor) {
		return Math.atan(valor);
	}

	/*
	 * (non-Javadoc)
	 * @see com.calculadora.service.RazaoTrigonometricaService#calcularCotangente(java.lang.Double)
	 */
	@Override
	public Double calcularCotangente(Double valor) {
		return (1 / Math.tan(valor));
	}

}
