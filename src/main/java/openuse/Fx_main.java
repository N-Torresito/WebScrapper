package openuse;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class Fx_main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Fx_main.class.getResource("scrapper.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Web Scrapper");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿Está seguro de cerrar la aplicación?" +
                            "Esto borrará todo trabajo no guardado");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.out.println("Closing application...");
                    Platform.exit();
                    System.exit(0);
                } else {
                    event.consume();
                }
            });
            event.consume();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
