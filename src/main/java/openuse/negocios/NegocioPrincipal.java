package openuse.negocios;

import openuse.dominio.Fuente;
import openuse.dominio.Precio;
import openuse.dominio.Producto;
import openuse.integrador.IntegradorExcel;
import openuse.integrador.IntegradorScrapper;

import java.io.Serializable;
import java.util.ArrayList;

public class NegocioPrincipal implements Serializable {
    ArrayList<Producto> productos;
    ArrayList<Fuente> fuentes;
    IntegradorScrapper scrapper = new IntegradorScrapper();
    IntegradorExcel excel = new IntegradorExcel();

    public NegocioPrincipal() {
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

