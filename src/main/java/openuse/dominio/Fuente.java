package openuse.dominio;

import java.io.Serializable;

public class Fuente implements Serializable {
    private String name;
    private String originalHtmlClass;
    private String descuentoHtmlClass;

    public Fuente(String name, String originalHtmlClass, String descuentoHtmlClass) {
        this.name = name;
        this.originalHtmlClass = originalHtmlClass;
        this.descuentoHtmlClass = descuentoHtmlClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalHtmlClass() {
        return originalHtmlClass;
    }

    public void setOriginalHtmlClass(String originalHtmlClass) {
        this.originalHtmlClass = originalHtmlClass;
    }

    public String getDescuentoHtmlClass() {
        return descuentoHtmlClass;
    }

    public void setDescuentoHtmlClass(String descuentoHtmlClass) {
        this.descuentoHtmlClass = descuentoHtmlClass;
    }

    @Override
    public String toString() {
        return name;
    }
}
