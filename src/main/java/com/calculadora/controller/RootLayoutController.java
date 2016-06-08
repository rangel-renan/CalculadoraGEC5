package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.util.Idioma;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RootLayoutController {
	
	private MainApp mainApp;
	private Stage rootStage;
	
	private ConfigProperties label;
	private Idioma idioma;
	
	public RootLayoutController() {
		label = ConfigProperties.getInstance(Idioma.Portugues);
	}
	
	@FXML
	public void handleSobre() {
		mainApp.initSobre();
		rootStage.hide();
	}
	
	public void setRootStage(Stage rootStage) {
		this.rootStage = rootStage;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
