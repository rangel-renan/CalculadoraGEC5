package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.MatrizService;
import com.calculadora.service.MatrizServiceImpl;
import com.calculadora.util.ParseMatriz;
import com.calculadora.util.enums.TipoOperacao;
import com.calculadora.util.excessoes.MatrizesTamanhosDiferentesException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MatrizController implements Runnable {
	private MainApp mainApp;
	private Stage matrizStage;

	private ConfigProperties label;
	private MatrizService matrizService;

	@FXML
	private TextArea textAreaMatrizA;

	@FXML
	private TextArea textAreaMatrizB;
	
	@FXML
	private Label labelResultado;
	
	@FXML
	private TextField textFieldElevadoPorA;
	
	@FXML
	private TextField textFieldElevadoPorB;
	
	@FXML
	private TextField textFieldMultiPorA;

	@FXML
	private TextField textFieldMultiPorB;
	
	@FXML
	private Button btnDeterminanteA;
	
	@FXML
	private Button btnAsomaB;
	
	@FXML
	private Button btnAsubtraiB;
	
	@FXML
	private Button btnAmultiplicaB;
	
	@FXML
	private Button btnTranspostaA;
	
	@FXML
	private Button btnMultiplicaPorA;
	
	@FXML
	private Button btnElevadoPorA;
	
	@FXML
	private Button btnInversaA;
	
	@FXML
	private Button btnTriangularA;
	
	@FXML
	private Button btnAdjuntaA;
	
	@FXML
	private Button btnDeterminanteB;
	
	@FXML
	private Button btnTranspostaB;
	
	@FXML
	private Button btnMultiplicaPorB;
	
	@FXML
	private Button btnElevadoPorB;
	
	@FXML
	private Button btnInversaB;
	
	@FXML
	private Button btnTriangularB;
	
	@FXML
	private Button btnAdjuntaB;
	
	@Override
	public void run() {
		labelResultado.setWrapText(true);
		
		btnAsomaB.setDisable(true);
    	btnAsubtraiB.setDisable(true);
    	btnAmultiplicaB.setDisable(true);
		btnDeterminanteA.setDisable(true);
    	btnTranspostaA.setDisable(true);
    	btnMultiplicaPorA.setDisable(true);
    	btnElevadoPorA.setDisable(true);
    	btnInversaA.setDisable(true);
    	btnTriangularA.setDisable(true);
    	btnAdjuntaA.setDisable(true);
    	btnDeterminanteB.setDisable(true);
    	btnTranspostaB.setDisable(true);
    	btnMultiplicaPorB.setDisable(true);
    	btnElevadoPorB.setDisable(true);
    	btnInversaB.setDisable(true);
    	btnTriangularB.setDisable(true);
    	btnAdjuntaB.setDisable(true);
    	
    	setListerners(textAreaMatrizA);
    	setListerners(textAreaMatrizB);
    	setListerners(textAreaMatrizA, textFieldMultiPorA, btnMultiplicaPorA);
    	setListerners(textAreaMatrizB, textFieldMultiPorB, btnMultiplicaPorB);
    	setListerners(textAreaMatrizA, textFieldElevadoPorA, btnElevadoPorA);
    	setListerners(textAreaMatrizB, textFieldElevadoPorB, btnElevadoPorB);
		setListernersMatrixA(textAreaMatrizA);
		setListernersMatrixB(textAreaMatrizB);
		
		Platform.runLater(new Runnable() {
			public void run() {
				matrizStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						handleVoltar();
					}
				});
			}
		});
	}
	
	public void show(MainApp _mainApp, Stage matrizStage, ConfigProperties label) {
		this.label = label;
		this.mainApp = _mainApp;
		this.matrizStage = matrizStage;
		this.matrizService = new MatrizServiceImpl();
		
		run();
		mainApp.addThread(new Thread(this));
	}
	
	private void setListernersMatrixA(TextArea label) {
		Platform.runLater(new Runnable() {
			public void run() {
				label.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (label.getText().length() == 0) {
				    	btnDeterminanteA.setDisable(true);
				    	btnTranspostaA.setDisable(true);
				    	btnInversaA.setDisable(true);
				    	btnTriangularA.setDisable(true);
				    	btnAdjuntaA.setDisable(true);
				    } else {
				    	btnDeterminanteA.setDisable(false);
				    	btnTranspostaA.setDisable(false);
				    	btnInversaA.setDisable(false);
				    	btnTriangularA.setDisable(false);
				    	btnAdjuntaA.setDisable(false);
				    }
				});
			}
		});
	}
	
	private void setListernersMatrixB(TextArea label) {
		Platform.runLater(new Runnable() {
			public void run() {
				label.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (label.getText().length() == 0) {
				    	btnDeterminanteB.setDisable(true);
				    	btnTranspostaB.setDisable(true);
				    	btnInversaB.setDisable(true);
				    	btnTriangularB.setDisable(true);
				    	btnAdjuntaB.setDisable(true);
				    } else {
				    	btnDeterminanteB.setDisable(false);
				    	btnTranspostaB.setDisable(false);
				    	btnInversaB.setDisable(false);
				    	btnTriangularB.setDisable(false);
				    	btnAdjuntaB.setDisable(false);
				    }
				});
			}
		});
	}
	
	private void setListerners(TextArea textArea) {
		Platform.runLater(new Runnable() {
			public void run() {
				textArea.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textAreaMatrizA.getText().length() == 0
				    	|| textAreaMatrizB.getText().length() == 0) {
				    	btnAsomaB.setDisable(true);
				    	btnAsubtraiB.setDisable(true);
				    	btnAmultiplicaB.setDisable(true);
				    } else {
				    	btnAsomaB.setDisable(false);
				    	btnAsubtraiB.setDisable(false);
				    	btnAmultiplicaB.setDisable(false);
				    }
				});
			}
		});
		
	}
	
	private void setListerners(TextArea textArea, TextField textField, Button btnOperacao) {
		Platform.runLater(new Runnable() {
			public void run() {
				textField.textProperty().addListener((observable, oldValue, newValue) -> {
				    if (textField.getText().length() == 0
				    	|| textArea.getText().length() == 0) {
				    	btnOperacao.setDisable(true);
				    } else {
				    	btnOperacao.setDisable(false);
				    }
				});
			}
		});
		
	}
	
	@FXML
	private void handleOperacao(ActionEvent actionEvent) {
		
		try {
			switch (((Button) actionEvent.getSource()).getText()) {
			case "A + B":
				soma(ParseMatriz.parse(textAreaMatrizA.getText()), ParseMatriz.parse(textAreaMatrizB.getText()));
				break;
			case "A - B":
				subtracao(ParseMatriz.parse(textAreaMatrizA.getText()), ParseMatriz.parse(textAreaMatrizB.getText()));
				break;
			case "A x B":
				multiplicacao(ParseMatriz.parse(textAreaMatrizA.getText()), ParseMatriz.parse(textAreaMatrizB.getText()));
				break;
			}
		} catch (MatrizesTamanhosDiferentesException e) {
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroTamanhoM"));
		} catch (ArrayIndexOutOfBoundsException e) {
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroTamanhoM"));
		}
		
	}
	
	private void soma(Double[][] firstMatriz, Double[][] secondMatriz) throws MatrizesTamanhosDiferentesException {
		labelResultado.setText(label.getString("root.tab.matrizEquacao.soma") + ": \n\n" + label.getString("root.tab.matrizEquacao.primMatriz") + ":\n" 
							+ ParseMatriz.montarMatriz(firstMatriz) + "\n" + label.getString("root.tab.matrizEquacao.segMatriz") 
							+ ":\n" + ParseMatriz.montarMatriz(secondMatriz) + "\n" 
							+ label.getString("root.tab.matrizEquacao.result") +": \n" + 
							ParseMatriz.montarMatriz(matrizService.operacaoMatrizes(firstMatriz, secondMatriz, TipoOperacao.SOMA)));
	}
	
	private void subtracao(Double[][] firstMatriz, Double[][] secondMatriz) throws MatrizesTamanhosDiferentesException {
		labelResultado.setText(label.getString("root.tab.matrizEquacao.subt") + ": \n\n" + label.getString("root.tab.matrizEquacao.primMatriz") + ":\n" 
				+ ParseMatriz.montarMatriz(firstMatriz) + "\n" + label.getString("root.tab.matrizEquacao.segMatriz") 
				+ ":\n" + ParseMatriz.montarMatriz(secondMatriz) + "\n" 
				+ label.getString("root.tab.matrizEquacao.result") +": \n" +  
				ParseMatriz.montarMatriz(matrizService.operacaoMatrizes(firstMatriz, secondMatriz, TipoOperacao.SUBTRACAO)));
	}
	
	private void multiplicacao(Double[][] firstMatriz, Double[][] secondMatriz) throws MatrizesTamanhosDiferentesException {
		labelResultado.setText(label.getString("root.tab.matrizEquacao.mult") + ": \n\n" + label.getString("root.tab.matrizEquacao.primMatriz") + ":\n" 
				+ ParseMatriz.montarMatriz(firstMatriz) + "\n" + label.getString("root.tab.matrizEquacao.segMatriz") 
				+ ":\n" + ParseMatriz.montarMatriz(secondMatriz) + "\n" 
				+ label.getString("root.tab.matrizEquacao.result") +": \n" + 
				ParseMatriz.montarMatriz(matrizService.operacaoMatrizes(firstMatriz, secondMatriz, TipoOperacao.MULTIPLICACAO)));
	}
	
	@FXML
	private void handleDeterminante(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			determinante(ParseMatriz.parse(textAreaMatrizA.getText()));
		 else if (matrizEmUso.equals("mB")) 
			determinante(ParseMatriz.parse(textAreaMatrizB.getText()));
	}

	private void determinante(Double[][] matriz) {
		
		if (matriz != null) 
			labelResultado.setText(ParseMatriz.montarMatriz(matriz) + 
					"\n" + "det(A)" + ": " + matrizService.calcularDeterminante(matriz));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleTransposta(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			trasposta(ParseMatriz.parse(textAreaMatrizA.getText()));
		 else if (matrizEmUso.equals("mB")) 
			 trasposta(ParseMatriz.parse(textAreaMatrizB.getText()));
	}
	
	private void trasposta(Double[][] matriz) {
		if (matriz != null) 
			labelResultado.setText(ParseMatriz.montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.transposta") + ":\n\n" + 
					ParseMatriz.montarMatriz(matrizService.calcularTransposta(matriz)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleElevadoPor(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			elevadoPor(ParseMatriz.parse(textAreaMatrizA.getText()), Double.valueOf(textFieldElevadoPorA.getText()));
		 else if (matrizEmUso.equals("mB")) 
			 elevadoPor(ParseMatriz.parse(textAreaMatrizB.getText()), Double.valueOf(textFieldElevadoPorB.getText()));
	}
	
	private void elevadoPor(Double[][] matriz, Double valor) {
		if (matriz != null) 
			labelResultado.setText(ParseMatriz.montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.elevPor") + ": " + valor + " = \n\n" + 
					ParseMatriz.montarMatriz(matrizService.elevarPor(matriz, valor)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleMultiplicaPor(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			multiplicaPor(ParseMatriz.parse(textAreaMatrizA.getText()), Double.valueOf(textFieldMultiPorA.getText()));
		 else if (matrizEmUso.equals("mB")) 
			 multiplicaPor(ParseMatriz.parse(textAreaMatrizB.getText()), Double.valueOf(textFieldMultiPorB.getText()));
	}
	
	private void multiplicaPor(Double[][] matriz, Double valor) {
		
		if (matriz != null) 
			labelResultado.setText(ParseMatriz.montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.mulPor2") + ": " + valor + " = \n\n" + 
					ParseMatriz.montarMatriz(matrizService.multiplicarPor(matriz, valor)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleInversa(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			inversa(ParseMatriz.parse(textAreaMatrizA.getText()));
		 else if (matrizEmUso.equals("mB")) 
			 inversa(ParseMatriz.parse(textAreaMatrizB.getText()));
	}
	
	private void inversa(Double[][] matriz) {
		if (matriz != null) 
			labelResultado.setText(ParseMatriz.montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.inversa") + ":\n\n" + 
					ParseMatriz.montarMatriz(matrizService.calcularMatrizInversa(matriz)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
		
	}
	
	@FXML
	private void handleTriangular(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			triangular(ParseMatriz.parse(textAreaMatrizA.getText()));
		 else if (matrizEmUso.equals("mB")) 
			triangular(ParseMatriz.parse(textAreaMatrizB.getText()));
	}
	
	private void triangular(Double[][] matriz) {
		if (matriz != null) 
			labelResultado.setText(ParseMatriz.montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.triangular") + ":\n\n" + 
					ParseMatriz.montarMatriz(matrizService.calcularMatrizTriangular(matriz)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleAdjunta(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			adjunta(ParseMatriz.parse(textAreaMatrizA.getText()));
		 else if (matrizEmUso.equals("mB")) 
			adjunta(ParseMatriz.parse(textAreaMatrizB.getText()));
	}
	
	private void adjunta(Double[][] matriz) {
		if (matriz != null) 
			labelResultado.setText(ParseMatriz.montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.adjunta") + ":\n\n" + 
					ParseMatriz.montarMatriz(matrizService.calcularMatrizAdjunta(matriz)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleLimparTudo() {
		textAreaMatrizA.setText("");
		textAreaMatrizB.setText("");
		labelResultado.setText("");
		textFieldElevadoPorA.setText("");
		textFieldElevadoPorB.setText("");
		textFieldMultiPorA.setText("");
		textFieldMultiPorB.setText("");
	}
	
	@FXML
	private void handleVoltar() {
		mainApp.exibirRoot();
		matrizStage.close();
	}

	@FXML
	private void handleAjuda() {
		
	}

	public void setLabel(ConfigProperties label) {
		this.label = label;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setMatrizStage(Stage matrizStage) {
		this.matrizStage = matrizStage;
	}
}
