package com.calculadora.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.model.CartaoCredito;
import com.calculadora.service.FinanceiraService;
import com.calculadora.service.FinanceiraServiceImpl;
import com.calculadora.util.enums.TipoMoedas;
import com.calculadora.util.excessoes.PagamentoMinimoMaiorParcelaException;

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

public class CartaoCreditoController implements Runnable {
	private MainApp mainApp;
	private Stage cartaoCreditoStage;
	
	private NumberFormat formatter;
	private ConfigProperties label;
	private FinanceiraService financeiraService;
	
	@FXML
	private Button btnCalcular;
	
	@FXML
	private ComboBox<TipoMoedas> comboMoedas;
	
	@FXML
	private TextField textFieldSimboloMoeda1;
	
	@FXML
	private TextField textFieldSimboloMoeda2;

	@FXML
	private TextField textFieldSimboloMoeda3;
	
	@FXML
	private TextField textFieldSimboloMoeda4;
	
	@FXML
	private TextField textFieldSaldoCartao;
	
	@FXML
	private TextField textFieldTaxaJuros;
	
	@FXML
	private TextField textFieldValorParcela;
	
	@FXML
	private TextField textFieldTotalJuros;
	
	@FXML
	private TextField textFieldBalancoFinal;
	
	@FXML
	private TextField textFieldTotalMeses;
	
	@FXML
	private Label labelError;
	
	@Override
	public void run() {
		btnCalcular.setDisable(true);
		
		setListerners(textFieldSaldoCartao);
		setListerners(textFieldTaxaJuros);
		setListerners(textFieldValorParcela);
		
		Platform.runLater(new Runnable() {
			public void run() {
				cartaoCreditoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
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
	}
	
	public void show(MainApp _mainApp, Stage _cartaoCreditoStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.cartaoCreditoStage = _cartaoCreditoStage;
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
				    if (textFieldSaldoCartao.getText().length() == 0 
				    || textFieldTaxaJuros.getText().length() == 0
				    || textFieldValorParcela.getText().length() == 0) {
				    	btnCalcular.setDisable(true);
				    } else {
				    	btnCalcular.setDisable(false);
				    }
				});
			}
		});
	}
	
	@FXML
	private void hiddenMoeda() {
		textFieldSimboloMoeda1.setText(comboMoedas.getValue().getSimbolo());
		textFieldSimboloMoeda2.setText(comboMoedas.getValue().getSimbolo());
		textFieldSimboloMoeda3.setText(comboMoedas.getValue().getSimbolo());
		textFieldSimboloMoeda4.setText(comboMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void calcular() {
		try {
			CartaoCredito cartaoCredito = financeiraService.calcularCartaoCredito(new BigDecimal(textFieldSaldoCartao.getText()), 
																		new BigDecimal(textFieldTaxaJuros.getText()), 
																		new BigDecimal(textFieldValorParcela.getText()));
			
			textFieldTotalJuros.setText(formatter.format(cartaoCredito.getTotalDeJuros().doubleValue()));
			textFieldBalancoFinal.setText(formatter.format((cartaoCredito.getBalancoFinal().doubleValue())));
			textFieldTotalMeses.setText(Integer.toString(cartaoCredito.getTotalMeses()));
			
		} catch (PagamentoMinimoMaiorParcelaException e) {
			labelError.setText(label.getString("root.tab.financeiro.cartaoCredito.erroParcela"));
		}
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		cartaoCreditoStage.close();
		mainApp.clean(cartaoCreditoStage, this);
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
