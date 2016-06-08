package com.calculadora.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SobreController {
	
	private Stage sobreStage;
	private Stage rootStage;
	
	public void show(Stage rootStage, Stage sobreStage) {
		this.rootStage = rootStage;
		this.sobreStage = sobreStage;
	}
	
	@FXML
	private void handleOk() {
		rootStage.show();
		sobreStage.close();
	}
	
	public void setRootStage(Stage rootStage) {
		this.rootStage = rootStage;
	}
	
	public void setSobreStage(Stage sobreStage) {
		this.sobreStage = sobreStage;
	}
}
