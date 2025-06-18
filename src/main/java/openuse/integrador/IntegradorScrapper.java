package openuse.integrador;

import javafx.util.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Objects;

public class IntegradorScrapper {
    //TODO

    public Pair<Double,Double> testScrapper(String URL, String normal, String descuento) throws IOException {
        String precioNormal = null;
        String precioDescuento = null;

        Document document = Jsoup.connect(URL).get();
        precioNormal = (document.selectFirst(normal) != null) ? document.selectFirst(normal).ownText().replace(".","").replace("$", "") : null;
        precioDescuento = (document.selectFirst(descuento) != null) ? document.selectFirst(descuento).ownText().replace(".","").replace("$", "") : null;




        return new Pair<Double, Double>(Double.parseDouble(precioNormal), Double.parseDouble(precioDescuento));
    }


}
