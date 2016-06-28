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
public class JurosController implements Runnable {
	private MainApp mainApp;
	private Stage jurosStage;

	private ConfigProperties label;
	private FinanceiraService financeiraService;

	@FXML
	private ComboBox<TipoMoedas> comboJurSimMoedas;
	
	@FXML
	private ComboBox<TipoMoedas> comboJurCompMoedas;

	@FXML
	private ComboBox<TipoPeriodos> comboJurSimTipoPeriodos;

	@FXML
	private ComboBox<TipoPeriodos> comboJurCompTipoPeriodos;
	
	@FXML
	private TextField textFieldJurSimSimboloMoeda1;
	
	@FXML
	private TextField textFieldJurSimSimboloMoeda2;

	@FXML
	private TextField textFieldJurSimSimboloMoeda3;
	
	@FXML
	private TextField textFieldJurCompSimboloMoeda1;
	
	@FXML
	private TextField textFieldJurCompSimboloMoeda2;

	@FXML
	private TextField textFieldJurCompSimboloMoeda3;
	
	@Override
	public void run() {
		Platform.runLater(new Runnable() {
			public void run() {
				jurosStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});

		preenxerCombo(comboJurSimMoedas, 0);
		preenxerCombo(comboJurCompMoedas, 0);
		preenxerComboPeriodos(comboJurSimTipoPeriodos);
		preenxerComboPeriodos(comboJurCompTipoPeriodos);
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
	
	public void show(MainApp _mainApp, Stage jurosStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.jurosStage = jurosStage;
		this.label = label;
		this.financeiraService = new FinanceiraServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void hiddenJurSimMoeda() {
		textFieldJurSimSimboloMoeda1.setText(comboJurSimMoedas.getValue().getSimbolo());
		textFieldJurSimSimboloMoeda2.setText(comboJurSimMoedas.getValue().getSimbolo());
		textFieldJurSimSimboloMoeda3.setText(comboJurSimMoedas.getValue().getSimbolo());
	}

	@FXML
	private void hiddenJurCompMoeda() {
		textFieldJurCompSimboloMoeda1.setText(comboJurCompMoedas.getValue().getSimbolo());
		textFieldJurCompSimboloMoeda2.setText(comboJurCompMoedas.getValue().getSimbolo());
		textFieldJurCompSimboloMoeda3.setText(comboJurCompMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		jurosStage.close();
		mainApp.clean(jurosStage, this);
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
	
	public void setJurosStage(Stage jurosStage) {
		this.jurosStage = jurosStage;
	}
	
	public Stage getJurosStage() {
		return jurosStage;
	}
}
