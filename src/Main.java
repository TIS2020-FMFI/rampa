import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {
    BorderPane root;
    Button vytvoritGrafikonBtn = new Button("Vytvoriť nový grafikon");
    TextField nazovSuboruTV = new TextField();
    Button nacitatGrafikonBtn = new Button("Načítať nový grafikon");
    Label alert = new Label("Musíte vyplniť názov súboru.");
    ButtonsPane buttonsPaneGrid = new ButtonsPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();

        // nastav chybovu spravu
        alert.setVisible(false);
        alert.setTextFill(Color.RED);

        // zobraz obrazok kamionu
        ImageView img = new ImageView(new Image(new FileInputStream("kamionObr.png")));
        img.setPreserveRatio(true);
        img.setFitWidth(400);
        HBox hboxImg = new HBox(img);
        hboxImg.setAlignment(Pos.BOTTOM_CENTER);

        // zobraz tlacidla a input text
        nazovSuboruTV.setTranslateX(-10);
        nazovSuboruTV.setPromptText("Zadaj názov grafikonu");
        buttonsPaneGrid.add(alert, 0, 0);

        buttonsPaneGrid.add(vytvoritGrafikonBtn, 0, 1);
        buttonsPaneGrid.add(nazovSuboruTV, 0, 2);
        buttonsPaneGrid.setVgap(15);
        buttonsPaneGrid.setPadding(new Insets(30, 0, 0, 0));


        HBox hbox = new HBox(nacitatGrafikonBtn);
        hbox.setPadding(new Insets(60, 0, 100, 0));

        hbox.setTranslateX(root.getPrefWidth());
        root.setTop(hboxImg);
        root.setCenter(buttonsPaneGrid);
        root.setBottom(hbox);

        vytvoritGrafikonBtn.setOnAction(e -> {
            if (nazovSuboruTV.getText().isEmpty()) showAlert();
            else {
                alert.setVisible(false);
                // Platform.exit();
            }
        });

        nacitatGrafikonBtn.setOnAction(e -> {
            alert.setVisible(false);
        });

        primaryStage.getIcons().add(new Image(new FileInputStream("kamionObr.png")));
        primaryStage.setTitle("Grafikon GEFCO");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

        buttonsPaneGrid.setTranslateX(root.getWidth()/2 - nacitatGrafikonBtn.getWidth()/2);
        hbox.setTranslateX(root.getWidth()/2 - nacitatGrafikonBtn.getWidth()/2);
    }

    public void showAlert() {
        alert.setVisible(true);
        alert.setTextFill(Color.RED);
    }

    public static class ButtonsPane extends GridPane {
    }


    public static void main(String[] args) {
        launch(args);
    }
}

