package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.util.Idioma;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

@SuppressWarnings("unused")
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
	
	@FXML
	private void handleFracoes() {
		rootStage.close();
		mainApp.initFracoes();
	}
	
	@FXML
	private void handlePorcentagens() {
		rootStage.close();
		mainApp.initPorcentagens();
	}
	
	@FXML
	private void handlePrimos() {
		rootStage.close();
		mainApp.initPrimos();
	}
	
	@FXML
	private void handleRegraTres() {
		rootStage.close();
		mainApp.initRegraTres();
	}
	
	@FXML
	private void handleMatriz(ActionEvent actionEvent) {
		rootStage.close();
		mainApp.initMatriz();
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
