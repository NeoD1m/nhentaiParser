package main;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class visual extends Application {

    StackPane root;
    GridPane hb;
    Stage primaryStageGlobal;
    static String pathLocalFolder = "";
    static String pathMangaNumber = "";

    public static void main(String[] args, String path, String pathLastMangaNumber) {
        pathLocalFolder = path;
        pathMangaNumber = pathLastMangaNumber;
        launch(args);
    }

    public static void updateText(){

    }

    @Override
    public void start(Stage primaryStage) {

        primaryStageGlobal = primaryStage;
        primaryStage.setTitle("nHentai Parser");
        Button downloadByNumberButton = new Button();
        downloadByNumberButton.setText("Download");
        Label label1 = new Label("Manga number:");
        Label label2 = new Label("Status: ");
        TextField textField = new TextField();
         //textField.setAlignment();

        downloadByNumberButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Thread newThread = new Thread(() -> {
                    //int lol =
                    try {
                        WebParser.parse( Integer.parseInt(textField.getText()),pathLocalFolder,pathMangaNumber);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                newThread.start();

                //System.out.println(textField.getText());
            }
        });

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });



        label1.setMaxWidth(Double.MAX_VALUE);
        //GridPane.setHgrow(label1, Priority.ALWAYS);



        //kek(label1);

        hb = new GridPane();
        //hb.getChildren().addAll(label1, textField,downloadByNumberButton);

        hb.add(label1,0,0);
        //label1.setAlignment(Pos.CENTER_LEFT);
        //hb.setAlignment(Pos.CENTER_LEFT);
        hb.add(textField,1,0);
        hb.add(downloadByNumberButton,2,0);
        hb.add(label2,1,1);

        root = new StackPane();
        root.getChildren().add(hb);

        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

}
/*    @FXML
    public void add(){

        //Label label2 = new Label("fdsafasfdasfasfdasfsafasdfas");
        hb.getChildren().add(new Button("Click me!"));
        //primaryStageGlobal.setScene(new Label("fdsafasfdasfasfdasfsafasdfas"));

    }

    public void setText(String text){

    }*/