package com.calculadora.controller;

import com.calculadora.MainApp;
import com.calculadora.config.ConfigProperties;
import com.calculadora.service.MatrizService;
import com.calculadora.service.MatrizServiceImpl;
import com.calculadora.util.MatrizesTamanhosDiferentesException;
import com.calculadora.util.TipoOperacao;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MatrizController {
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
	
	public void show(MainApp mainApp, Stage matrizStage, ConfigProperties label) {
		this.label = label;
		this.mainApp = mainApp;
		this.matrizStage = matrizStage;
		this.matrizService = new MatrizServiceImpl();
		
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
		
		this.matrizStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleVoltar();
			}
		});
	}
	
	private void setListernersMatrixA(TextArea label) {
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
	
	private void setListernersMatrixB(TextArea label) {
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
	
	private void setListerners(TextArea textArea) {
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
	
	private void setListerners(TextArea textArea, TextField textField, Button btnOperacao) {
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (textField.getText().length() == 0
		    	|| textArea.getText().length() == 0) {
		    	btnOperacao.setDisable(true);
		    } else {
		    	btnOperacao.setDisable(false);
		    }
		});
	}
	
	@FXML
	private void handleOperacao(ActionEvent actionEvent) {
		
		try {
			switch (((Button) actionEvent.getSource()).getText()) {
			case "A + B":
				soma(convertMatriz(textAreaMatrizA), convertMatriz(textAreaMatrizB));
				break;
			case "A - B":
				subtracao(convertMatriz(textAreaMatrizA), convertMatriz(textAreaMatrizB));
				break;
			case "A x B":
				multiplicacao(convertMatriz(textAreaMatrizA), convertMatriz(textAreaMatrizB));
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
							+ montarMatriz(firstMatriz) + "\n" + label.getString("root.tab.matrizEquacao.segMatriz") 
							+ ":\n" + montarMatriz(secondMatriz) + "\n" 
							+ label.getString("root.tab.matrizEquacao.result") +": \n" + 
							montarMatriz(matrizService.operacaoMatrizes(firstMatriz, secondMatriz, TipoOperacao.SOMA)));
	}
	
	private void subtracao(Double[][] firstMatriz, Double[][] secondMatriz) throws MatrizesTamanhosDiferentesException {
		labelResultado.setText(label.getString("root.tab.matrizEquacao.subt") + ": \n\n" + label.getString("root.tab.matrizEquacao.primMatriz") + ":\n" 
				+ montarMatriz(firstMatriz) + "\n" + label.getString("root.tab.matrizEquacao.segMatriz") 
				+ ":\n" + montarMatriz(secondMatriz) + "\n" 
				+ label.getString("root.tab.matrizEquacao.result") +": \n" +  
				montarMatriz(matrizService.operacaoMatrizes(firstMatriz, secondMatriz, TipoOperacao.SUBTRACAO)));
	}
	
	private void multiplicacao(Double[][] firstMatriz, Double[][] secondMatriz) throws MatrizesTamanhosDiferentesException {
		labelResultado.setText(label.getString("root.tab.matrizEquacao.mult") + ": \n\n" + label.getString("root.tab.matrizEquacao.primMatriz") + ":\n" 
				+ montarMatriz(firstMatriz) + "\n" + label.getString("root.tab.matrizEquacao.segMatriz") 
				+ ":\n" + montarMatriz(secondMatriz) + "\n" 
				+ label.getString("root.tab.matrizEquacao.result") +": \n" + 
				montarMatriz(matrizService.operacaoMatrizes(firstMatriz, secondMatriz, TipoOperacao.MULTIPLICACAO)));
	}
	
	@FXML
	private void handleDeterminante(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			determinante(convertMatriz(textAreaMatrizA));
		 else if (matrizEmUso.equals("mB")) 
			determinante(convertMatriz(textAreaMatrizB));
	}

	private void determinante(Double[][] matriz) {
		
		if (matriz != null) 
			labelResultado.setText(montarMatriz(matriz) + 
					"\n" + "det(A)" + ": " + matrizService.calcularDeterminante(matriz));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleTransposta(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			trasposta(convertMatriz(textAreaMatrizA));
		 else if (matrizEmUso.equals("mB")) 
			 trasposta(convertMatriz(textAreaMatrizB));
	}
	
	private void trasposta(Double[][] matriz) {
		if (matriz != null) 
			labelResultado.setText(montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.transposta") + ":\n\n" + 
					montarMatriz(matrizService.calcularTransposta(matriz)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleElevadoPor(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			elevadoPor(convertMatriz(textAreaMatrizA), Double.valueOf(textFieldElevadoPorA.getText()));
		 else if (matrizEmUso.equals("mB")) 
			 elevadoPor(convertMatriz(textAreaMatrizB), Double.valueOf(textFieldElevadoPorB.getText()));
	}
	
	private void elevadoPor(Double[][] matriz, Double valor) {
		if (matriz != null) 
			labelResultado.setText(montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.elevPor") + ": " + valor + " = \n\n" + 
					montarMatriz(matrizService.elevarPor(matriz, valor)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleMultiplicaPor(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			multiplicaPor(convertMatriz(textAreaMatrizA), Double.valueOf(textFieldMultiPorA.getText()));
		 else if (matrizEmUso.equals("mB")) 
			 multiplicaPor(convertMatriz(textAreaMatrizB), Double.valueOf(textFieldMultiPorB.getText()));
	}
	
	private void multiplicaPor(Double[][] matriz, Double valor) {
		
		if (matriz != null) 
			labelResultado.setText(montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.mulPor2") + ": " + valor + " = \n\n" + 
					montarMatriz(matrizService.multiplicarPor(matriz, valor)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleInversa(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			inversa(convertMatriz(textAreaMatrizA));
		 else if (matrizEmUso.equals("mB")) 
			 inversa(convertMatriz(textAreaMatrizB));
	}
	
	private void inversa(Double[][] matriz) {
		if (matriz != null) 
			labelResultado.setText(montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.inversa") + ":\n\n" + 
					montarMatriz(matrizService.calcularMatrizInversa(matriz)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
		
	}
	
	@FXML
	private void handleTriangular(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			triangular(convertMatriz(textAreaMatrizA));
		 else if (matrizEmUso.equals("mB")) 
			triangular(convertMatriz(textAreaMatrizB));
	}
	
	private void triangular(Double[][] matriz) {
		if (matriz != null) 
			labelResultado.setText(montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.triangular") + ":\n\n" + 
					montarMatriz(matrizService.calcularMatrizTriangular(matriz)));
		 else 
			labelResultado.setText(label.getString("root.tab.matrizEquacao.erroNumero"));
	}
	
	@FXML
	private void handleAdjunta(ActionEvent actionEvent) {
		String matrizEmUso = ((Button) actionEvent.getSource()).getId();
		
		if (matrizEmUso.equals("mA")) 
			adjunta(convertMatriz(textAreaMatrizA));
		 else if (matrizEmUso.equals("mB")) 
			adjunta(convertMatriz(textAreaMatrizB));
	}
	
	private void adjunta(Double[][] matriz) {
		if (matriz != null) 
			labelResultado.setText(montarMatriz(matriz) + 
					"\n" + label.getString("root.tab.matrizEquacao.adjunta") + ":\n\n" + 
					montarMatriz(matrizService.calcularMatrizAdjunta(matriz)));
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
		mainApp.initRoot();
		matrizStage.close();
	}

	@FXML
	private void handleAjuda() {
		
	}

	private Double[][] convertMatriz(TextArea matriz) {
		Double[][] matrizDouble = null;
		
		try {
			String[] linhas = matriz.getText().split("\n");
			String[][] colunas = new String[linhas.length][];
			
			for (int l = 0; l < linhas.length; l++)
				colunas[l] = linhas[l].split(",");

			matrizDouble = new Double[linhas.length][colunas[0].length];

			for (int l = 0; l < linhas.length; l++)
				for (int c = 0; c < colunas[0].length; c++)
					matrizDouble[l][c] = new Double(colunas[l][c]);
		} catch (NumberFormatException e) {
			System.out.println("Numero em Formato Inválido.");
			return null;
		}
		
		return matrizDouble;
	}

	private String montarMatriz(Double[][] matriz) {
		String matrizMontada = new String();

		for (int i = 0; i < matriz.length; i++) {
			matrizMontada += "|  ";
			for (int j = 0; j < matriz[0].length; j++) 
				matrizMontada += matriz[i][j] + "  ";
			matrizMontada += "|\n";
		}

		return matrizMontada;
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
