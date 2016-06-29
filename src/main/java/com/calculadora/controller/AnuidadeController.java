package com.calculadora.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.FinanceiraService;
import com.calculadora.service.FinanceiraServiceImpl;
import com.calculadora.util.ParseMes;
import com.calculadora.util.enums.TipoMoedas;
import com.calculadora.util.enums.TipoPeriodos;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AnuidadeController implements Runnable {
	private MainApp mainApp;
	private Stage anuidadeStage;
	
	private NumberFormat formatter;
	private ConfigProperties label;
	private FinanceiraService financeiraService;
	
	@FXML
	private Button btnCalcular;
	
	@FXML
	private ComboBox<TipoMoedas> comboMoedas;
	
	@FXML
	private ComboBox<TipoPeriodos> comboTipoPeridos;
	
	@FXML
	private TextField textFieldSimboloMoeda1;
	
	@FXML
	private TextField textFieldSimboloMoeda2;
	
	@FXML
	private TextField textFieldPagamentoMensal;
	
	@FXML
	private TextField textFieldTaxaAnual;
	
	@FXML
	private TextField textFieldPeriodoPagamento;
	
	@FXML
	private TextField textFieldValorAnuidade;
	
	@Override
	public void run() {
		btnCalcular.setDisable(true);
		
		setListerners(textFieldPagamentoMensal);
		setListerners(textFieldTaxaAnual);
		setListerners(textFieldPeriodoPagamento);
		
		Platform.runLater(new Runnable() {
			public void run() {
				anuidadeStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
		
		Platform.runLater(new Runnable() {
			public void run() { 
				comboMoedas.setItems(FXCollections.observableArrayList(TipoMoedas.values())); 
				comboMoedas.getSelectionModel().select(0);
			}
		});
		
		Platform.runLater(new Runnable() {
			public void run() { 
				comboTipoPeridos.setItems(FXCollections.observableArrayList(TipoPeriodos.values())); 
				comboTipoPeridos.getSelectionModel().select(0);
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage _anuidadeStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.anuidadeStage = _anuidadeStage;
		this.label = label;
		this.financeiraService = new FinanceiraServiceImpl();
		this.formatter = NumberFormat.getInstance();
		
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	private void setListerners(TextField textField) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldPagamentoMensal.getText().length() == 0 
				    || textFieldTaxaAnual.getText().length() == 0
				    || textFieldPeriodoPagamento.getText().length() == 0) {
				    	btnCalcular.setDisable(true);
				    } else {
				    	btnCalcular.setDisable(false);
				    }
				});
			}
		});
	}
	
	@FXML
	private void calcular() {
		textFieldValorAnuidade.setText(formatter.format(financeiraService.calcularAnuidade(new BigDecimal(textFieldPagamentoMensal.getText()), 
																		new BigDecimal(textFieldTaxaAnual.getText()), 
																		ParseMes.parseToMes(new BigDecimal(textFieldPeriodoPagamento.getText()), comboTipoPeridos.getValue())).doubleValue()));
	}
	
	@FXML
	private void hiddenMoeda() {
		textFieldSimboloMoeda1.setText(comboMoedas.getValue().getSimbolo());
		textFieldSimboloMoeda2.setText(comboMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		anuidadeStage.close();
		mainApp.clean(anuidadeStage, this);
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

	public Stage getAnuidadeStage() {
		return anuidadeStage;
	}

	public void setAnuidadeStage(Stage anuidadeStage) {
		this.anuidadeStage = anuidadeStage;
	}

	public FinanceiraService getFinanceiraService() {
		return financeiraService;
	}

	public void setFinanceiraService(FinanceiraService financeiraService) {
		this.financeiraService = financeiraService;
	}
	
}
