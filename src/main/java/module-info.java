module  openuse.fx{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jsoup;

    opens openuse.fx to javafx.fxml;
    exports openuse.fx;
}