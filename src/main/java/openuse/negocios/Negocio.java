package openuse.negocios;

import openuse.dominio.Fuente;
import openuse.dominio.Precio;
import openuse.dominio.Producto;
import openuse.integrador.IntegradorArchivos;
import openuse.integrador.IntegradorExcel;
import openuse.integrador.IntegradorScrapper;

import java.io.*;
import java.util.ArrayList;

public class Negocio implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    ArrayList<Producto> productos;
    ArrayList<Fuente> fuentes;
    IntegradorScrapper scrapper = new IntegradorScrapper();
    IntegradorExcel excel = new IntegradorExcel();
    IntegradorArchivos archivos = new IntegradorArchivos();

    public Negocio() {
        productos = new ArrayList<Producto>();
        fuentes = new ArrayList<Fuente>();
    }

    public boolean agregarFuente(Fuente fuenteDTO){
        for (Fuente e : fuentes){
            if (e.getName().equals(fuenteDTO.getName())){
                if (!e.getDescuentoHtmlClass().equals(fuenteDTO.getDescuentoHtmlClass())){
                    e.setDescuentoHtmlClass(fuenteDTO.getDescuentoHtmlClass());
                }
                if (!e.getOriginalHtmlClass().equals(fuenteDTO.getOriginalHtmlClass())){
                    e.setOriginalHtmlClass(fuenteDTO.getOriginalHtmlClass());
                }
                return false;
            }
        }

        fuentes.add(fuenteDTO);
        return true;
    }

    public boolean agregarProducto(Producto productoDTO){
        for (Producto p : productos){
            if (p.getNombre().equals(productoDTO.getNombre())){
                return false;
            }
        }
        productos.add(productoDTO);
        return true;
    }

    public boolean agregarPrecio(Producto productoDTO, Precio precioDTO){
        for(Precio p : productoDTO.getPrecios()){
            if(p.getURL().equals(precioDTO.getURL())){
                return false;
            }
        }
        productoDTO.getPrecios().add(precioDTO);
        return true;
    }

    public void modificarFuente(Fuente originalDTO, Fuente fuenteDTO){
        originalDTO.setOriginalHtmlClass(fuenteDTO.getOriginalHtmlClass());
        originalDTO.setDescuentoHtmlClass(fuenteDTO.getDescuentoHtmlClass());
        originalDTO.setName(fuenteDTO.getName());
    }

    public void guardarArchivo(File file, Negocio negocio){
        archivos.guardarNegocio(file, negocio);
    }

    //-------//
    //Getter & Setters

    public Negocio  leerArchivo(File file) throws IOException { return archivos.leerNegocio(file);}

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Fuente> getFuentes() {
        return fuentes;
    }

    public void setFuentes(ArrayList<Fuente> fuentes) {
        this.fuentes = fuentes;
    }

    public IntegradorArchivos getArchivos() {
        return archivos;
    }

    public void setArchivos(IntegradorArchivos archivos) {
        this.archivos = archivos;
    }

    public IntegradorScrapper getScrapper() {
        return scrapper;
    }

    public void setScrapper(IntegradorScrapper scrapper) {
        this.scrapper = scrapper;
    }

    public IntegradorExcel getExcel() {
        return excel;
    }

    public void setExcel(IntegradorExcel excel) {
        this.excel = excel;
    }
}

