package com.calculadora.config;

import com.calculadora.MainApp;
import com.calculadora.model.Idioma;

import javafx.stage.Stage;

public class RootLayoutController {
	
	private MainApp mainApp;
	private Stage rootLayout;
	
	private ConfigProperties label;
	private Idioma idioma;
	
	public RootLayoutController() {
		label = ConfigProperties.getInstance(Idioma.Portugues);
	}
	
	public void initRootLayout() {
		
	}
	
	public void setRootLayout(Stage rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
