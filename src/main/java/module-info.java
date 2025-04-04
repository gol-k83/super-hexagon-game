module org.example.superhexagon {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.superhexagon to javafx.fxml;
    exports org.example.superhexagon;
}