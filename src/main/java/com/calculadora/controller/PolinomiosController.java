package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.PolinomioService;
import com.calculadora.service.PolinomioServiceImpl;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PolinomiosController implements Runnable {
	private MainApp mainApp;
	private ConfigProperties label;
	private Stage polinomiosStage;
	private PolinomioService polinomioService;
	
	@Override
	public void run() {
		
		Platform.runLater(new Runnable() {
			public void run() {
				polinomiosStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage polinomiosStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.polinomiosStage = polinomiosStage;
		this.label = label;
		this.polinomioService = new PolinomioServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void handleCalcular() {
		
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		polinomiosStage.close();
		mainApp.clean(polinomiosStage, this);
	}
	
	@FXML
	private void handleAjuda() {
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public ConfigProperties getLabel() {
		return label;
	}

	public void setLabel(ConfigProperties label) {
		this.label = label;
	}

	public Stage getPolinomiosStage() {
		return polinomiosStage;
	}

	public void setPolinomiosStage(Stage polinomiosStage) {
		this.polinomiosStage = polinomiosStage;
	}

	public PolinomioService getPolinomioService() {
		return polinomioService;
	}

	public void setPolinomioService(PolinomioService polinomioService) {
		this.polinomioService = polinomioService;
	}

	public MainApp getMainApp() {
		return mainApp;
	}
	
	
}
