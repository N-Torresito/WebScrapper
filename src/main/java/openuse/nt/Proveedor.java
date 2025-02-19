package openuse.nt;

/**
 * Clase que representa un proveedor de producto, con sus respectivos selectores para obtener la información
 */
public class Proveedor {
    private String nombreProveedor;
    private String url;
    private String selectorTitulo;
    private String selectorPrecio;
    private String selectorImagen;

    /**
     * Constructor de la clase Proveedor con parametros individuales
     * @param nombreProveedor
     * @param url
     * @param selectorTitulo
     * @param selectorPrecio
     * @param selectorImagen
     */
    public Proveedor(String nombreProveedor, String url, String selectorTitulo, String selectorPrecio, String selectorImagen) {
        this.nombreProveedor = nombreProveedor;
        this.url = url;
        this.selectorTitulo = selectorTitulo;
        this.selectorPrecio = selectorPrecio;
        this.selectorImagen = selectorImagen;
    }

    /**
     * Retorna el nombre del proveedor
     * @return nombreProveedor
     */
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    /**
     * Modifica el nombre del proveedor
     * @param nombreProveedor
     */
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    /**
     * Retorna la url del proveedor
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Modifica la url del proveedor
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Retorna el selector del titulo
     * @return
     */
    public String getSelectorTitulo() {
        return selectorTitulo;
    }

    /**
     * Modifica el selector del titulo
     * @param selectorTitulo
     */
    public void setSelectorTitulo(String selectorTitulo) {
        this.selectorTitulo = selectorTitulo;
    }

    /**
     * Retorna el selector del precio
     * @return
     */
    public String getSelectorPrecio() {
        return selectorPrecio;
    }

    /**
     * Modifica el selector del precio
     * @param selectorPrecio
     */
    public void setSelectorPrecio(String selectorPrecio) {
        this.selectorPrecio = selectorPrecio;
    }

    /**
     * Retorna el selector de la imagen
     * @return
     */
    public String getSelectorImagen() {
        return selectorImagen;
    }

    /**
     * Modifica el selector de la imagen
     * @param selectorImagen
     */
    public void setSelectorImagen(String selectorImagen) {
        this.selectorImagen = selectorImagen;
    }
}
