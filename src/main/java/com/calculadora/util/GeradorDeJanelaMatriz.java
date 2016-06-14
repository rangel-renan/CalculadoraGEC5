package com.calculadora.util;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

@SuppressWarnings("static-access")
public class GeradorDeJanelaMatriz {
	
	public static Object gerar(int largura, int altura) {
		GridPane root = new GridPane();    

        for(int y = 0; y < altura; y++){
            for(int x = 0; x < largura; x++){

                TextField tf = new TextField();
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);

                root.setRowIndex(tf,y);
                root.setColumnIndex(tf,x);    
                root.getChildren().add(tf);
            }
        }
        
        root.setAlignment(Pos.CENTER);
        
		return root;
	}
}
