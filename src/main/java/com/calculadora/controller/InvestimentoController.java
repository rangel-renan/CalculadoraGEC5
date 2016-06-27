package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.FinanceiraService;
import com.calculadora.service.FinanceiraServiceImpl;
import com.calculadora.util.enums.TipoMoedas;
import com.calculadora.util.enums.TipoPeriodos;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

@SuppressWarnings({"rawtypes", "unchecked"})
public class InvestimentoController implements Runnable {
	private MainApp mainApp;
	private Stage investimentoStage;

	private ConfigProperties label;
	private FinanceiraService financeiraService;

	@FXML
	private ComboBox<TipoMoedas> comboInvIniciMoedas;
	
	@FXML
	private ComboBox<TipoMoedas> comboValFutMoedas;
	
	@FXML
	private ComboBox<TipoMoedas> comboJurosMoedas;
	
	@FXML
	private TextField textFieldInvSimboloMoeda1;
	
	@FXML
	private ComboBox<TipoPeriodos> comboInvIniciPeriodos;
	
	@FXML
	private ComboBox<TipoPeriodos> comboInvIniciDuracao;
	
	@FXML
	private ComboBox<TipoPeriodos> comboValFutPeriodos;
	
	@FXML
	private ComboBox<TipoPeriodos> comboValFutDuracao;
	
	@FXML
	private ComboBox<TipoPeriodos> comboJurosPeriodos;
	
	@FXML
	private ComboBox<TipoPeriodos> comboJurosDuracao;
	
	@FXML
	private TextField textFieldInvSimboloMoeda2;
	
	@FXML
	private TextField textFieldValSimboloMoeda1;
	
	@FXML
	private TextField textFieldValSimboloMoeda2;
	
	@FXML
	private TextField textFieldJurosSimboloMoeda1;
	
	@FXML
	private TextField textFieldJurosSimboloMoeda2;
	
	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			public void run() {
				investimentoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});

		preenxerCombo(comboInvIniciMoedas, 0);
		preenxerCombo(comboValFutMoedas, 0);
		preenxerCombo(comboJurosMoedas, 0);
		preenxerComboPeriodos(comboInvIniciPeriodos);
		preenxerComboPeriodos(comboInvIniciDuracao);
		preenxerComboPeriodos(comboValFutPeriodos);
		preenxerComboPeriodos(comboValFutDuracao);
		preenxerComboPeriodos(comboJurosPeriodos);
		preenxerComboPeriodos(comboJurosDuracao);
	}
	
	private void preenxerCombo(ComboBox combo, int index) {
		Platform.runLater(new Runnable() {
			public void run() { 
				combo.setItems(FXCollections.observableArrayList(TipoMoedas.values())); 
				combo.getSelectionModel().select(index);
			}
		});
	}
	
	private void preenxerComboPeriodos(ComboBox combo) {
		Platform.runLater(new Runnable() {
			public void run() { 
				combo.setItems(FXCollections.observableArrayList(TipoPeriodos.values())); 
				combo.getSelectionModel().select(0);
			}
		});
	}
	
	@FXML
	private void hiddenInvMoeda() {
		textFieldInvSimboloMoeda1.setText(comboInvIniciMoedas.getValue().getSimbolo());
		textFieldInvSimboloMoeda2.setText(comboInvIniciMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void hiddenValMoeda() {
		textFieldValSimboloMoeda1.setText(comboValFutMoedas.getValue().getSimbolo());
		textFieldValSimboloMoeda2.setText(comboValFutMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void hiddenJurosMoeda() {
		textFieldJurosSimboloMoeda1.setText(comboJurosMoedas.getValue().getSimbolo());
		textFieldJurosSimboloMoeda2.setText(comboJurosMoedas.getValue().getSimbolo());
	}
	
	public void show(MainApp _mainApp, Stage _investimentoStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.investimentoStage = _investimentoStage;
		this.label = label;
		this.financeiraService = new FinanceiraServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		investimentoStage.close();
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
	
	public Stage getInvestimentoStage() {
		return investimentoStage;
	}
	
	public void setInvestimentoStage(Stage investimentoStage) {
		this.investimentoStage = investimentoStage;
	}
}
