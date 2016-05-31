package com.calculadora;

import java.io.IOException;

import com.calculadora.config.ConfigProperties;
import com.calculadora.controller.CalculadoraController;
import com.calculadora.controller.EscolherIdiomaController;
import com.calculadora.util.Idioma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	public static final String CAMINHO_ICONE_APLICACAO = "file:resources/images/img-calc.png";

	private ConfigProperties label;
	private Idioma idioma;
	
	private Stage primaryStage;
	private Stage rootStage;

	private AnchorPane escolherIdiomaLayout;
	private BorderPane rootLayout;
	
	private CalculadoraController calculadoraController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Escolher Idioma");

		initEscolherIdioma();
	}

	public Object getLayout(FXMLLoader loader, String pathFXML) {

		try {
			loader.setLocation(MainApp.class.getResource(pathFXML));
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Stage getStage(Parent pageLayout, String nomeAplicacao, String icone) {
		
		Stage tempStage = new Stage();
		tempStage.initModality(Modality.WINDOW_MODAL);
		tempStage.setResizable(false);
		tempStage.setTitle(nomeAplicacao);
		tempStage.getIcons().add(new Image(icone));
		tempStage.setScene(new Scene(pageLayout));
		
		return tempStage;
	}

	public void initEscolherIdioma() {

		FXMLLoader loader = new FXMLLoader();
		escolherIdiomaLayout = (AnchorPane) getLayout(loader, "controller/EscolherIdioma.fxml");

		Scene scene = new Scene(escolherIdiomaLayout);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(CAMINHO_ICONE_APLICACAO));
		primaryStage.show();

		EscolherIdiomaController escolherIdiomaController = loader.getController();
		escolherIdiomaController.setEscolherIdiomaStage(primaryStage);
		escolherIdiomaController.setMainApp(this);
		escolherIdiomaController.show();
	}
	
	public void initRoot(Idioma _idioma) {
		this.idioma = _idioma;
		label = ConfigProperties.getInstance(idioma);
		
		FXMLLoader loader = new FXMLLoader();
		rootLayout = (BorderPane) getLayout(loader, "controller/RootLayout.fxml");
		
		rootStage = getStage(rootLayout, label.getString("root.titulo"), CAMINHO_ICONE_APLICACAO);
		rootStage.show();
		
		FXMLLoader loaderCalculadora = new FXMLLoader();
		AnchorPane calculadoraLayout = (AnchorPane) getLayout(loaderCalculadora, "controller/Calculadora.fxml");
		
		rootLayout.setBottom(calculadoraLayout);
		
		calculadoraController = loaderCalculadora.getController();
		calculadoraController.setIdioma(idioma);
		calculadoraController.setRootLayout(calculadoraLayout);
		calculadoraController.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
