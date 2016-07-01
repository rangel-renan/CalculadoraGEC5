package com.calculadora.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.model.Hipoteca;
import com.calculadora.service.FinanceiraService;
import com.calculadora.service.FinanceiraServiceImpl;
import com.calculadora.util.ParseCurrency;
import com.calculadora.util.ParseMes;
import com.calculadora.util.enums.TipoMoedas;
import com.calculadora.util.enums.TipoPeriodos;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HipotecaController implements Runnable {
	private MainApp mainApp;
	private Stage hipotecaStage;

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
	private TextField textFieldSimboloMoeda3;

	@FXML
	private TextField textFieldSimboloMoeda4;
	
	@FXML
	private TextField textFieldPrecoImovel;
	
	@FXML
	private TextField textFieldTaxaJuros;
	
	@FXML
	private TextField textFieldPrazoPagamentos;
	
	@FXML
	private TextField textFieldJuroResult;
	
	@FXML
	private TextField textFieldValorTotal;
	
	@FXML
	private TextField textFieldValorPrestacao;

	@FXML
	private Label labelError;
	
	@Override
	public void run() {
		btnCalcular.setDisable(true);
		
		setListerners(textFieldPrecoImovel);
		setListerners(textFieldTaxaJuros);
		setListerners(textFieldPrazoPagamentos);
		
		Platform.runLater(new Runnable() {
			public void run() {
				hipotecaStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
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
	
	public void show(MainApp _mainApp, Stage _hipotecaStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.hipotecaStage = _hipotecaStage;
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
				    if (textFieldPrecoImovel.getText().length() == 0 
				    || textFieldTaxaJuros.getText().length() == 0
				    || textFieldPrazoPagamentos.getText().length() == 0) {
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
		try {
			Hipoteca hipoteca = financeiraService.calcularHipoteca(ParseCurrency.parseCurrency(textFieldPrecoImovel.getText()), 
												new BigDecimal(textFieldTaxaJuros.getText()), 
												ParseMes.parseToMes(new BigDecimal(textFieldPrazoPagamentos.getText()), comboTipoPeridos.getValue()));
		
			textFieldJuroResult.setText(formatter.format(hipoteca.getJuros().doubleValue()));
			textFieldValorTotal.setText(formatter.format(hipoteca.getValorTotal().doubleValue()));
			textFieldValorPrestacao.setText(formatter.format(hipoteca.getValorPrestacao().doubleValue()));
		} catch (ParseException e) {
			labelError.setText(label.getString("error.currencyIncor"));
		}
		
	}
	
	@FXML
	private void hiddenMoeda() {
		textFieldSimboloMoeda1.setText(comboMoedas.getValue().getSimbolo());
		textFieldSimboloMoeda2.setText(comboMoedas.getValue().getSimbolo());
		textFieldSimboloMoeda3.setText(comboMoedas.getValue().getSimbolo());
		textFieldSimboloMoeda4.setText(comboMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		hipotecaStage.close();
		mainApp.clean(hipotecaStage, this);
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
	
	public Stage getHipotecaStage() {
		return hipotecaStage;
	}
	
	public void setHipotecaStage(Stage hipotecaStage) {
		this.hipotecaStage = hipotecaStage;
	}
}
