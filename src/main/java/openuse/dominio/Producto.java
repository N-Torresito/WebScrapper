package openuse.dominio;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String marca;
    private ArrayList<Precio> precios;

    public Producto(String nombre, String marca) {
        this.nombre = nombre;
        this.marca = marca;
        this.precios = new ArrayList<Precio>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public ArrayList<Precio> getPrecios() {
        return precios;
    }

    public void setPrecios(ArrayList<Precio> precios) {
        this.precios = precios;
    }

    @Override
    public String toString() {
        return nombre + " - " + marca;
    }
}
