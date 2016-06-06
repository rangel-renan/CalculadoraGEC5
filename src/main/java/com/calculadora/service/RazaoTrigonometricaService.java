package com.calculadora.service;

/**
 * Interface que define os serviços para a classe RazoesTrigonometricas.
 * 
 * Interface <code>RazoesTrigonometricasService</code>
 * 
 * @author TiagoHenrique
 * @author YasminRomi
 * @version 1.0 (23/05/2016)
 * @version 1.2 (25/05/2016)
 *
 */
public interface RazaoTrigonometricaService {
	
	/**
	 * Calcula o cosseno a partir de um valor em radiano.
	 * 
	 * @param valor
	 *  			valor
	 * @return cosseno
	 */
	public Double calcularCosseno(Double valor);
	
	/**
	 * Calcula a função inversa do cosseno a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return arco-cosseno
	 */
	public Double calcularArcoCosseno(Double valor);
	
	/**
	 * Calcula a secante a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return secante
	 */
	public Double calcularSecante(Double valor);
	
	/**
	 * Calcula o seno a partir de um valor em radiano.
	 * 
	 * @param valor
	 *  			valor
	 * @return seno
	 */
	public Double calcularSeno(Double valor);
	
	/**
	 * Calcula o arcosseno a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return arco-seno
	 */
	public Double calcularArcoSeno(Double valor);
	
	/**
	 * Calcula a cossecante a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return cossecante
	 */
	public Double calcularCossecante(Double valor);
	
	/**
	 * Calcula a tangente a partir a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return tangente
	 */
	public Double calcularTangente(Double valor);
	
	/**
	 * Calcula a função inversa da tangente a partir de um valor em radiano
	 * 
	 * @param valor
	 *  			valor
	 * @return arco-tangente
	 */
	public Double calcularArcoTangente(Double valor);
	
	/**
	 * Calcula a cotangente a partir da tangente
	 * 
	 * @param valor
	 *  			valor
	 * @return cotangente
	 */
	public Double calcularCotangente(Double valor);
}
