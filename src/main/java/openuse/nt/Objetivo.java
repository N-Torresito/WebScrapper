package openuse.nt;
import openuse.exceptions.ObjetivoExc;

import java.io.Serial;
import java.io.Serializable;

/**
 * Clase Objetivo, la cual representa un producto de un proveedor con un precio
 */
public class Objetivo implements Serializable {
    private double precio;
    
    private String nombreProveedor;
    private String nombreProducto;
    private String Url;
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase Objetivo con parametros indivudales
     * @param precio
     * @param nombreProveedor
     * @throws ObjetivoExc
     */
    public Objetivo (double precio, String nombreProveedor, String nombreProducto, String Url) throws ObjetivoExc {
        if (precio < 0) {
            throw new ObjetivoExc("El precio no puede ser negativo");
        }
        if (nombreProveedor == null || nombreProveedor.isBlank()) {
            throw new ObjetivoExc("El nombre del proveedor no puede ser nulo o vacío");
        }
        if (nombreProducto == null || nombreProducto.isBlank()) {
            throw new ObjetivoExc("El nombre del producto no puede ser nulo o vacío");
        }
        if (Url == null || Url.isBlank()) {
            throw new ObjetivoExc("La url no puede ser nula o vacía");
        }
        this.precio = precio;
        this.nombreProveedor = nombreProveedor;
        this.nombreProducto = nombreProducto;
        this.Url = Url;
    }

    /**
     * Constructor de la clase Objetivo con parametros de un objeto
     * @param objetivo
     */
    public Objetivo(Objetivo objetivo) {
        this.precio = objetivo.getPrecio();
        this.nombreProveedor = objetivo.getNombreProveedor();
        this.nombreProducto = objetivo.getNombreProducto();
    }

    /**
     * Retorna el precio del producto
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio del producto
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Retorna el nombre del producto
     * @return nombreProveedor
     */
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    /**
     * Modifica el nombre del producto
     * @param nombreProveedor
     */
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    /**
     * Retorna el nombre del producto
     * @return nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Modifica el nombre del producto
     * @param nombreProducto
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Retorna la url del producto
     * @return Url
     */
    public String getUrl() {
        return Url;
    }

    /**
     * Modifica la url del producto
     * @param url
     */
    public void setUrl(String url) {
        this.Url = url;
    }

    /**
     * Retorna un String con el nombre y precio
     * @return nombre+precio
     */
    public String toStringSimple() {
        return nombreProveedor + "," + precio;
    }

    /**
     * Retorna un String con el nombre, precio y nombre del producto
     * @return nombre+precio+nombreProducto
     */
    public String toStringCompleto() {
        return nombreProveedor + "," + precio + "," + nombreProducto;
    }
}
