package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.stage.Stage;

public class PorcentagensController {
	private MainApp mainApp;
	private Stage porcentagensStage;
	
	public void show(MainApp mainApp, Stage porcentagensStage) {
		this.mainApp = mainApp;
		this.porcentagensStage = porcentagensStage;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setPorcentagensStage(Stage porcentagensStage) {
		this.porcentagensStage = porcentagensStage;
	}
	
}
