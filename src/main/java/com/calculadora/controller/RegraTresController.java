package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegraTresController {
	private MainApp mainApp;
	private Stage regraTresStage;

	@FXML
	private Button btnCalcular;
	
	public void show(MainApp mainApp, Stage regraTresStage) {
		this.mainApp = mainApp;
		this.regraTresStage = regraTresStage;
	}

	@FXML
	private void handleCalcular() {
		
	}
	
	@FXML
	private void handleVoltar() {
		
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setRegraTresStage(Stage regraTresStage) {
		this.regraTresStage = regraTresStage;
	}
}
