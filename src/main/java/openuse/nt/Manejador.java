package openuse.nt;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Manejador implements Serializable {
   List<Precios> preciosProductos;
   @Serial
   private static final long serialVersionUID = 1L;

   public Manejador() {
       preciosProductos = new ArrayList<>();
   }

   public void agregarProducto(Precios producto) {
       preciosProductos.add(producto);
   }

   public void agregarProducto(String nombreProducto) {
       //TODO: Implementar
   }

    public void agregarProducto(double precio, String nombreProducto, String nombreProveedor) {
       //TODO: Implementar
    }


    public void eliminarProductoObjeto(Precios producto) {
         preciosProductos.remove(producto);
    }
}
