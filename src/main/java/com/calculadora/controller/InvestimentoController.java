package com.calculadora.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.InvestimentoService;
import com.calculadora.service.InvestimentoServiceImpl;
import com.calculadora.util.ParseAno;
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

@SuppressWarnings({"rawtypes", "unchecked"})
public class InvestimentoController implements Runnable {
	private MainApp mainApp;
	private Stage investimentoStage;

	private NumberFormat formatter;
	private ConfigProperties label;
	private InvestimentoService investimentoService;

	@FXML
	private Button btnInvCalcular;
	
	@FXML
	private Button btnJurosCalcular;
	
	@FXML
	private Button btnValFutCalcular;
	
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
	
	@FXML
	private TextField textFieldInvValorFuturo;
	
	@FXML
	private TextField textFieldInvInvestimentoInicial;
	
	@FXML
	private TextField textFieldInvDuracao;
	
	@FXML
	private TextField textFieldInvNumeroPeriodos;
	
	@FXML
	private TextField textFieldInvTaxaJuros;
	
	@FXML
	private TextField textFieldJurosValorFuturo;
	
	@FXML
	private TextField textFieldJurosInvestimentoInicial;
	
	@FXML
	private TextField textFieldJurosDuracao;
	
	@FXML
	private TextField textFieldJurosNumeroPeriodos;
	
	@FXML
	private TextField textFieldJurosTaxaJuros;
	
	@FXML
	private TextField textFieldValValorFuturo;
	
	@FXML
	private TextField textFieldValInvestimentoInicial;
	
	@FXML
	private TextField textFieldValDuracao;
	
	@FXML
	private TextField textFieldValNumeroPeriodos;
	
	@FXML
	private TextField textFieldValTaxaJuros;

	@Override
	public void run() {
		btnInvCalcular.setDisable(true);
		btnJurosCalcular.setDisable(true);
		btnValFutCalcular.setDisable(true);
		
		setListernersInvInic(textFieldInvValorFuturo, textFieldInvValorFuturo, textFieldInvDuracao, textFieldInvNumeroPeriodos, textFieldInvTaxaJuros, btnInvCalcular);
		setListernersInvInic(textFieldInvDuracao, textFieldInvValorFuturo, textFieldInvDuracao, textFieldInvNumeroPeriodos, textFieldInvTaxaJuros, btnInvCalcular);
		setListernersInvInic(textFieldInvNumeroPeriodos, textFieldInvValorFuturo, textFieldInvDuracao, textFieldInvNumeroPeriodos, textFieldInvTaxaJuros, btnInvCalcular);
		setListernersInvInic(textFieldInvTaxaJuros, textFieldInvValorFuturo, textFieldInvDuracao, textFieldInvNumeroPeriodos, textFieldInvTaxaJuros, btnInvCalcular);
		
		setListernersJur(textFieldJurosValorFuturo, textFieldJurosValorFuturo, textFieldJurosInvestimentoInicial, textFieldJurosDuracao, textFieldJurosNumeroPeriodos, btnJurosCalcular);
		setListernersJur(textFieldJurosInvestimentoInicial, textFieldJurosValorFuturo, textFieldJurosInvestimentoInicial, textFieldJurosDuracao, textFieldJurosNumeroPeriodos, btnJurosCalcular);
		setListernersJur(textFieldJurosDuracao, textFieldJurosValorFuturo, textFieldJurosInvestimentoInicial, textFieldJurosDuracao, textFieldJurosNumeroPeriodos, btnJurosCalcular);
		setListernersJur(textFieldJurosNumeroPeriodos, textFieldJurosValorFuturo, textFieldJurosInvestimentoInicial, textFieldJurosDuracao, textFieldJurosNumeroPeriodos, btnJurosCalcular);
		
		setListernersValTot(textFieldValInvestimentoInicial, textFieldValInvestimentoInicial, textFieldValDuracao, textFieldValNumeroPeriodos, textFieldValTaxaJuros, btnValFutCalcular);
		setListernersValTot(textFieldValDuracao, textFieldValInvestimentoInicial, textFieldValDuracao, textFieldValNumeroPeriodos, textFieldValTaxaJuros, btnValFutCalcular);
		setListernersValTot(textFieldValNumeroPeriodos, textFieldValInvestimentoInicial, textFieldValDuracao, textFieldValNumeroPeriodos, textFieldValTaxaJuros, btnValFutCalcular);
		setListernersValTot(textFieldValTaxaJuros, textFieldValInvestimentoInicial, textFieldValDuracao, textFieldValNumeroPeriodos, textFieldValTaxaJuros, btnValFutCalcular);
		
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
				combo.getSelectionModel().select(2);
			}
		});
	}

	private void setListernersJur(TextField textField, TextField textFieldValorFuturo, TextField textFieldInvestimentoInicial, 
			TextField textFieldDuracao, TextField textFieldNumeroPeriodos, Button btnCalcular) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldValorFuturo.getText().length() == 0 
				    || textFieldInvestimentoInicial.getText().length() == 0
				    || textFieldDuracao.getText().length() == 0
				    || textFieldNumeroPeriodos.getText().length() == 0) {
				    	btnCalcular.setDisable(true);
				    } else {
				    	btnCalcular.setDisable(false);
				    }
				});
			}
		});
	}
	
	private void setListernersInvInic(TextField textField, TextField textFieldValorFuturo, 
			TextField textFieldDuracao, TextField textFieldNumeroPeriodos, TextField textFieldTaxaJuros, Button btnCalcular) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldValorFuturo.getText().length() == 0
				    || textFieldDuracao.getText().length() == 0
				    || textFieldNumeroPeriodos.getText().length() == 0
				    || textFieldTaxaJuros.getText().length() == 0) {
				    	btnCalcular.setDisable(true);
				    } else {
				    	btnCalcular.setDisable(false);
				    }
				});
			}
		});
	}
	
	private void setListernersValTot(TextField textField, TextField textFieldInvestimentoInicial, 
			TextField textFieldDuracao, TextField textFieldNumeroPeriodos, TextField textFieldTaxaJuros, Button btnCalcular) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldInvestimentoInicial.getText().length() == 0
				    || textFieldDuracao.getText().length() == 0
				    || textFieldNumeroPeriodos.getText().length() == 0
				    || textFieldTaxaJuros.getText().length() == 0) {
				    	btnCalcular.setDisable(true);
				    } else {
				    	btnCalcular.setDisable(false);
				    }
				});
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
		this.investimentoService = new InvestimentoServiceImpl();
		this.formatter = NumberFormat.getInstance();
		
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		
		run();
		mainApp.addThread(new Thread(this));
	}

	@FXML
	private void calcularInv() {
		textFieldInvInvestimentoInicial
							.setText(formatter
							.format(investimentoService.calcularInvestimentoInicial(new BigDecimal(textFieldInvValorFuturo.getText()), 
															ParseAno.parseToAno(new BigDecimal(textFieldInvDuracao.getText()), comboInvIniciDuracao.getValue()), 
															new BigDecimal(textFieldInvTaxaJuros.getText()), 
															ParseAno.parseToAno(new BigDecimal(textFieldInvNumeroPeriodos.getText()), comboInvIniciPeriodos.getValue())).doubleValue()));
	}

	@FXML
	private void calcularValorFuturo() {
		textFieldValValorFuturo
						.setText(formatter
						.format(investimentoService.calcularValorFuturoInvestimento(new BigDecimal(textFieldValInvestimentoInicial.getText()), 
																ParseAno.parseToAno(new BigDecimal(textFieldValDuracao.getText()), comboValFutDuracao.getValue()), 
																new BigDecimal(textFieldValTaxaJuros.getText()), 
																ParseAno.parseToAno(new BigDecimal(textFieldValNumeroPeriodos.getText()), comboValFutPeriodos.getValue())).doubleValue()));
	}

	@FXML
	private void calcularJuros() {
		textFieldJurosTaxaJuros
		.setText(formatter
		.format(investimentoService.calcularTaxaJurosNominal(Double.parseDouble(textFieldJurosInvestimentoInicial.getText()), 
												Double.parseDouble(textFieldJurosValorFuturo.getText()),
												ParseAno.parseToAno(new BigDecimal(textFieldJurosDuracao.getText()), comboJurosDuracao.getValue()).doubleValue(), 
												ParseAno.parseToAno(new BigDecimal(textFieldJurosNumeroPeriodos.getText()), comboJurosPeriodos.getValue()).doubleValue())));
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		investimentoStage.close();
		mainApp.clean(investimentoStage, this);
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

	public Stage getInvestimentoStage() {
		return investimentoStage;
	}
	
	public void setInvestimentoStage(Stage investimentoStage) {
		this.investimentoStage = investimentoStage;
	}
	
	public InvestimentoService getInvestimentoService() {
		return investimentoService;
	}
	
	public void setInvestimentoService(InvestimentoService investimentoService) {
		this.investimentoService = investimentoService;
	}
}
