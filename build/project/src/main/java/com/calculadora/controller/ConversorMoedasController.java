package com.calculadora.controller;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.ParseException;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.ConversaoService;
import com.calculadora.service.ConversaoServiceImpl;
import com.calculadora.util.ParseCurrency;
import com.calculadora.util.enums.TipoMoedas;
import com.calculadora.util.excessoes.ImpossivelConverterException;

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
public class ConversorMoedasController implements Runnable {
	private MainApp mainApp;
	private Stage conversaoMoedasStage;

	private NumberFormat formatter;
	private ConfigProperties label;
	private ConversaoService conversaoService;
	
	@FXML
	private Button btnCalcular;
	
	@FXML
	private ComboBox<TipoMoedas> comboFirstMoedas;
	
	@FXML
	private ComboBox<TipoMoedas> comboSecondMoedas;
	
	@FXML
	private TextField textFieldSimboloMoeda1;
	
	@FXML
	private TextField textFieldSimboloMoeda2;
	
	@FXML
	private TextField textFieldValor;
	
	@FXML
	private TextField textFieldResultado;
	
	@FXML
	private Label labelError;
	
	@Override
	public void run() {
		btnCalcular.setDisable(true);
		
		Platform.runLater(new Runnable() {
			public void run() {
				textFieldValor.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldValor.getText().length() == 0)
				    	btnCalcular.setDisable(true);
				    else
				    	btnCalcular.setDisable(false);		  
				});
			}
		});
		
		Platform.runLater(new Runnable() {
			public void run() {
				conversaoMoedasStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage _conversaoMoedasStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.conversaoMoedasStage = _conversaoMoedasStage;
		this.label = label;
		this.conversaoService = new ConversaoServiceImpl();
		this.formatter = NumberFormat.getInstance();
		
		preenxerCombo(comboFirstMoedas, 0);
		preenxerCombo(comboSecondMoedas, 1);

		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	private void preenxerCombo(ComboBox combo, int index) {
		Platform.runLater(new Runnable() {
			public void run() { 
				combo.setItems(FXCollections.observableArrayList(TipoMoedas.values())); 
				combo.getSelectionModel().select(index);
			}
		});
	}
	
	@FXML
	private void calcular() {
		try {
			textFieldResultado.setText(formatter.format(conversaoService.converterMoeda(ParseCurrency.parseCurrency(textFieldValor.getText()), 
														comboFirstMoedas.getValue().getCodigoISO(), 
														comboSecondMoedas.getValue().getCodigoISO()).doubleValue()));
		} catch (NumberFormatException e) {
			labelError.setText(label.getString("root.tab.financeiro.conversorMoeda.errorFormatNum"));
		} catch (ConnectException e) {
			labelError.setText(label.getString("root.tab.financeiro.conversorMoeda.errorConexao"));
		} catch (MalformedURLException e) {
			labelError.setText(label.getString("root.tab.financeiro.conversorMoeda.errorValInexis"));
		} catch (IOException e) {
			labelError.setText(label.getString("root.tab.financeiro.conversorMoeda.errorConexao"));
		} catch (ImpossivelConverterException e) {
			labelError.setText(label.getString("root.tab.financeiro.conversorMoeda.errorValInexis"));
		} catch (ParseException e) {
			labelError.setText(label.getString("error.currencyIncor"));
		}
	}
	
	@FXML
	private void hiddenFirstMoeda() {
		textFieldSimboloMoeda1.setText(comboFirstMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void hiddenSecondMoeda() {
		textFieldSimboloMoeda2.setText(comboSecondMoedas.getValue().getSimbolo());
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		conversaoMoedasStage.close();
		mainApp.clean(conversaoMoedasStage, this);
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

	public Stage getConversaoMoedasStage() {
		return conversaoMoedasStage;
	}

	public void setConversaoMoedasStage(Stage conversaoMoedasStage) {
		this.conversaoMoedasStage = conversaoMoedasStage;
	}

	public ConversaoService getConversaoService() {
		return conversaoService;
	}

	public void setConversaoService(ConversaoService conversaoService) {
		this.conversaoService = conversaoService;
	}
	
}
