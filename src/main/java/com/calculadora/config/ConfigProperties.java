package com.calculadora.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ResourceBundle;

import com.calculadora.util.enums.Idioma;

public class ConfigProperties {
	private volatile static ConfigProperties instance;
	
	private ResourceBundle bundle;
	
	private ConfigProperties(Idioma idiomaEscolhido) throws MalformedURLException {
		setConfigJanelas(idiomaEscolhido);
	}
	
	public static ConfigProperties getInstance(Idioma idiomaEscolhido) {

		if (instance == null) {
			synchronized (ConfigProperties.class) {
				if (instance == null) {
					try {
						instance = new ConfigProperties(idiomaEscolhido);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return instance;
	}
	
	public void setConfigJanelas(Idioma idiomaEscolhido) throws MalformedURLException {
		File file = new File("config");
		URL[] urls = {file.toURI().toURL()};
		ClassLoader loader = new URLClassLoader(urls);
		bundle = ResourceBundle.getBundle("config", idiomaEscolhido.getLocale(), loader);
	}
	
	public String getString(String key) {
		return bundle.getString(key);
	}
	
	public Integer getInt(String key) {
		return new Integer(bundle.getString(key));
	}
	
	public Double getDouble(String key) {
		return new Double(bundle.getString(key));
	}
	
	public ResourceBundle getBundle() {
		return bundle;
	}
	
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
}
