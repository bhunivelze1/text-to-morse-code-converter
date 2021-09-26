package view;

import java.util.LinkedList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import model.MorseCodeGenerator;

//TODO: Add playback sound for each morse code element
//TODO: Improve UI
//TODO: Change to a better morse code font
public class TextToMorseConverterApp extends Application {
    
    TextArea inputArea = new TextArea();
    TextArea outputArea = new TextArea();
    
//    Path shortSound = Paths.get();
//    Path longSound = Paths.get();
//    Path pauseSound = Paths.get();
    
    @Override
    public void start(Stage primaryStage) {
        
        inputArea.setPrefSize(200, 100);
        outputArea.setPrefSize(300, 200);
        outputArea.setStyle("-fx-background-color: white; -fx-font-size: 30");
        outputArea.setWrapText(true);
        outputArea.setEditable(false);
        Button convertButton = new Button("Convert");
        convertButton.setPrefSize(100, 100);
        convertButton.setOnAction(e -> convertTextToMorse());
        Button playButton = new Button("Play");
        playButton.setPrefSize(100, 100);
        playButton.setOnAction(e -> playMorseCode());
        
        
        VBox root = new VBox(new HBox(inputArea, convertButton, playButton), outputArea);
                
        primaryStage.setTitle("Text to Morse Converter");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void convertTextToMorse() {
        String initialText = inputArea.getText().toLowerCase();
        String finishedText = initialText.replaceAll(" ", "");
        
        outputArea.clear();
        for (char s : finishedText.toCharArray()) {
            if (outputArea.getText().equals("")) {
                outputArea.setText(MorseCodeGenerator.toMorse(s) + "  ");
            } else {
                outputArea.setText(outputArea.getText() + MorseCodeGenerator.toMorse(s) + "  ");
            }
        }
        
        inputArea.clear();
    }
    
    public void playMorseCode() {
        char[] morseCode = outputArea.getText().toCharArray();
        LinkedList<AudioClip> playbackQueue = new LinkedList<>();
        
        for (char s : morseCode) {
            switch (s) {
                case '.':
//                    playbackQueue.add(new AudioClip(shortSound.toString()));
                    System.out.print("short-");
                    break;
                case '-':
//                    playbackQueue.add(new AudioClip(longSound.toString()));
                    System.out.print("long-");
                    break;
                case ' ':
//                    playbackQueue.add(new AudioClip(pauseSound.toString()));
                    System.out.print(" ");
                    break;
            }
        }
        
    }
    
}