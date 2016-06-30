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

@SuppressWarnings({"rawtypes", "unchecked"})
public class JurosController implements Runnable {
	private MainApp mainApp;
	private Stage jurosStage;

	private NumberFormat formatter;
	private ConfigProperties label;
	private FinanceiraService financeiraService;

	@FXML
	private Button btnJurSimCalcular;

	@FXML
	private Button btnJurCompCalcular;
	
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
	
	@FXML
	private TextField textFieldJurSimInvestInicial;
	
	@FXML
	private TextField textFieldJurSimTaxJuros;
	
	@FXML
	private TextField textFieldJurSimNumPeriodo;
	
	@FXML
	private TextField textFieldJurSimValorFuturo;
	
	@FXML
	private TextField textFieldJurCompInvestInicial;
	
	@FXML
	private TextField textFieldJurCompTaxJuros;
	
	@FXML
	private TextField textFieldJurCompNumPeriodo;
	
	@FXML
	private TextField textFieldJurCompValorFuturo;
	
	@Override
	public void run() {
		btnJurSimCalcular.setDisable(true);
		btnJurCompCalcular.setDisable(true);
		
		setListerners(textFieldJurSimInvestInicial, textFieldJurSimInvestInicial, textFieldJurSimTaxJuros, textFieldJurSimNumPeriodo, btnJurSimCalcular);
		setListerners(textFieldJurSimTaxJuros, textFieldJurSimInvestInicial, textFieldJurSimTaxJuros, textFieldJurSimNumPeriodo, btnJurSimCalcular);
		setListerners(textFieldJurSimNumPeriodo, textFieldJurSimInvestInicial, textFieldJurSimTaxJuros, textFieldJurSimNumPeriodo, btnJurSimCalcular);
		
		setListerners(textFieldJurCompInvestInicial, textFieldJurCompInvestInicial, textFieldJurCompTaxJuros, textFieldJurCompNumPeriodo, btnJurCompCalcular);
		setListerners(textFieldJurCompTaxJuros, textFieldJurCompInvestInicial, textFieldJurCompTaxJuros, textFieldJurCompNumPeriodo, btnJurCompCalcular);
		setListerners(textFieldJurCompNumPeriodo, textFieldJurCompInvestInicial, textFieldJurCompTaxJuros, textFieldJurCompNumPeriodo, btnJurCompCalcular);
		
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

	private void setListerners(TextField textField, TextField textFieldJurInvestInicial, TextField textFieldSimTaxJuros, 
			TextField textFieldSimNumPeriodo, Button btnCalcular) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldJurInvestInicial.getText().length() == 0 
				    || textFieldSimTaxJuros.getText().length() == 0
				    || textFieldSimNumPeriodo.getText().length() == 0) {
				    	btnCalcular.setDisable(true);
				    } else {
				    	btnCalcular.setDisable(false);
				    }
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage jurosStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.jurosStage = jurosStage;
		this.label = label;
		this.financeiraService = new FinanceiraServiceImpl();
		this.formatter = NumberFormat.getInstance();
		
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		
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
	};
	
	@FXML
	private void calcularJurosSimples() {
		textFieldJurSimValorFuturo
								.setText(formatter
								.format(financeiraService.calcularJuros(new BigDecimal(textFieldJurSimInvestInicial.getText()), 
												new BigDecimal(textFieldJurSimTaxJuros.getText()), 
												ParseMes.parseToMes(new BigDecimal(textFieldJurSimNumPeriodo.getText()), comboJurSimTipoPeriodos.getValue())).doubleValue()));
	}

	@FXML
	private void calcularJurosCompostos() {
		textFieldJurCompValorFuturo
								.setText(formatter
								.format(financeiraService.calcularJurosComposto(new BigDecimal(textFieldJurCompInvestInicial.getText()), 
												new BigDecimal(textFieldJurCompTaxJuros.getText()), 
												ParseMes.parseToMes(new BigDecimal(textFieldJurCompNumPeriodo.getText()), comboJurCompTipoPeriodos.getValue()).intValue()).doubleValue()));
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
