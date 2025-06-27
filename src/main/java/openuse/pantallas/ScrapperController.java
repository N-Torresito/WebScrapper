package openuse.pantallas;



import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import openuse.Fx_main;
import openuse.dominio.Fuente;
import openuse.dominio.Precio;
import openuse.dominio.Producto;
import openuse.negocios.Negocio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    @javafx.fxml.FXML
    private Button probarBtn;
    @javafx.fxml.FXML
    private MenuItem menuArchivoAbrir;
    @javafx.fxml.FXML
    private MenuItem menuExportarCSV;
    @javafx.fxml.FXML
    private MenuItem menuArchivoGuardar;
    @javafx.fxml.FXML
    private MenuItem menuExportarExcel;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Negocio negocio = new Negocio();
    @javafx.fxml.FXML
    private ProgressIndicator estadoProgressIndicator;

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

        urltxt.clear();
        actualizarPrecios(producto);
        alert.setContentText("Se agregó el precio al producto");
        alert.show();
    }

    @javafx.fxml.FXML
    public void onClickAgregarProducto(ActionEvent actionEvent) {
        String nombre = nombreProductoTxt.getText();
        String marca = marcaProductoTxt.getText();

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
    public void onClickEliminarPrecio(ActionEvent actionEvent) {
        Precio precio = (Precio) preciosListView.getSelectionModel().getSelectedItem();
        Producto producto = (Producto) productosListView.getSelectionModel().getSelectedItem();

        if (precio == null || producto == null){
            alert.setContentText("Seleccione el producto y precio que desea eliminar");
            alert.show();
            return;
        }

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("¿Está seguro de eliminar el precio?");
        alert.showAndWait().ifPresent(response -> {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            if (response == ButtonType.OK) {
                producto.getPrecios().remove(precio);
                alert.setContentText("Se ha eliminado el precio");
            } else {
                alert.setContentText("No se eliminado el precio");
            }
            alert.show();
        });

        actualizarPrecios(producto);
    }

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

        preciosListView.getItems().clear();
        actualizarProductos();
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

    @javafx.fxml.FXML
    public void onClickProbarProducto(ActionEvent actionEvent) {
        Producto producto = (Producto) productosListView.getSelectionModel().getSelectedItem();
        if (producto == null || producto.getPrecios().isEmpty()){
            alert.setContentText("Seleccione un producto o agregue precios para buscar");
            alert.show();
            return;
        }

        try {
            ArrayList<Pair<String, Double>> datos = negocio.getScrapper().productScrapping(producto);

            for (Pair<String, Double> p : datos){
                System.out.println(p.getKey() + " " + p.getValue());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onClickArchivoGuardar(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Archivos Binarios", "*.bin");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setInitialFileName("datos.bin");

        File archivo = fileChooser.showSaveDialog(new Stage());

        if (archivo == null){
            return;
        }

        negocio.guardarArchivo(archivo, negocio);
        System.out.println(archivo.getPath());

        alert.setContentText("Archivo Guardado con exito en " + archivo.getPath());
        alert.show();
    }

    @javafx.fxml.FXML
    public void onClickArchivoAbrir(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Archivos Binarios", "*.bin");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setInitialFileName("datos.bin");

        File archivo = fileChooser.showOpenDialog(new Stage());

        if (archivo == null){
            return;
        }

        Negocio nuevoNegocio;
        try {
            nuevoNegocio = negocio.leerArchivo(archivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (nuevoNegocio != null) {
            negocio = nuevoNegocio;
        }

        actualizarProductos();
        actualizarFuentes();

        System.out.println(archivo.getPath());

        alert.setContentText("Archivo cargado con exito en " + archivo.getPath());
        alert.show();
    }

    @javafx.fxml.FXML
    public void onClickExportarCSV(ActionEvent actionEvent) {

        if (productosListView.getItems().isEmpty()){
            alert.setContentText("Ingrese productos para exportar");
            alert.show();
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exportar a csv");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("CSV", "*.csv");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setInitialFileName("precios.csv");

        estadoProgressIndicator.opacityProperty().set(1);
        File archivo = fileChooser.showSaveDialog(new Stage());

        if (archivo == null){
            estadoProgressIndicator.opacityProperty().set(0.0);
            return;
        }



        for (Producto p : negocio.getProductos()){
            try {
                negocio.getArchivos().exportarCSV(negocio.getScrapper().productScrapping(p), p.toString(), archivo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        estadoProgressIndicator.opacityProperty().set(0.0);
        alert.setContentText("Archivo Guardado con exito en " + archivo.getPath());
        alert.show();
    }

    @javafx.fxml.FXML
    public void onClickExportarExcel(ActionEvent actionEvent) {
        //TODO
    }

    @javafx.fxml.FXML
    public void onClickListViewActualizarPrecios(Event event) {
        Producto producto = (Producto) productosListView.getSelectionModel().getSelectedItem();

        if (producto == null){
            alert.setContentText("Agregue un producto");
            alert.show();
            return;
        }

        actualizarPrecios(producto);
    }

    public void actualizarFuentes(){
        fuentesComboBox.getItems().clear();
        for(Fuente f: negocio.getFuentes()){
            fuentesComboBox.getItems().add(f);
        }
    }

    public void actualizarPrecios(Producto producto){
        preciosListView.getItems().clear();
        for(Precio p : producto.getPrecios()){
            preciosListView.getItems().add(p);
        }
    }

    public void actualizarProductos(){
        productosListView.getItems().clear();
        for(Producto p : negocio.getProductos()){
            productosListView.getItems().add(p);
        }
    }
}
