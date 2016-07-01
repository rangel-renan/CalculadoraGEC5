package com.calculadora.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.PoupancaService;
import com.calculadora.service.PoupancaServiceImpl;
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

@SuppressWarnings({"rawtypes", "unchecked"})
public class PoupancaController implements Runnable {
	private MainApp mainApp;
	private Stage poupancaStage;

	private NumberFormat formatter;
	private ConfigProperties label;
	private PoupancaService poupancaService;
	
	@FXML
	private Button btnDepRegulCalcular;

	@FXML
	private Button btnValTotalCalcular;
	
	@FXML
	private ComboBox<TipoMoedas> comboDepRegulMoedas;
	
	@FXML
	private ComboBox<TipoMoedas> comboValTotalMoedas;
	

	@FXML
	private ComboBox<TipoPeriodos> comboDepRegulTipoPeriodos;
	

	@FXML
	private ComboBox<TipoPeriodos> comboValTipoPeriodos;

	@FXML
	private TextField textFieldDepRegulSimboloMoeda1;
	
	@FXML
	private TextField textFieldDepRegulSimboloMoeda2;

	@FXML
	private TextField textFieldValTotalSimboloMoeda1;
	
	@FXML
	private TextField textFieldValTotalSimboloMoeda2;
	
	@FXML
	private TextField textFieldDepRegulValorTotal;
	
	@FXML
	private TextField textFieldDepRegulTaxaJuros;
	
	@FXML
	private TextField textFieldDepRegulNumDepositos;
	
	@FXML
	private TextField textFieldDepRegulDuracao;
	
	@FXML
	private TextField textFieldDepRegulValorDeposito;
	
	@FXML
	private TextField textFieldValTotalValorTotal;
	
	@FXML
	private TextField textFieldValTotalTaxaJuros;
	
	@FXML
	private TextField textFieldValTotalNumDepositos;
	
	@FXML
	private TextField textFieldValTotalDuracao;
	
	@FXML
	private TextField textFieldValTotalValorDeposito;
	
	@FXML
	private Label labelDepRegulError;
	
	@FXML
	private Label labelValTotalError;
	
	@Override
	public void run() {
		btnDepRegulCalcular.setDisable(true);
		btnValTotalCalcular.setDisable(true);
		
		setListernersDepRegul(textFieldDepRegulValorTotal, textFieldDepRegulValorTotal, textFieldDepRegulTaxaJuros, textFieldDepRegulNumDepositos, textFieldDepRegulDuracao, btnDepRegulCalcular);
		setListernersDepRegul(textFieldDepRegulTaxaJuros, textFieldDepRegulValorTotal, textFieldDepRegulTaxaJuros, textFieldDepRegulNumDepositos, textFieldDepRegulDuracao, btnDepRegulCalcular);
		setListernersDepRegul(textFieldDepRegulNumDepositos, textFieldDepRegulValorTotal, textFieldDepRegulTaxaJuros, textFieldDepRegulNumDepositos, textFieldDepRegulDuracao, btnDepRegulCalcular);
		setListernersDepRegul(textFieldDepRegulDuracao, textFieldDepRegulValorTotal, textFieldDepRegulTaxaJuros, textFieldDepRegulNumDepositos, textFieldDepRegulDuracao, btnDepRegulCalcular);
		
		setListernersValTotal(textFieldValTotalTaxaJuros, textFieldValTotalTaxaJuros, textFieldValTotalNumDepositos, textFieldValTotalDuracao, textFieldValTotalValorDeposito, btnValTotalCalcular);
		setListernersValTotal(textFieldValTotalNumDepositos, textFieldValTotalTaxaJuros, textFieldValTotalNumDepositos, textFieldValTotalDuracao, textFieldValTotalValorDeposito, btnValTotalCalcular);
		setListernersValTotal(textFieldValTotalDuracao, textFieldValTotalTaxaJuros, textFieldValTotalNumDepositos, textFieldValTotalDuracao, textFieldValTotalValorDeposito, btnValTotalCalcular);
		setListernersValTotal(textFieldValTotalValorDeposito, textFieldValTotalTaxaJuros, textFieldValTotalNumDepositos, textFieldValTotalDuracao, textFieldValTotalValorDeposito, btnValTotalCalcular);
		
		Platform.runLater(new Runnable() {
			public void run() {
				poupancaStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});

		preenxerCombo(comboDepRegulMoedas, 0);
		preenxerCombo(comboValTotalMoedas, 0);
		preenxerComboPeriodos(comboDepRegulTipoPeriodos);
		preenxerComboPeriodos(comboValTipoPeriodos);
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
				combo.getItems().remove(0);
				combo.getSelectionModel().select(0);
			}
		});
	}

	private void setListernersDepRegul(TextField textField, TextField textFieldDepRegulValorTotal, TextField textFieldDepRegulTaxaJuros, 
			TextField textFieldDepRegulNumDepositos, TextField textFieldDepRegulDuracao, Button btnCalcular) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldDepRegulValorTotal.getText().length() == 0 
				    || textFieldDepRegulTaxaJuros.getText().length() == 0
				    || textFieldDepRegulNumDepositos.getText().length() == 0
				    || textFieldDepRegulDuracao.getText().length() == 0) {
				    	btnCalcular.setDisable(true);
				    } else {
				    	btnCalcular.setDisable(false);
				    }
				});
			}
		});
	}

	private void setListernersValTotal(TextField textField, TextField textFieldValTotalTaxaJuros, TextField textFieldValTotalNumDepositos, 
			TextField textFieldValTotalDuracao, TextField textFieldValTotalValorDeposito, Button btnCalcular) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldValTotalTaxaJuros.getText().length() == 0 
				    || textFieldValTotalNumDepositos.getText().length() == 0
				    || textFieldValTotalDuracao.getText().length() == 0
				    || textFieldValTotalValorDeposito.getText().length() == 0) {
				    	btnCalcular.setDisable(true);
				    } else {
				    	btnCalcular.setDisable(false);
				    }
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage _poupancaStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.poupancaStage = _poupancaStage;
		this.label = label;
		this.poupancaService = new PoupancaServiceImpl();
		this.formatter = NumberFormat.getInstance();
		
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void hiddenDepRegulMoeda() {
		textFieldDepRegulSimboloMoeda1.setText(comboDepRegulMoedas.getValue().getSimbolo());
		textFieldDepRegulSimboloMoeda2.setText(comboDepRegulMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void hiddenValTotalMoeda() {
		textFieldValTotalSimboloMoeda1.setText(comboValTotalMoedas.getValue().getSimbolo());
		textFieldValTotalSimboloMoeda2.setText(comboValTotalMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		poupancaStage.close();
		mainApp.clean(poupancaStage, this);
	}
	
	@FXML
	private void calcularValTotal() {
		try {
			textFieldValTotalValorTotal
									.setText(formatter
									.format(poupancaService.calcularValorTotal(ParseCurrency.parseCurrency(textFieldValTotalValorDeposito.getText()), 
																	ParseCurrency.parseCurrency(textFieldValTotalTaxaJuros.getText()), 
																	new BigDecimal(textFieldValTotalNumDepositos.getText()), 
																	ParseMes.parseToMes(new BigDecimal(textFieldValTotalDuracao.getText()), comboValTipoPeriodos.getValue())).doubleValue()));
		} catch (ParseException e) {
			labelValTotalError.setText(label.getString("error.currencyIncor"));
		}
	}

	@FXML
	private void calcularDepRegul() {
		try {
			textFieldDepRegulValorDeposito
									.setText(formatter
									.format(poupancaService.calcularValorDepositos(ParseCurrency.parseCurrency(textFieldDepRegulValorTotal.getText()), 
																	new BigDecimal(textFieldDepRegulTaxaJuros.getText()), 
																	ParseCurrency.parseCurrency(textFieldDepRegulNumDepositos.getText()), 
																	ParseMes.parseToMes(new BigDecimal(textFieldDepRegulDuracao.getText()), comboDepRegulTipoPeriodos.getValue())).doubleValue()));
		} catch (ParseException e) {
			labelDepRegulError.setText(label.getString("error.currencyIncor"));
		}
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
	
	public PoupancaService getPoupancaService() {
		return poupancaService;
	}
	
	public void setPoupancaService(PoupancaService poupancaService) {
		this.poupancaService = poupancaService;
	}
	
	public Stage getPoupancaStage() {
		return poupancaStage;
	}
	
	public void setPoupancaStage(Stage poupancaStage) {
		this.poupancaStage = poupancaStage;
	}
}
