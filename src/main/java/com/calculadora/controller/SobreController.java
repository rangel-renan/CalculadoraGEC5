package com.calculadora.controller;

import com.calculadora.MainApp;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SobreController implements Runnable {
	
	private Stage sobreStage;
	private MainApp mainApp;
	
	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			public void run() {
				sobreStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleOk();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage _sobreStage) {
		this.mainApp = _mainApp;
		this.sobreStage = _sobreStage;
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void handleOk() {
		mainApp.exibirRoot();
		sobreStage.close();
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setSobreStage(Stage sobreStage) {
		this.sobreStage = sobreStage;
	}
}
