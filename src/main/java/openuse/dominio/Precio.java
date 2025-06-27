package openuse.dominio;

import java.io.Serial;
import java.io.Serializable;

public class Precio implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String URL;
    private Fuente fuente;

    public Precio(String URL, Fuente fuente) {
        this.URL = URL;
        this.fuente = fuente;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Fuente getFuente() {
        return fuente;
    }

    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }

    @Override
    public String toString() {
        return URL;
    }
}
