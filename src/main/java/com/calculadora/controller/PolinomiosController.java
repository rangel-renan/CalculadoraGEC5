package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.model.Polinomio;
import com.calculadora.service.PolinomioService;
import com.calculadora.service.PolinomioServiceImpl;
import com.calculadora.util.enums.TipoOperacao;
import com.calculadora.util.excessoes.PolinomioFormatoInvalidoException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PolinomiosController implements Runnable {
	private MainApp mainApp;
	private Stage polinomiosStage;
	
	private ConfigProperties label;
	private PolinomioService polinomioService;
	
	@FXML
	private Button btnASomaB;
	
	@FXML
	private Button btnASubtraiB;
	
	@FXML
	private Button btnAMultB;
	
	@FXML
	private Button btnARaizes;
	
	@FXML
	private Button btnADerivada;
	
	@FXML
	private Button btnBRaizes;
	
	@FXML
	private Button btnBDerivada;
	
	@FXML
	private TextField textFieldFirstPolinomio;
	
	@FXML
	private TextField textFieldSecondPolinomio;
	
	@FXML
	private TextField textFieldResult;
	
	@FXML
	private TextField textFieldFirstRaizesResul;
	
	@FXML
	private TextField textFieldFirstDerivadaResul;
	
	@FXML
	private TextField textFieldSecondRaizesResul;
	
	@FXML
	private TextField textFieldSecondDerivadaResul;
	
	@FXML
	private Label labelError;
	
	@Override
	public void run() {
		btnASomaB.setDisable(true);
		btnASubtraiB.setDisable(true);
		btnAMultB.setDisable(true);
		btnARaizes.setDisable(true);
		btnADerivada.setDisable(true);
		btnBRaizes.setDisable(true);
		btnBDerivada.setDisable(true);
		
		setListerners(textFieldFirstPolinomio);
		setListerners(textFieldSecondPolinomio);
		setListerners(textFieldFirstPolinomio, btnARaizes, btnADerivada);
		setListerners(textFieldSecondPolinomio, btnBRaizes, btnBDerivada);
		
		Platform.runLater(new Runnable() {
			public void run() {
				polinomiosStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	private void setListerners(TextField textArea) {
		Platform.runLater(new Runnable() {
			public void run() {
				textArea.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textFieldFirstPolinomio.getText().length() == 0
				    	|| textFieldSecondPolinomio.getText().length() == 0) {
				    	btnASomaB.setDisable(true);
				    	btnASubtraiB.setDisable(true);
				    	btnAMultB.setDisable(true);
				    } else {
				    	btnASomaB.setDisable(false);
				    	btnASubtraiB.setDisable(false);
				    	btnAMultB.setDisable(false);
				    }
				});
			}
		});
	}
	
	private void setListerners(TextField textField, Button btnRaiz, Button btnDerivada) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textField.getText().length() == 0) {
				    	btnRaiz.setDisable(true);
				    	btnDerivada.setDisable(true);
				    } else {
				    	btnRaiz.setDisable(false);
				    	btnDerivada.setDisable(false);
				    }
				});
			}
		});
	}
	
	
	public void show(MainApp _mainApp, Stage polinomiosStage, ConfigProperties label) {
		this.mainApp = _mainApp;
		this.polinomiosStage = polinomiosStage;
		this.label = label;
		this.polinomioService = new PolinomioServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	@FXML
	private void handleOperacao(ActionEvent actionEvent) {
		try {
			switch (((Button) actionEvent.getSource()).getText()) {
				case "q(x) + p(x)":
					calcularOperacao(new Polinomio(textFieldFirstPolinomio.getText()), new Polinomio(textFieldSecondPolinomio.getText()), TipoOperacao.SOMA);
					break;
				case "q(x) - p(x)":
					calcularOperacao(new Polinomio(textFieldFirstPolinomio.getText()), new Polinomio(textFieldSecondPolinomio.getText()), TipoOperacao.SUBTRACAO);
					break;
				case "q(x) × p(x)":
					calcularOperacao(new Polinomio(textFieldFirstPolinomio.getText()), new Polinomio(textFieldSecondPolinomio.getText()), TipoOperacao.MULTIPLICACAO);
			}
		} catch (PolinomioFormatoInvalidoException e) {
			labelError.setText(label.getString("root.tab.arquivo.polinomios.errorPolinomio"));
		}
	}
	
	private void calcularOperacao(Polinomio firstPolinomio, Polinomio secondPolinomio, TipoOperacao operacao) {
		textFieldResult.setText((polinomioService.operacao(firstPolinomio, secondPolinomio, operacao)).toString());
	}
	
	@FXML
	private void handleCalcularRaizes(ActionEvent actionEvent) {
		try {
			switch (((Button) actionEvent.getSource()).getId()) {
				case "pA":
					calcularRaizes(textFieldFirstRaizesResul, new Polinomio(textFieldFirstPolinomio.getText()));
					break;
				case "pB":
					calcularRaizes(textFieldSecondRaizesResul, new Polinomio(textFieldSecondPolinomio.getText()));
					break;
			}
		} catch (PolinomioFormatoInvalidoException e) {
			labelError.setText(label.getString("root.tab.arquivo.polinomios.errorPolinomio"));
		}
	}
	
	private void calcularRaizes(TextField textFieldResul, Polinomio polinomio) {
		textFieldResul.setText((polinomioService.getRaizes(polinomio)).toString());
	}
	
	@FXML
	private void handleCalcularDerivadas(ActionEvent actionEvent) {
		try {
			switch (((Button) actionEvent.getSource()).getId()) {
			case "pA":
				calcularDerivada(textFieldFirstDerivadaResul, new Polinomio(textFieldFirstPolinomio.getText()));
				break;
			case "pB":
				calcularDerivada(textFieldSecondDerivadaResul, new Polinomio(textFieldSecondPolinomio.getText()));
				break;
			}
		} catch (PolinomioFormatoInvalidoException e) {
			labelError.setText(label.getString("root.tab.arquivo.polinomios.errorPolinomio"));
		}
	}
	
	private void calcularDerivada(TextField textFieldResul, Polinomio polinomio) {
		textFieldResul.setText((polinomioService.derivada(polinomio)).toString());
	}
	
	@FXML
	private void handleLimparTudo() {
		textFieldFirstPolinomio.setText("");
		textFieldSecondPolinomio.setText("");
		textFieldResult.setText("");
		textFieldFirstRaizesResul.setText("");
		textFieldFirstDerivadaResul.setText("");
		textFieldSecondRaizesResul.setText("");
		textFieldSecondDerivadaResul.setText("");
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		polinomiosStage.close();
		mainApp.clean(polinomiosStage, this);
	}
	
	@FXML
	private void handleAjuda() {
		
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

	public Stage getPolinomiosStage() {
		return polinomiosStage;
	}

	public void setPolinomiosStage(Stage polinomiosStage) {
		this.polinomiosStage = polinomiosStage;
	}

	public PolinomioService getPolinomioService() {
		return polinomioService;
	}

	public void setPolinomioService(PolinomioService polinomioService) {
		this.polinomioService = polinomioService;
	}

	public MainApp getMainApp() {
		return mainApp;
	}
	
	
}
