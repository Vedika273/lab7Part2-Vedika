/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vedika.lab7part2;

//https://github.com/Vedika273/lab7Part2-Vedika

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Vedika
 */
public class VedikaLab7part2 extends Application {
    
    public Image[] images = new Image[20]; // space for 20 images
    public int currentIndex = 0;            // which image is showing
    public double interval = 1.5;           // seconds between images
    public PauseTransition slideshow;

    
    public static void main(String[] args) {
      launch(args);
    }

    @Override
    public void start(Stage stage){
        
        //load images 
        for (int i = 0; i < images.length ; i++) {
        int number = 101 + i;  // filenames 101.png .. 120.png
        images[i] = new Image("file:src/vedika/images/" + number + ".jpg");
        }
        
       //image view setup
        ImageView imageView = new ImageView();
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setImage(images[currentIndex]); // show first image (index 0)
 
        // put imageView inside a VBox for center region
        VBox middleBox = new VBox(imageView);
        middleBox.setAlignment(Pos.CENTER);
        middleBox.setPadding(new Insets(10));
        
        // 3) Top and bottom labels 
        Label labelTop = new Label("Random Game");
        labelTop.setStyle("-fx-font-size: 18px;");
        BorderPane.setAlignment(labelTop, Pos.CENTER);

        Label labelBottom = new Label("Waiting...");

         // Buttons 
        Button btnPlayPause = new Button("Play");
        Button btnSpeedUp = new Button("Speed+");
        Button btnSpeedDown = new Button("Speed-");

        HBox controls = new HBox(10, btnPlayPause, btnSpeedUp, btnSpeedDown);
        controls.setAlignment(Pos.CENTER);
        controls.setStyle("-fx-alignment: center; -fx-padding: 8;");
        controls.setPadding(new Insets(8));
        
        VBox bottomBox = new VBox(5, labelBottom, controls);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setStyle("-fx-alignment: center;");
       
        //Borderpane layout
        BorderPane root = new BorderPane();
        root.setTop(labelTop);
        root.setCenter(middleBox);
        root.setBottom(bottomBox);
        // left/right empty
        root.setLeft(new Label(""));
        root.setRight(new Label(""));

        // Slideshow (PauseTransition)
        slideshow = new PauseTransition(Duration.seconds(interval));
        slideshow.setOnFinished(e -> { //when the pause finshes 
            showNextImage(imageView);
            slideshow.play(); 
        });
        
        // Play / Pause button
        btnPlayPause.setOnAction(e -> {
        if (btnPlayPause.getText().equals("Play")) {
           btnPlayPause.setText("Pause"); //change the button to "pause"
           labelBottom.setText("Playing...");
           slideshow.play();
          } else {
           btnPlayPause.setText("Play"); //change the button to "play"
           labelBottom.setText("Paused");
           slideshow.stop();
         }
        });
        
        // Speed+ button
        btnSpeedUp.setOnAction(e -> {
            if (interval > 0.5) { // minimum speed
                interval -= 0.5;
                slideshow.setDuration(Duration.seconds(interval));
                labelBottom.setText("Interval between the images: " + interval + "s");
            }
        });
        
        // Speed- button
        btnSpeedDown.setOnAction(e -> {
            interval += 0.5;
            slideshow.setDuration(Duration.seconds(interval));
            labelBottom.setText("Interval between the images: " + interval + "s");
        });

        Scene scene = new Scene(root, 350, 420);
        stage.setTitle("Lab07 - Image Slideshow");
        stage.setScene(scene);
        stage.show();
    }
   
        public void showNextImage(ImageView imageView) {
        currentIndex++; 
        if (currentIndex >= images.length) { //if the end of the array is reached then...
            currentIndex = 0; // go back to first image to make a cycle
        }
        imageView.setImage(images[currentIndex]);
      }
    }