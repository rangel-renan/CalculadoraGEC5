package com.calculadora.controller;

import java.math.BigDecimal;

import com.calculadora.service.OperacoesBasicasService;
import com.calculadora.service.OperacoesBasicasServiceImpl;
import com.calculadora.util.Idioma;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class CalculadoraController {
	private String operador = "";
	private OperacoesBasicasService operacoesBasicasService;

	private AnchorPane rootLayout;
	private Idioma idioma;
	private BigDecimal currentNumber;

	@FXML
	private TextField displayField;

	@FXML
	private Label displayAnteriorField;

	@FXML
	private void initialize() {
	}

	public void show() {
		
		operacoesBasicasService = new OperacoesBasicasServiceImpl();
		rootLayout.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {

				switch (keyEvent.getCode()) {
				case DIGIT1:
				case NUMPAD1:
					handleNumPad("1");
					break;
				case DIGIT2:
				case NUMPAD2:
					handleNumPad("2");
					break;
				case DIGIT3:
				case NUMPAD3:
					handleNumPad("3");
					break;
				case DIGIT4:
				case NUMPAD4:
					handleNumPad("4");
					break;
				case DIGIT5:
				case NUMPAD5:
					handleNumPad("5");
					break;
				case DIGIT6:
				case NUMPAD6:
					handleNumPad("6");
					break;
				case DIGIT7:
				case NUMPAD7:
					handleNumPad("7");
					break;
				case DIGIT8:
				case NUMPAD8:
					handleNumPad("8");
					break;
				case DIGIT9:
				case NUMPAD9:
					handleNumPad("9");
					break;
				case DIGIT0:
				case NUMPAD0:
					handleNumPad("0");
					break;
				case SUBTRACT:
					handleOperador("-");
					break;
				case ADD:
					handleOperador("+");
					break;
				case DIVIDE:
					handleOperador("/");
					break;
				case MULTIPLY:
					handleOperador("*");
					break;
				case ENTER:
					handleIgual();
					break;
				case DELETE:
					apagarTudo();
					break;
				case BACK_SPACE:
					apagarUltimaLetra();
					break;
				default:
					break;
				}
			}
		});

	}

	@FXML
	public void apagarTudo() {
		displayField.setText("");
		displayAnteriorField.setText("");
	}

	@FXML
	public void apagarUltimaLetra() {
		displayField.setText(displayField.getText().substring(0, displayField.getText().length() - 1));
		displayAnteriorField.setText(displayField.getText().substring(0, displayField.getText().length() - 1));
	}

	@FXML
	private void handleIgual() {
		
		if (displayField.getText() != null) {
			String resultado = calcular(currentNumber, new BigDecimal(displayField.getText()), operador).toString();
			displayField.setText(resultado);
			displayAnteriorField.setText(resultado);
		}
	}

	@FXML
	private void handleOperador(ActionEvent actionEvent) {
		handleOperador(((Button) actionEvent.getSource()).getText());
	}

	private void handleOperador(String operacaoAtual) {
		String currentText = displayField.getText();

		if (!currentText.isEmpty()) {
			currentNumber = new BigDecimal(currentText);
			displayField.setText("");
			operador = operacaoAtual;
		}

		displayAnteriorField.setText(currentText + " " + operador + " ");
	}

	@FXML
	private void handleNumPad(ActionEvent actionEvent) {
		handleNumPad(((Button) actionEvent.getSource()).getText());
	}

	private void handleNumPad(String valorAtual) {
		String oldValor = displayField.getText();
		String novoValor = null;
		novoValor = oldValor + valorAtual;
		displayField.setText(novoValor);
		displayField.setText(novoValor);
	}

	public BigDecimal calcular(BigDecimal valor1, BigDecimal valor2, String operador) {
		return operacoesBasicasService.calcular(valor1, valor2, operador);
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public void setRootLayout(AnchorPane rootLayout) {
		this.rootLayout = rootLayout;
	}
}
