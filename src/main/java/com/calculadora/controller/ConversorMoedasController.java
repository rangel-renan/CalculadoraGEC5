package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.ConversaoService;
import com.calculadora.service.ConversaoServiceImpl;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ConversorMoedasController implements Runnable {
	private MainApp mainApp;
	private Stage conversaoMoedasStage;

	private ConfigProperties label;
	private ConversaoService conversaoService;
	
	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			public void run() {
				conversaoMoedasStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage _conversaoMoedasStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.conversaoMoedasStage = _conversaoMoedasStage;
		this.label = label;
		this.conversaoService = new ConversaoServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		conversaoMoedasStage.close();
	}

	@FXML
	private void handleAjuda() {
		
	}

	public MainApp getMainApp() {
		return mainApp;
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

	public Stage getConversaoMoedasStage() {
		return conversaoMoedasStage;
	}

	public void setConversaoMoedasStage(Stage conversaoMoedasStage) {
		this.conversaoMoedasStage = conversaoMoedasStage;
	}

	public ConversaoService getConversaoService() {
		return conversaoService;
	}

	public void setConversaoService(ConversaoService conversaoService) {
		this.conversaoService = conversaoService;
	}
	
}
