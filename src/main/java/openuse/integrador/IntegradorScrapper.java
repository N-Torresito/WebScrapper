package openuse.integrador;

import javafx.util.Pair;
import openuse.dominio.Precio;
import openuse.dominio.Producto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class IntegradorScrapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public ArrayList<Pair<String, Double>> productScrapping (Producto producto) throws IOException {
        ArrayList<Precio> precios = producto.getPrecios();
        ArrayList<Pair<String,Double>> datos = new ArrayList<Pair<String, Double>>();

        for (Precio p: precios){
            String URL = p.getURL();
            double normal = singleScrape(URL, p.getFuente().getOriginalHtmlClass());
            double descuento = singleScrape(URL, p.getFuente().getDescuentoHtmlClass());
            double menor;

            if (normal == 0.0 || descuento == 0.0){
                menor = Math.max(normal, descuento);
            } else {
                menor = Math.min(normal, descuento);
            }
            datos.add(new Pair<String, Double>(p.getFuente().getName(), menor));
        }
        return datos;
    }

    public double singleScrape(String URL, String htmlQuery) throws IOException {
        String precio = null;
        Document document = Jsoup.connect(URL).get();
        precio = (document.selectFirst(htmlQuery) != null) ? document.selectFirst(htmlQuery).ownText().replace(".","").replace("$", "") : null;
        return Double.parseDouble(precio);
    }

    public Pair<Double,Double> testScrapper(String URL, String normal, String descuento) throws IOException {
        double precioNormal = singleScrape(URL, normal);
        double precioDescuento = singleScrape(URL, descuento);

        return new Pair<Double, Double>(precioNormal, precioDescuento);
    }


}
