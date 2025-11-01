/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vedika.lab7part2;


import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
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
    
    
    public Image[] images = new Image[119]; // space for 119 images
    public int currentIndex = 0;            // which image is showing
    public Timeline timeline;               // used for automatic cycling
    public double interval = 2.0;           // seconds between images
    public PauseTransition slideshow;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      launch(args);
    }

    @Override
    public void start(Stage stage){
        // inside start(Stage primaryStage) at the top
       for (int i = 0; i < 119; i++) {
       int number = 101 + i; // filenames 101.png .. 219.png
       images[i] = new Image("file:./src/images/" + number + ".png");
       }
     

       // still inside start()
        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setImage(images[currentIndex]); // show first image (index 0)
 
        // put imageView inside a VBox for center region
        VBox middle = new VBox();
        middle.setStyle("-fx-alignment: center; -fx-padding: 10;");
        middle.getChildren().add(imageView);
        
        // 3) Top and bottom labels + BorderPane
        Label lblTop = new Label("Random Game");
        Label lblBottom = new Label("Waiting...");

        BorderPane root = new BorderPane();
        root.setTop(lblTop);
        root.setCenter(middle);
        // left/right empty
        root.setLeft(new Label(""));
        root.setRight(new Label(""));

        
          // 4) Buttons and controls
        Button btnPlayPause = new Button("Play");
        Button btnSpeedUp = new Button("Speed+");
        Button btnSpeedDown = new Button("Speed-");

        HBox controls = new HBox(10, btnPlayPause, btnSpeedUp, btnSpeedDown);
        controls.setStyle("-fx-alignment: center; -fx-padding: 8;");

        VBox bottomBox = new VBox(5, lblBottom, controls);
        bottomBox.setStyle("-fx-alignment: center;");
        root.setBottom(bottomBox);
        
        // Slideshow logic (PauseTransition)
        slideshow = new PauseTransition(Duration.seconds(interval));
        slideshow.setOnFinished(e -> {
            currentIndex++;
            if (currentIndex >= images.length) {
                currentIndex = 0;
            }
            imageView.setImage(images[currentIndex]);
            slideshow.play(); // repeat
        });
       
        Scene scene = new Scene(root, 350, 420);
        stage.setTitle("Lab07 - Image Slideshow");
        stage.setScene(scene);
        stage.show();

}
    //add method to show the next image
    public void showNextImage(ImageView imageView) {
    currentIndex++; 

    if (currentIndex >= images.length) {
        currentIndex = 0; // go back to first image to make a cycle
    }

    imageView.setImage(images[currentIndex]);
}

}

    
    

