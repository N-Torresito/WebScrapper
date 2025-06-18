package openuse.pantallas;



import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import openuse.Fx_main;
import openuse.dominio.Fuente;
import openuse.dominio.Precio;
import openuse.dominio.Producto;
import openuse.negocios.NegocioPrincipal;

import java.io.IOException;

public class ScrapperController {
    @javafx.fxml.FXML
    private ListView productosListView;
    @javafx.fxml.FXML
    private TextField urltxt;
    @javafx.fxml.FXML
    private Button agregarProductoBtn;
    @javafx.fxml.FXML
    private ComboBox fuentesComboBox;
    @javafx.fxml.FXML
    private Button eliminarPrecioBtn;
    @javafx.fxml.FXML
    private Button agregarPrecioBtn;
    @javafx.fxml.FXML
    private ListView preciosListView;
    @javafx.fxml.FXML
    private TextField nombreProductoTxt;
    @javafx.fxml.FXML
    private Button administrarFuentesBtn;
    @javafx.fxml.FXML
    private TextField marcaProductoTxt;
    @javafx.fxml.FXML
    private Button eliminarProductoBtn;
    @javafx.fxml.FXML
    private Label infoTxt;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private NegocioPrincipal negocio = new NegocioPrincipal();

    @javafx.fxml.FXML
    public void onClickEliminarProducto(ActionEvent actionEvent) {
        Producto producto = (Producto) productosListView.getSelectionModel().getSelectedItem();

        if(producto == null){
            alert.setContentText("Seleccione un producto a eliminar");
            alert.show();
            return;
        }

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Está seguro de eliminar el producto?" +
                "Todos los precios relacionados seguran eliminados junto al producto");
        alert.showAndWait().ifPresent(response -> {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            if (response == ButtonType.OK) {
                negocio.getProductos().remove(producto);
                alert.setContentText("Se ha eliminado el producto");
            } else {
                alert.setContentText("No se eliminado el producto");
            }
            alert.show();
        });

        actualizarProductos();
    }

    @javafx.fxml.FXML
    public void onClickAgregarPrecio(ActionEvent actionEvent) {
        Producto producto = (Producto) productosListView.getSelectionModel().getSelectedItem();
        Fuente fuente = (Fuente) fuentesComboBox.getSelectionModel().getSelectedItem();
        String URL = urltxt.getText();

        if (fuente == null){
            alert.setContentText("Seleccione una fuente");
            alert.show();
            return;
        }
        if (producto == null){
            alert.setContentText("Seleccione un producto");
            alert.show();
            return;
        }
        if (URL.isBlank() || !URL.contains(".com")){
            alert.setContentText("Ingrese una URL valida");
            alert.show();
            return;
        }

        producto.getPrecios().add(new Precio(URL, fuente));
        alert.setContentText("Se agregó el precio al producto");
        alert.show();
    }

    @javafx.fxml.FXML
    public void onClickEliminarPrecio(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onClickAgregarProducto(ActionEvent actionEvent) {
        String nombre = nombreProductoTxt.getText();
        String marca = nombreProductoTxt.getText();

        if (nombre.isBlank() || nombre.isEmpty()){
            alert.setContentText("Ingrese el nombre del producto");
            alert.show();
            return;
        }
        if (marca.isBlank() || marca.isEmpty()){
            alert.setContentText("Ingrese el nombre del producto");
            alert.show();
            return;
        }

        for (Producto p : negocio.getProductos()){
            if(p.getNombre().equals(nombre) && p.getMarca().equals(marca)){
                alert.setContentText("El producto ya existe");
                alert.show();
                return;
            }
        }

        negocio.getProductos().add(new Producto(nombre, marca));
        actualizarProductos();
        preciosListView.getItems().clear();
    }

    @javafx.fxml.FXML
    public void onClickListViewActualizarPrecios(Event event) {
        preciosListView.getItems().clear();
        Producto producto = (Producto) productosListView.getSelectionModel().getSelectedItem();
        for(Precio p : producto.getPrecios()){
            preciosListView.getItems().add(p);
        }
    }

    public void actualizarFuentes(){
        fuentesComboBox.getItems().clear();
        for(Fuente f: negocio.getFuentes()){
            fuentesComboBox.getItems().add(f);
        }
    }

    public void actualizarProductos(){
        productosListView.getItems().clear();
        for(Producto p : negocio.getProductos()){
            productosListView.getItems().add(p);
        }
    }

    @javafx.fxml.FXML
    public void onClickAdministrarFuentes(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Fx_main.class.getResource("prueba.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ((TestController) fxmlLoader.getController()).setNegocio(this.negocio);
        stage.setTitle("Modificación y prueba de fuentes");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            actualizarFuentes();
        });
    }
}
