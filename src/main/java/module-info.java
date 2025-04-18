module org.example.superhexagon {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens org.example.superhexagon to javafx.fxml;
    exports org.example.superhexagon;
   // exports obstacle6P;
   // opens obstacle6P to javafx.fxml;
   // exports org.example.superhexagon.lib;
   // opens org.example.superhexagon.lib to javafx.fxml;
}