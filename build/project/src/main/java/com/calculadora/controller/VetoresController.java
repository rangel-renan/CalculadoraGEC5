package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.VetoresService;
import com.calculadora.service.VetoresServiceImpl;
import com.calculadora.util.excessoes.VetorTamanhoExcedidoException;
import com.calculadora.util.excessoes.VetoresTamanhosDiferentesException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VetoresController implements Runnable {
	private MainApp mainApp;
	private Stage vetorStage;

	private ConfigProperties label;
	private VetoresService vetorService;

	@FXML
	private TextField textFieldvetorA;

	@FXML
	private TextField textFieldvetorB;

	@FXML
	private TextField textFieldResult;

	@FXML
	private TextField textFieldMultiPorA;

	@FXML
	private TextField textFieldMultiPorB;

	@FXML
	private TextField textFieldElevadoPorA;

	@FXML
	private TextField textFieldElevadoPorB;

	@FXML
	private Button btnAsomaB;

	@FXML
	private Button btnAsubtraiB;

	@FXML
	private Button btnModuloA;

	@FXML
	private Button btnMultiplicaPorA;

	@FXML
	private Button btnElevadoPorA;

	@FXML
	private Button btnModuloB;

	@FXML
	private Button btnMultiplicaPorB;

	@FXML
	private Button btnElevadoPorB;

	@FXML
	private Button btnEscalar;

	@FXML
	private Button btnVetorial;

	@FXML
	private Button btnProjecao;

	@FXML
	private Button btnCosseno;
	
	@Override
	public void run() {
		btnAsomaB.setDisable(true);
		btnAsubtraiB.setDisable(true);
		btnModuloA.setDisable(true);
		btnMultiplicaPorA.setDisable(true);
		btnElevadoPorA.setDisable(true);
		btnModuloB.setDisable(true);
		btnMultiplicaPorB.setDisable(true);
		btnElevadoPorB.setDisable(true);
		btnEscalar.setDisable(true);
		btnVetorial.setDisable(true);
		btnProjecao.setDisable(true);
		btnCosseno.setDisable(true);

		setListerners(textFieldvetorA);
		setListerners(textFieldvetorB);
		setListerners(textFieldvetorA, textFieldMultiPorA, btnMultiplicaPorA);
		setListerners(textFieldMultiPorA, textFieldvetorA, btnMultiplicaPorA);
		setListerners(textFieldvetorB, textFieldMultiPorB, btnMultiplicaPorB);
		setListerners(textFieldMultiPorB, textFieldvetorB, btnMultiplicaPorB);
		setListerners(textFieldvetorA, textFieldElevadoPorA, btnElevadoPorA);
		setListerners(textFieldElevadoPorA, textFieldvetorA, btnElevadoPorA);
		setListerners(textFieldvetorB, textFieldElevadoPorB, btnElevadoPorB);
		setListerners(textFieldElevadoPorB, textFieldvetorB, btnElevadoPorB);
		setListernersVetorA(textFieldvetorA);
		setListernersVetorB(textFieldvetorB);
		
		Platform.runLater(new Runnable() {
			public void run() {
				vetorStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage vetorStage, ConfigProperties label) {
		this.label = label;
		this.mainApp = _mainApp;
		this.vetorStage = vetorStage;
		this.vetorService = new VetoresServiceImpl();

		run();
		mainApp.addThread(new Thread(this));
	}

	private void setListernersVetorA(TextField label) {
		Platform.runLater(new Runnable() {
			public void run() {
				label.textProperty().addListener((observable, oldValue, newValue) -> {
					if (label.getText().length() == 0) {
						btnModuloA.setDisable(true);
					} else {
						btnModuloA.setDisable(false);
					}
				});
			}
		});
	}

	private void setListernersVetorB(TextField label) {
		Platform.runLater(new Runnable() {
			public void run() {
				label.textProperty().addListener((observable, oldValue, newValue) -> {
					if (label.getText().length() == 0) {
						btnModuloB.setDisable(true);
					} else {
						btnModuloB.setDisable(false);
					}
				});
			}
		});
	}

	private void setListerners(TextField textArea, TextField textField, Button btnOperacao) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
					if (textField.getText().length() == 0 || textArea.getText().length() == 0) {
						btnOperacao.setDisable(true);
					} else {
						btnOperacao.setDisable(false);
					}
				});
			}
		});
	}

	private void setListerners(TextField textArea) {
		Platform.runLater(new Runnable() {
			public void run() {
				textArea.textProperty().addListener((observable, oldValue, newValue) -> {
					if (textFieldvetorA.getText().length() == 0 || textFieldvetorB.getText().length() == 0) {
						btnAsomaB.setDisable(true);
						btnAsubtraiB.setDisable(true);
						btnEscalar.setDisable(true);
						btnVetorial.setDisable(true);
						btnProjecao.setDisable(true);
						btnCosseno.setDisable(true);
					} else {
						btnAsomaB.setDisable(false);
						btnAsubtraiB.setDisable(false);
						btnEscalar.setDisable(false);
						btnVetorial.setDisable(false);
						btnProjecao.setDisable(false);
						btnCosseno.setDisable(false);
					}
				});
			}
		});
	}

	@FXML
	private void handleSoma() {
		try {
			textFieldResult.setText(mostrarVetor(
					vetorService.calcularSoma(convertVetor(textFieldvetorA), convertVetor(textFieldvetorB))));
		} catch (VetoresTamanhosDiferentesException e) {
			textFieldResult.setText(label.getString("root.tab.vetor.erroTamanDifer"));
		}
	}

	@FXML
	private void handleSubtracao() {
		try {
			textFieldResult.setText(mostrarVetor(
					vetorService.calcularSubtracao(convertVetor(textFieldvetorA), convertVetor(textFieldvetorB))));
		} catch (VetoresTamanhosDiferentesException e) {
			textFieldResult.setText(label.getString("root.tab.vetor.erroTamanDifer"));
		}
	}

	@FXML
	private void handleElevadoPor(ActionEvent actionEvent) {
		String vetorEmUso = ((Button) actionEvent.getSource()).getId();

		if (vetorEmUso.equals("vA"))
			elevadoPor(convertVetor(textFieldvetorA), Double.valueOf(textFieldElevadoPorA.getText()));
		else if (vetorEmUso.equals("vB"))
			elevadoPor(convertVetor(textFieldvetorB), Double.valueOf(textFieldElevadoPorA.getText()));
	}

	private void elevadoPor(Double[] vetor, Double valor) {
		textFieldResult.setText(mostrarVetor(vetorService.elevarPor(valor, vetor)));
	}

	@FXML
	private void handleMultiplicaPor(ActionEvent actionEvent) {
		String vetorEmUso = ((Button) actionEvent.getSource()).getId();

		if (vetorEmUso.equals("vA"))
			multiplicaPor(convertVetor(textFieldvetorA), Double.valueOf(textFieldMultiPorA.getText()));
		else if (vetorEmUso.equals("vB"))
			multiplicaPor(convertVetor(textFieldvetorB), Double.valueOf(textFieldMultiPorB.getText()));
	}

	private void multiplicaPor(Double[] vetor, Double valor) {
		textFieldResult.setText(mostrarVetor(vetorService.multiplicarPor(valor, vetor)));
	}

	@FXML
	private void handleEscalar() {
		try {
			textFieldResult.setText(label.getString("root.tab.vetor.escalar") + ": " + vetorService
					.calcularProdutoEscalar(convertVetor(textFieldvetorA), convertVetor(textFieldvetorB)));
		} catch (VetoresTamanhosDiferentesException e) {
			textFieldResult.setText(label.getString("root.tab.vetor.erroTamanDifer"));
		}
	}

	@FXML
	private void handleVetorial() {
		try {
			textFieldResult.setText(mostrarVetor(vetorService.calcularProdutoVetorial(convertVetor(textFieldvetorA),
					convertVetor(textFieldvetorB))));
		} catch (VetoresTamanhosDiferentesException e) {
			textFieldResult.setText(label.getString("root.tab.vetor.erroTamanDifer"));
		} catch (VetorTamanhoExcedidoException e) {
			textFieldResult.setText(label.getString("root.tab.vetor.erroTamanExced"));
		}
	}

	@FXML
	private void handleProjecao() {
		try {
			textFieldResult.setText(mostrarVetor(
					vetorService.calcularProjecao(convertVetor(textFieldvetorA), convertVetor(textFieldvetorB))));
		} catch (VetoresTamanhosDiferentesException e) {
			textFieldResult.setText(label.getString("root.tab.vetor.erroTamanDifer"));
		}
	}

	@FXML
	private void handleCosseno() {
		try {
			textFieldResult.setText(label.getString("root.tab.vetor.coss") + ": " + ": "
					+ vetorService.calcularCosseno(convertVetor(textFieldvetorA), convertVetor(textFieldvetorB)));
		} catch (VetoresTamanhosDiferentesException e) {
			textFieldResult.setText(label.getString("root.tab.vetor.erroTamanDifer"));
		}
	}

	@FXML
	private void handleModulo(ActionEvent actionEvent) {
		String vetorEmUso = ((Button) actionEvent.getSource()).getId();

		if (vetorEmUso.equals("vA"))
			modulo(convertVetor(textFieldvetorA));
		else if (vetorEmUso.equals("vB"))
			modulo(convertVetor(textFieldvetorB));
	}

	private void modulo(Double[] vetor) {
		textFieldResult.setText(label.getString("root.tab.vetor.modulo") + ": " + vetorService.calcularModulo(vetor));
	}

	@FXML
	private void handleLimparTudo() {
		textFieldvetorA.setText("");
		textFieldvetorB.setText("");
		textFieldResult.setText("");
		textFieldMultiPorA.setText("");
		textFieldMultiPorB.setText("");
		textFieldElevadoPorA.setText("");
		textFieldElevadoPorB.setText("");
	}

	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		vetorStage.close();
		mainApp.clean(vetorStage, this);
	}

	@FXML
	private void handleAjuda() {

	}

	private Double[] convertVetor(TextField textFieldVetor) {
		String[] stringVetor = textFieldVetor.getText().split(",");
		Double[] vetor = new Double[stringVetor.length];

		for (int cont = 0; cont < stringVetor.length; cont++) {
			vetor[cont] = new Double(stringVetor[cont]);
		}

		return vetor;
	}

	private String mostrarVetor(Double[] vetor) {
		String stringVetor = new String();

		for (int cont = 0; cont < vetor.length; cont++) {
			stringVetor += vetor[cont] + ", ";
		}

		return stringVetor;
	}

	public void setLabel(ConfigProperties label) {
		this.label = label;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setVetorStage(Stage vetorStage) {
		this.vetorStage = vetorStage;
	}
}
