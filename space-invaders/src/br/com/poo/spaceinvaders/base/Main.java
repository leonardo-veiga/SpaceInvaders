package br.com.poo.spaceinvaders.base;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Handles window initialization and primary game setup
 * @author Bernardo Copstein, Rafael Copstein
 */

public class Main extends Application {
	
    @Override
    public void start(Stage stage) throws Exception {
        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
 
        Group rootGame = new Group();
        Scene sceneGame = new Scene(rootGame);
        
        Group rootMenu = new Group();
        Scene sceneMenu = new Scene(rootMenu);
        stage.setScene(sceneMenu);
        

        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
        rootMenu.getChildren().add(canvas);
        rootGame.getChildren().add(canvas);
        
        Button startGame = new Button();
        startGame.setText("Start Game");
        rootMenu.getChildren().add(startGame);
        
//        Text tEat = new Text();
//        tEat.setText("GG! " + Game.getInstance().getTotalPoints());
//        rootGame.getChildren().add(tEat);
        
        Label label = new Label();
        label.textProperty().bind(Bindings.createStringBinding(() -> ("Score: " + Game.getInstance().getTotalPoints().get()), Game.getInstance().getTotalPoints()));
        rootGame.getChildren().add(label);
        
//        Image image = new Image(getClass().getResourceAsStream("labels.jpg"));
        
        startGame.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent e) {
	    		startGame(sceneGame, canvas, stage);
	    	}
    	});

        // Show window
        stage.show();
    }    
    
    private void startGame(Scene scene, Canvas canvas, Stage stage) {
    	// Setup Game object
        Game.getInstance().Start();
        
        stage.setScene( scene );
        
        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });
        
        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });
        
        // Register Game Loop       
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastNanoTime = System.nanoTime();
            
            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;
                
                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                Game.getInstance().Draw(gc);
                
                lastNanoTime = currentNanoTime;
            }
            
        }.start();

    }
    
    public static void main(String args[]) {
        launch();
    }
}