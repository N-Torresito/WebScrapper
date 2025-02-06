package openuse.nt;

import openuse.exceptions.ObjetivoExc;
import openuse.exceptions.PreciosExc;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase que representa los precios de un producto de diferentes proveedores en un solo objeto
 */
public class Precios implements Serializable {
    private String nombreProducto;
    private List<Objetivo> objetivos;
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase Precios con parametros individuales
     * @param nombreProducto
     * @throws PreciosExc
     */
    public Precios(String nombreProducto) throws PreciosExc {
        if (nombreProducto == null || nombreProducto.isBlank()) {
            throw new PreciosExc("El nombre del producto no puede ser nulo o vacío");
        }
        this.nombreProducto = nombreProducto;
        objetivos = new ArrayList<>();
    }

    /**
     * Constructor de la clase Precios con parametros de un objeto
     * @param precios
     */
    public Precios(Precios precios) {
        this.nombreProducto = precios.getNombreProducto();
        objetivos = new ArrayList<>();
        objetivos.addAll(precios.getObjetivos());
    }

    /**
     * Agrega un objetivo a la lista de objetivos
     * @param objetivo
     */
    public void agregarObjetivo(Objetivo objetivo) {
        objetivos.add(objetivo);
    }

    public void agregarObjetivo(double precio, String nombreProveedor) throws ObjetivoExc {
        objetivos.add(new Objetivo(precio, nombreProveedor, nombreProducto));
    }

    /**
     * Retorna el nombre del producto
     * @return
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
     * Retorna la lista de objetivos
     * @return
     */
    public List<Objetivo> getObjetivos() {
        return objetivos;
    }

    /**
     * Modifica la lista de objetivos
     * @param objetivos
     */
    public void setObjetivos(List<Objetivo> objetivos) {
        this.objetivos = objetivos;
    }
}
