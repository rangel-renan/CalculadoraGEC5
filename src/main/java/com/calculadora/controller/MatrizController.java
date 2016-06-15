package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MatrizController {
	private MainApp mainApp;
	private Stage matrizStage;
	
	private ConfigProperties label;
	
	public void show(MainApp mainApp, Stage matrizStage, ConfigProperties label) {
		this.label = label;
		this.mainApp = mainApp;
		this.matrizStage = matrizStage;
	}
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
		matrizStage.close();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setLabel(ConfigProperties label) {
		this.label = label;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setMatrizStage(Stage matrizStage) {
		this.matrizStage = matrizStage;
	}
}
