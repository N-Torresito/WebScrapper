package openuse.nt;
import java.util.*;

public class Manejador {
   List<Precios> productos;

   public Manejador() {
       productos = new ArrayList<>();
   }

   public void agregarProducto(Precios producto) {
       productos.add(producto);
   }

   public void agregarProducto(String nombreProducto) {
       //TODO: Implementar
   }

    public void agregarProducto(double precio, String nombreProducto, String nombreProveedor) {
       //TODO: Implementar
    }

    public void eliminarProducto(Precios producto) {
         productos.remove(producto);
    }
}
