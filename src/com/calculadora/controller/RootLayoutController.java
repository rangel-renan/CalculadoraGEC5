package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.model.Idioma;

import javafx.stage.Stage;

public class RootLayoutController {
	
	private MainApp mainApp;
	private Stage rootLayout;
	private Stage calculadoraStage;
	
	private ConfigProperties label;
	private Idioma idioma;
	
	public RootLayoutController() {
		label = ConfigProperties.getInstance(Idioma.Portugues);
	}
	
	public void initRootLayout() {
		
	}
	
	public void setRootLayout(Stage rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	public void setCalculadoraStage(Stage calculadoraStage) {
		this.calculadoraStage = calculadoraStage;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
