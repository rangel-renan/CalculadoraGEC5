package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.FinanceiraService;
import com.calculadora.service.FinanceiraServiceImpl;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CartaoCreditoController implements Runnable {
	private MainApp mainApp;
	private Stage cartaoCreditoStage;

	private ConfigProperties label;
	private FinanceiraService financeiraService;
	
	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			public void run() {
				cartaoCreditoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage _cartaoCreditoStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.cartaoCreditoStage = _cartaoCreditoStage;
		this.label = label;
		this.financeiraService = new FinanceiraServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		cartaoCreditoStage.close();
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

	public FinanceiraService getFinanceiraService() {
		return financeiraService;
	}

	public void setFinanceiraService(FinanceiraService financeiraService) {
		this.financeiraService = financeiraService;
	}

	public Stage getCartaoCreditoStage() {
		return cartaoCreditoStage;
	}

	public void setCartaoCreditoStage(Stage cartaoCreditoStage) {
		this.cartaoCreditoStage = cartaoCreditoStage;
	}


}
