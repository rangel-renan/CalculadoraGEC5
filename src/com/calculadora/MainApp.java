package com.calculadora;

import java.io.IOException;
import java.util.Optional;

import com.calculadora.config.ConfigProperties;
import com.calculadora.controller.CalculadoraController;
import com.calculadora.controller.RootLayoutController;
import com.calculadora.model.Idioma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private FXMLLoader loader;
	private BorderPane rootLayout;
	
	private Stage calculadoraStage;
	
	private RootLayoutController rootLayoutController;
	private CalculadoraController calculadoraController;
	
	private ConfigProperties label;
	private Idioma idioma;
	
	@Override
	public void start(Stage _calculadoraStage) throws Exception {
		idioma = showEscolherIdioma();
		label = ConfigProperties.getInstance(idioma);
		calculadoraStage = _calculadoraStage;
		
		FXMLLoader carregarFXML = new FXMLLoader();
		carregarFXML.setResources(label.getBundle());
		
		initRootLayout();
		initHome();
	}
	
	private Idioma showEscolherIdioma() {
		ChoiceDialog<Idioma> dialog = new ChoiceDialog<Idioma>(Idioma.Ingles, Idioma.values());
		dialog.setTitle("Escolher Idioma");
		dialog.setHeaderText("Escolher o Idioma Padrão da Aplicação.");
		dialog.setContentText("Escolha o Idioma Desejado: ");

		// Verifica no ComboBox qual Opção o Usuário Escolheu.
		Optional<Idioma> idiomaEscolhido = dialog.showAndWait();
		if (idiomaEscolhido.isPresent()) {
			return idiomaEscolhido.get();
		} else {
			System.exit(0);
		}

		return null;
	}
	
	private FXMLLoader getNovoLoader() {

		FXMLLoader carregarFXML = new FXMLLoader();
		carregarFXML.setResources(label.getBundle());

		return carregarFXML;
	}
	
	private Parent getNewNodo(FXMLLoader loader, String caminhoFXML) {
		try {
			return loader.load(this.getClass().getResource(caminhoFXML).openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void initRootLayout() {

		loader = getNovoLoader();
		rootLayout = (BorderPane) getNewNodo(loader, "controller/RootLayout.fxml");

		rootLayoutController = loader.getController();
		rootLayoutController.setMainApp(this);
		rootLayoutController.setCalculadoraStage(calculadoraStage);
		//rootLayoutController.init();
	}
	
	private void initHome() {
		
		loader = getNovoLoader();
		rootLayout.setBottom(getNewNodo(loader, "controller/Calculadora-Style2.fxml"));
		rootLayout.getStylesheets().add("file:resources/css/style.css");
		
		calculadoraStage.setTitle("Calculadora GEC5");
		calculadoraStage.setResizable(false);
		calculadoraStage.setScene(new Scene(rootLayout));
		calculadoraStage.show();
		
		calculadoraController = loader.getController();
		calculadoraController.setHomeStage(calculadoraStage);
		calculadoraController.setIdioma(idioma);
		calculadoraController.init();
	}
		
	public static void main(String[] args) {
		launch(args);
	}
}
