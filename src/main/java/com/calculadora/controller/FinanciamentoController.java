package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.FinanceiraService;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class FinanciamentoController {
	private MainApp mainApp;
	private Stage financiamentoStage;

	private ConfigProperties label;
	private FinanceiraService financeiraService;
	
	public void show(MainApp mainApp, Stage financiamentoStage, ConfigProperties label,
			FinanceiraService financeiraService) {
		this.mainApp = mainApp;
		this.financiamentoStage = financiamentoStage;
		this.label = label;
		this.financeiraService = financeiraService;
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.initRoot();
		financiamentoStage.close();
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
	
	public void setFinanciamentoStage(Stage financiamentoStage) {
		this.financiamentoStage = financiamentoStage;
	}
	
	public Stage getFinanciamentoStage() {
		return financiamentoStage;
	}
}
