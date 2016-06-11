package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RegraTresController {
	private MainApp mainApp;
	private Stage regraTresStage;

	@FXML
	private Button btnCalcular;
	
	public void show(MainApp mainApp, Stage regraTresStage) {
		this.mainApp = mainApp;
		this.regraTresStage = regraTresStage;
		
		this.regraTresStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
	}

	@FXML
	private void handleCalcular() {
		
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
		regraTresStage.close();
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
