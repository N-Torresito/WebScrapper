package openuse.integrador;

import javafx.util.Pair;
import openuse.negocios.Negocio;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class IntegradorArchivos implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    public Negocio leerNegocio (File archivo) throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))){
            return (Negocio) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarNegocio (File archivo, Negocio negocio){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))){
            out.writeObject(negocio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarCSV (ArrayList<Pair<String, Double>> datos, String producto, File file) throws Exception {
        datos.sort(Comparator.comparing(Pair::getKey));

        StringBuilder dato = new StringBuilder();
        datos.forEach(stringDoublePair -> dato.append(",").append(stringDoublePair.getKey()));
        dato.append("\n").append(producto);
        datos.forEach(stringDoublePair -> dato.append(",").append(stringDoublePair.getValue()));

        try (FileOutputStream stream = new FileOutputStream(file)){
            stream.write(dato.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
