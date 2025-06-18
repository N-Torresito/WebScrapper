package openuse.pantallas;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Pair;
import openuse.dominio.Fuente;
import openuse.integrador.IntegradorScrapper;
import openuse.negocios.NegocioPrincipal;
import java.io.IOException;


public class TestController {
    @javafx.fxml.FXML
    private Label infolabel;
    @javafx.fxml.FXML
    private TextField urltxt;
    @javafx.fxml.FXML
    private TextField normalHTMLtxt;
    @javafx.fxml.FXML
    private TextField discounttxt;
    @javafx.fxml.FXML
    private Button testbtn;
    @javafx.fxml.FXML
    private TextField nombrefuentetxt;
    @javafx.fxml.FXML
    private ComboBox fuentescmbx;
    @javafx.fxml.FXML
    private Button guardarfuentesbtn;

    private NegocioPrincipal negocio;
    private IntegradorScrapper scrapper;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @javafx.fxml.FXML
    private Button btnModificar;


    @javafx.fxml.FXML
    public void onClickPrueba(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String datos = "";
        double normal = 0.0;
        double descuento = 0.0;

        String normalHTML = normalHTMLtxt.getText();
        String discountHTML = discounttxt.getText();
        String URL = urltxt.getText();
        Fuente fuente = (Fuente) fuentescmbx.getSelectionModel().getSelectedItem();

        if (fuente != null){
            normalHTML = fuente.getOriginalHtmlClass();
            discountHTML = fuente.getDescuentoHtmlClass();
            normalHTMLtxt.clear();
            discounttxt.clear();
        } else {
            if (normalHTMLtxt.getText().isBlank() || discountHTML.isBlank()){
                alert.setContentText("Ingrese todos los datos necesarios para la prueba (URL, HTML normal, HTML descuento)");
                alert.show();
                return;
            }
        }

        if (URL.isBlank()){
            alert.setContentText("Ingrese una URL");
            alert.show();
        }

        try {
            Pair<Double, Double> precios = scrapper.testScrapper(URL, normalHTML, discountHTML);
            normal = precios.getKey();
            descuento = precios.getValue();
        } catch (IOException e) {
            alert.setContentText("URL no valida");
            alert.show();
        } catch (RuntimeException e) {
            alert.setContentText("No se ha encontrado uno o ambos precios");
            alert.show();
        } finally {
            infolabel.setText("Precio normal: " + normal + "\nPrecio Descuento: " + descuento);
        }
    }

    @javafx.fxml.FXML
    public void onClickGuardar(ActionEvent actionEvent) {
        if (nombrefuentetxt.getText().isEmpty() || nombrefuentetxt.getText().isBlank()){
            alert.setContentText("Escriba un nombre para la fuente");
            alert.show();
            return;
        }
        if (normalHTMLtxt.getText().isEmpty() || discounttxt.getText().isEmpty()){
            alert.setContentText("Ingrese los datos para extraer");
            alert.show();
            return;
        }

        if(negocio.agregarFuente(new Fuente(nombrefuentetxt.getText(), normalHTMLtxt.getText(), discounttxt.getText()))){
            alert.setContentText("Se agregó la fuente");
            nombrefuentetxt.clear();
        } else {
            alert.setContentText("No se pudo agregar la fuente");
        }
        alert.show();
        actualizarFuentesCMB();
    }

    @javafx.fxml.FXML
    public void onClickEliminarFuentes(ActionEvent actionEvent) {
        if (fuentescmbx.getSelectionModel().getSelectedItem() == null){
            alert.setContentText("Seleccione una fuente a modificar");
            alert.show();
            return;
        }

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Está seguro de modificar la fuente: " + fuentescmbx.getSelectionModel().getSelectedItem().toString() + "?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Fuente fuente = (Fuente) fuentescmbx.getSelectionModel().getSelectedItem();
                negocio.getFuentes().remove(fuente);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Se ha eliminado la fuente");
                alert.show();
            }
        });
        fuentescmbx.getSelectionModel().clearSelection();
        actualizarFuentesCMB();
    }

    public void actualizarFuentesCMB(){
        fuentescmbx.getItems().clear();
        for (Fuente f : negocio.getFuentes()){
            fuentescmbx.getItems().add(f);
        }
    }

    public NegocioPrincipal getNegocio() {
        return negocio;
    }

    public void setNegocio(NegocioPrincipal negocio) {
        this.negocio = negocio;
        scrapper = negocio.getScrapper();
        actualizarFuentesCMB();
    }
}
