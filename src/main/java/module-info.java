module  openuse{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jsoup;

    opens openuse.pantallas to javafx.fxml;
    exports openuse.pantallas;
    exports openuse;
    opens openuse to javafx.fxml;
}