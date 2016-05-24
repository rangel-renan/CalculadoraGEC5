package com.calculadora.controller;

import org.jsoup.Jsoup;

import com.calculadora.model.Idioma;
import com.calculadora.service.OperacoesBasicasService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HomeController {

	@FXML
	private Button tecla1;
	@FXML
	private Button tecla2;
	@FXML
	private Button tecla3;
	@FXML
	private Button tecla4;
	@FXML
	private Button tecla5;
	@FXML
	private Button tecla6;
	@FXML
	private Button tecla7;
	@FXML
	private Button tecla8;
	@FXML
	private Button tecla9;
	@FXML
	private Button tecla0;
	@FXML
	private Button teclaVirgula;
	@FXML
	private Button teclaSomar;
	@FXML
	private Button teclaSubtrair;
	@FXML
	private Button teclaMultiplicar;
	@FXML
	private Button teclaDividir;
	
	@FXML
	private Button asin;
	
	private String operador = "";
	private Long numeroAux = 0L;
	
	private OperacoesBasicasService operacoesBasicasService;
	
	private Stage homeStage;
	private Idioma idioma;
	private boolean start;

	@FXML
	private TextField inputText;
	
	@FXML
	private Label inputTextAnterior;

	@FXML
	private void initialize() {
	}
	
	public void init() {
		
		homeStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent keyEvent) {
				
				switch (keyEvent.getCode()) {
	                case DIGIT1: case NUMPAD1:
	                	exibirTextoInputText("1");
	                    break ;
	                case DIGIT2: case NUMPAD2:
	                	exibirTextoInputText("2");
	                    break ;
	                case DIGIT3:  case NUMPAD3:
	                	exibirTextoInputText("3");
	                    break ;
	                case DIGIT4:  case NUMPAD4:
	                	exibirTextoInputText("4");
	                    break ;
	                case DIGIT5:  case NUMPAD5:
	                	exibirTextoInputText("5");
	                    break ;
	                case DIGIT6:  case NUMPAD6:
	                	exibirTextoInputText("6");
	                    break ;
	                case DIGIT7:  case NUMPAD7:
	                	exibirTextoInputText("7");
	                    break ;
	                case DIGIT8:  case NUMPAD8:
	                	exibirTextoInputText("8");
	                    break ;
	                case DIGIT9:  case NUMPAD9:
	                	exibirTextoInputText("9");
	                    break ;
	                case DIGIT0:  case NUMPAD0:
	                	exibirTextoInputText("0");
	                    break ;
	                case SUBTRACT:
	                	processarOperador("-");
	                	break;
	                case ADD:
	                	processarOperador("+");
	                	break;
	                case DIVIDE:
	                	processarOperador("/");
	                	break;
	                case MULTIPLY:
	                	processarOperador("*");
	                	break;
	                case ENTER:
	                	processarOperador("=");
	                	break;
	                case DELETE: 
	                	inputText.setText("");
	                	break;
	                case BACK_SPACE:
	                	apagarUltimaLetra();
	                	break;
					default:
						break;
	            }
			}
		});
		asin.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("This is an alert!");
            WebView webView = new WebView();
            webView.getEngine().loadContent("<html>Pay attention, there are sin<sup>-1</sup> tags, here.</html>");
            webView.setPrefSize(150, 60);
            alert.getDialogPane().setContent(webView);;
            alert.showAndWait();
        });
	}
	
	public String html2text(String html) {
	    return Jsoup.parse(html).text();
	}
	
	@FXML
	public void apagarTudo() {
		inputText.setText("");
		inputTextAnterior.setText("");
	}

	@FXML
	public void apagarUltimaLetra() {
		inputText.setText(inputText.getText().substring(0, inputText.getText().length() - 1));
		inputTextAnterior.setText(inputText.getText().substring(0, inputText.getText().length() - 1));
	}
	
	public void getNumPad(ActionEvent actionEvent) {
		exibirTextoInputText(((Button) actionEvent.getSource()).getText());
	}
	
	private void exibirTextoInputText(String value) {
		if (start) {
			inputText.setText("");
			start = false;
		}
		
		inputText.setText(inputText.getText() + value);
		inputTextAnterior.setText(inputTextAnterior.getText() + value);
	}
	
	public void getOperador(ActionEvent actionEvent) {
		processarOperador(((Button) actionEvent.getSource()).getText());
	}
	
	private void processarOperador(String value) {
		
		if (value.equals("=")) {
			if (!operador.isEmpty()) {
				inputText.setText(String.valueOf(calcular(numeroAux, Long.parseLong(inputText.getText()), operador)));
				inputTextAnterior.setText(inputTextAnterior.getText() + " ");
				operador = "";
				start = true;
			} else {
				return;
			}
		} else {
			operador = "";
			if (operador.isEmpty()) {
				operador = value;
				numeroAux = Long.parseLong(inputText.getText());
				inputTextAnterior.setText(inputTextAnterior.getText() + " " + value + " ");
				inputText.setText("");
			} else {
				return;
			}
		}
	}
	
	public Long calcular(Long int1, Long int2, String operador) {
		
		switch (operador) {
			case "+":
				return int1 + int2;
			case "*":
				return int1 * int2;
			case "-":
				return int1 - int2;
			case "/":
				return int1 / int2;
		}
		
		return null;
	}
	
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public void setHomeStage(Stage homeStage) {
		this.homeStage = homeStage;
	}
}
