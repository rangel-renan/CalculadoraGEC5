package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConversoesController {
	private MainApp mainApp;
	private Stage conversoesStage;
	
	@FXML
	private Button btnCalcular;
	
	public void show(MainApp mainApp, Stage conversoesStage) {
		this.mainApp = mainApp;
		this.conversoesStage = conversoesStage;
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
	
	public void setConversoesStage(Stage conversoesStage) {
		this.conversoesStage = conversoesStage;
	}
}
