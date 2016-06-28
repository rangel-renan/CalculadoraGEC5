package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.PoupancaService;
import com.calculadora.service.PoupancaServiceImpl;
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
public class PoupancaController implements Runnable {
	private MainApp mainApp;
	private Stage poupancaStage;

	private ConfigProperties label;
	private PoupancaService poupancaService;

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

	@Override
	public void run() {
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
				combo.getSelectionModel().select(0);
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage _poupancaStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.poupancaStage = _poupancaStage;
		this.label = label;
		this.poupancaService = new PoupancaServiceImpl();
		
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
