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
	
	public RootLayoutController() {
		label = ConfigProperties.getInstance(Idioma.Portugues);
	}
	
	public void show(ConfigProperties label, MainApp mainApp, Stage rootStage) {
		this.label = label;
		this.rootStage = rootStage;
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleSobre() {
		mainApp.initSobre();
		rootStage.hide();
	}
	
	@FXML
	private void handleSair() {
		System.exit(0);
	}
	
	@FXML
	private void handleOpcoes() {
		rootStage.close();
		mainApp.initOpcoes();
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	@FXML
	private void handleConversoes() {
		rootStage.close();
		mainApp.initConversoes();
	}
	
	public void setRootStage(Stage rootStage) {
		this.rootStage = rootStage;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setLabel(ConfigProperties label) {
		this.label = label;
	}
}
