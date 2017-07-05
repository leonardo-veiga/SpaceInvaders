package br.com.poo.spaceinvaders.scenes;

import java.io.FileInputStream;

import br.com.poo.spaceinvaders.base.Params;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuScene {
	
	private static MenuScene menuScene = null;
	
	public MenuScene() {
		
	}
	
	public static MenuScene getInstance(){
        if (menuScene == null){
        	menuScene = new MenuScene();
        }
        return(menuScene);
    }
	
	public Scene getMenuScene() {
		Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        
        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
        root.getChildren().add(canvas);
        
        try {        
	        ImageView logoView = new ImageView();
	        Image logo = new Image(new FileInputStream("C:\\Users\\leonardo_veiga\\Pictures\\Other\\Space_invaders_logo.svg.png"));
	        logoView.setFitHeight(Params.WINDOW_HEIGHT * 0.5);
	        logoView.setFitWidth(Params.WINDOW_WIDTH * 0.5);
	        logoView.setLayoutX(Params.WINDOW_WIDTH * 0.25);
	        logoView.setLayoutY(Params.WINDOW_HEIGHT * 0.1);
	        logoView.setImage(logo);
	        root.getChildren().add(logoView);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Button startGame = new Button();
        startGame.setText("Start Game");
        startGame.setLayoutX(Params.WINDOW_WIDTH * 0.45);
        startGame.setLayoutY(Params.WINDOW_HEIGHT * 0.7);
        root.getChildren().add(startGame);
        
        startGame.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent e) {
	    		Stage stage = (Stage) startGame.getScene().getWindow();
	    		Scene gameScene = GameScene.getInstance().getGameScene();
	    		stage.setScene(gameScene);
	    	}
    	});
        
        return scene;
	}
}
