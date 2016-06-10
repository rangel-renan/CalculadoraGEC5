package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrimosController {
	private MainApp mainApp;
	private Stage primosStage;
	
	@FXML
	private Button btnCalcular;
	
	public void show(MainApp mainApp, Stage primosStage) {
		this.mainApp = mainApp;
		this.primosStage = primosStage;
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
	
	public void setPrimosStage(Stage primosStage) {
		this.primosStage = primosStage;
	}
}
