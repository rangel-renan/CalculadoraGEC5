package com.calculadora.controller;

import java.math.BigDecimal;
import java.text.ParseException;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.model.Financiamento;
import com.calculadora.service.FinanciamentoService;
import com.calculadora.service.FinanciamentoServiceImpl;
import com.calculadora.util.ParseCurrency;
import com.calculadora.util.ParseMes;
import com.calculadora.util.enums.TipoMoedas;
import com.calculadora.util.enums.TipoPeriodos;
import com.calculadora.util.enums.TipoPrestacao;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FinanciamentoController implements Runnable {
	private MainApp mainApp;
	private Stage financiamentoStage;

	private ConfigProperties label;
	private FinanciamentoService financiamentoService;

	@FXML
	private Button btnCalcular;
	
	@FXML
	private TableView<Financiamento> tabelaDetlFinanciamento;
	
	@FXML
	private TableColumn<Financiamento, String> colunaNumParcela;
	
	@FXML
	private TableColumn<Financiamento, String> colunaValorParcela;
	
	@FXML
	private TableColumn<Financiamento, String> colunaAmortizacoes;
	
	@FXML
	private TableColumn<Financiamento, String> colunaJuros;
	
	@FXML
	private TableColumn<Financiamento, String> colunaSaldoDevedor;
	
	@FXML
	private ComboBox<TipoMoedas> comboMoedas;
	
	@FXML
	private ComboBox<TipoPrestacao> comboTipoPrestacao;
	
	@FXML
	private ComboBox<TipoPeriodos> comboTipoPeridos;
	
	@FXML
	private TextField textFieldSimboloMoeda;
	
	@FXML
	private TextField textFieldValorFinanciado;
	
	@FXML
	private TextField textFieldTaxaJuros;
	
	@FXML
	private TextField textFieldPeriodo;

	@FXML
	private Label labelError;
	
	@Override
	public void run() {
		btnCalcular.setDisable(true);
		
		setListerners(textFieldValorFinanciado);
		setListerners(textFieldTaxaJuros);
		setListerners(textFieldPeriodo);
		
		Platform.runLater(new Runnable() {
			public void run() {
				financiamentoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
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
				comboTipoPrestacao.setItems(FXCollections.observableArrayList(TipoPrestacao.values())); 
				comboTipoPrestacao.getSelectionModel().select(0);
			}
		});
		
		Platform.runLater(new Runnable() {
			public void run() { 
				comboTipoPeridos.setItems(FXCollections.observableArrayList(TipoPeriodos.values())); 
				comboTipoPeridos.getItems().remove(0);
				comboTipoPeridos.getSelectionModel().select(0);
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage _financiamentoStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.financiamentoStage = _financiamentoStage;
		this.label = label;
		this.financiamentoService = new FinanciamentoServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	private void setListerners(TextField textField) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldValorFinanciado.getText().length() == 0 
				    || textFieldTaxaJuros.getText().length() == 0
				    || textFieldPeriodo.getText().length() == 0) {
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
			ObservableList<Financiamento> listaFinanciamentos = financiamentoService.calcularFinanciamento(ParseCurrency.parseCurrency(textFieldValorFinanciado.getText()), 
																	new BigDecimal(textFieldTaxaJuros.getText()), 
																	ParseMes.parseToMes(new BigDecimal(textFieldPeriodo.getText()), comboTipoPeridos.getValue()), 
																	comboTipoPrestacao.getValue());
			preenxerTabela(listaFinanciamentos);
		} catch (ParseException e) {
			labelError.setText(label.getString("error.currencyIncor"));
		}
	}

	private void preenxerTabela(ObservableList<Financiamento> listaFinanciamentos) {
		colunaNumParcela.setCellValueFactory(new PropertyValueFactory<>("numeroParcela"));
		colunaValorParcela.setCellValueFactory(new PropertyValueFactory<>("parcela"));
		colunaAmortizacoes.setCellValueFactory(new PropertyValueFactory<>("amortizacaoAoMes"));
		colunaJuros.setCellValueFactory(new PropertyValueFactory<>("jurosAoMes"));
		colunaSaldoDevedor.setCellValueFactory(new PropertyValueFactory<>("saldoDevedorAoMes"));
		
		tabelaDetlFinanciamento.setItems(listaFinanciamentos);
	}
	
	@FXML
	private void hiddenMoeda() {
		textFieldSimboloMoeda.setText(comboMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		financiamentoStage.close();
		mainApp.clean(financiamentoStage, this);
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
	
	public FinanciamentoService getFinanciamentoService() {
		return financiamentoService;
	}
	
	public void setFinanciamentoService(FinanciamentoService financiamentoService) {
		this.financiamentoService = financiamentoService;
	}
	
	public void setFinanciamentoStage(Stage financiamentoStage) {
		this.financiamentoStage = financiamentoStage;
	}
	
	public Stage getFinanciamentoStage() {
		return financiamentoStage;
	}
}
