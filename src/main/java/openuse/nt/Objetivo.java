package openuse.nt;
import openuse.exceptions.ObjetivoExc;

/**
 * Clase Objetivo, la cual representa un producto de un proveedor con un precio
 */
public class Objetivo {
    private double precio;
    private String nombreProveedor;
    private String nombreProducto;


    /**
     * Constructor de la clase Objetivo con parametros indivudales
     * @param precio
     * @param nombreProveedor
     * @throws ObjetivoExc
     */
    public Objetivo (double precio, String nombreProveedor, String nombreProducto) throws ObjetivoExc {
        if (precio < 0) {
            throw new ObjetivoExc("El precio no puede ser negativo");
        }
        if (nombreProveedor == null || nombreProveedor.isBlank()) {
            throw new ObjetivoExc("El nombre del proveedor no puede ser nulo o vacío");
        }
        if (nombreProducto == null || nombreProducto.isBlank()) {
            throw new ObjetivoExc("El nombre del producto no puede ser nulo o vacío");
        }
        this.precio = precio;
        this.nombreProveedor = nombreProveedor;
        this.nombreProducto = nombreProducto;
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

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Retorna un String con los datos del objetivo
     * @return precio
     */
    @Override
    public String toString() {
        return nombreProveedor + " $" + precio;
    }

    public String toStringCompleto() {
        return nombreProveedor + " $" + precio + " " + nombreProducto;
    }
}
