package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ChoiceBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private Scene scene; //scena
    private GridPane grid; // wzór
    private Label label1 = new Label("Source file: "); // etykieta
    private Label label2 = new Label("Destination: "); // etykieta
    private Label label3 = new Label("Choose cipher: "); // etykieta
    Button button1 = new Button("Select"); // przycisk
    Button button2 = new Button("Select"); // przycisk
    Button button3 = new Button("Run and let miracles happen"); // przycisk
    static int i;
    RadioButton radioButton1 = new RadioButton("Encode");
    RadioButton radioButton2 = new RadioButton("Decrypt");
    private ChoiceBox<Object> choiceBox;
    TextField tf1, tf2;
    TextArea tf3;
    FileChooser fileChooser1 = new FileChooser();
    FileChooser fileChooser2 = new FileChooser();
    Menu m = new Menu("Menu");
    MenuItem m1 = new MenuItem("Clear"); // podpunkt w menu
    MenuItem m2 = new MenuItem("Exit"); // podpunkt w menu
    SeparatorMenuItem separator = new SeparatorMenuItem();
    Menu mn = new Menu("Edit");
    Menu mn1 = new Menu("Langue");
    MenuItem mn11 = new MenuItem("English");
    MenuItem mn22 = new MenuItem("Español");
    MenuItem mn33 = new MenuItem("Русский");
    MenuItem mn44 = new MenuItem("汉语中文");
    MenuItem mn55 = new MenuItem("대한민국");
    private String method;
    private String cipherMethod;
    private RadioButton decode;
    private TextArea textArea;
    private Button textSpace;
    private final Text notification = new Text();
    Text text1 = new Text();


    //================== Uruchamianie programu =================================

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Marcin Laskowski");
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 28));
        text1.setText("SDA Enigma Machine");

        // określenie dostępnych jeżyków po angielsku
        mn22.setAccelerator(KeyCombination.keyCombination("(Spanish)"));
        mn33.setAccelerator(KeyCombination.keyCombination("(Russian)"));
        mn44.setAccelerator(KeyCombination.keyCombination("(Chinese)"));
        mn55.setAccelerator(KeyCombination.keyCombination("(Korean)"));

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tf2.setText(fileChooser1.getInitialFileName());
                button1.setOnAction(f -> {
                    File selectedFile = fileChooser1.showOpenDialog(primaryStage);
                });
            };
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tf2.setText(fileChooser2.getInitialFileName());
                button2.setOnAction(f -> {
                    File selectedFile = fileChooser2.showOpenDialog(primaryStage);
                });
            };
        });

        // podpowiedzi co można wpisać w dane pole
        tf1 = new TextField();
        tf1.setPromptText("Reading");
        tf2 = new TextField();
        tf2.setPromptText("Record");
        tf3 = new TextArea();
        tf3.setPromptText("Some text to code or decode");

        // wybieranie przycisku Encode lub Decrypt
        ToggleGroup radioGroup = new ToggleGroup();
        radioButton1.setToggleGroup(radioGroup);
        radioButton2.setToggleGroup(radioGroup);

        // wybór opcji "Clean" czyści wszystko w programie
        m1.setAccelerator(KeyCombination.keyCombination("(restart)"));
        m1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                primaryStage.hide();
                try {
                    start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("CLEAR");
            }
        });

        // wybór opcji "Exit" wyłącza program
        m2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("EXIT");
                System.exit(0);
            }

        });

        mn22.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //tf3.appendText("Botton run clicked \n");
                // wszystko zrobic w tym miejscu

                // pilnuje by przyciski można było nacisnąć dopiero wtedy,
                // gdy w polu TextArea zostanie umieszczony jakiś tekst
                /*Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Information ERROR:");
                String s ="The text field cannot be empty!";
                alert.setContentText(s);
                alert.show(); */
//                String txt = textField.getText().trim();
//                boolean valid = true;
//                if ((txt.isEmpty()) || (txt.length() < 0)) {
//                    valid = false;
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    String s = "The text field cannot be empty!";
//                    alert.setContentText(s);
//                    alert.showAndWait();
//                }
//                actionStatus.setText(txt);
//                if (! valid) {
//                    textField.requestFocus();
//                }


            }
        });

        prepareScene(primaryStage);
    }

    //================== Konstruktor i scena ===================================

    private void prepareScene(Stage primaryStage) {

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        HBox hbox1 = new HBox(radioButton1);
        HBox hbox2 = new HBox(radioButton2);

        // tworzy listę opcji do wyboru
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Cesar", "ROT-13", "Vigenere", "Matrix",
                "AtBash");
        choiceBox.setValue("Cesar");

        // rozciąga przycisk "Run and let miracles happen" do maksymalnego rozmiaru
        button3.setMaxWidth(Double.MAX_VALUE);

        // wiersz 1
        grid.add(text1, 1, 0, 5, 1);
        // wiersz 2
        grid.add(label1, 0, 1);
        grid.add(tf1, 1, 1);
        grid.add(button1, 2, 1);
        grid.add(label3, 3, 1);
        grid.add(hbox1, 4, 1);
        // wiersz 3
        grid.add(label2, 0, 2);
        grid.add(tf2, 1, 2);
        grid.add(button2, 2, 2);
        grid.add(choiceBox, 3, 2);
        grid.add(hbox2, 4, 2);
        // wiersz 4
        grid.add(button3, 0, 3, 5, 1);
        // wiersz 5
        grid.add(tf3, 0, 4, 5, 1);

        // dodaje podpunkty w Menu
        m.getItems().add(m1);
        m.getItems().add(separator); // oddziela opcje kreską
        m.getItems().add(m2);
        mn.getItems().add(mn1);
        mn1.getItems().add(mn11); // do wyboru język angielski
        mn1.getItems().add(mn22); // do wyboru język hiszpański
        mn1.getItems().add(mn33); // do wyboru język rosyjski
        mn1.getItems().add(mn44); // do wyboru język chiński
        mn1.getItems().add(mn55); // do wyboru język południowo koreański
        // tworzy całą tabelkę menu na górze
        MenuBar mb = new MenuBar();
        // dodaje podpunkty menu do MenuBar
        mb.getMenus().add(m);
        mb.getMenus().add(mn);

        // wyświetla wszystkie ikony i menu
        VBox vb = new VBox(mb, grid);

        // to polecenie ma być na samym końcu
        scene = new Scene(vb, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //=======================================================

    public static void main(String[] args) {
        launch(args);
    }
}