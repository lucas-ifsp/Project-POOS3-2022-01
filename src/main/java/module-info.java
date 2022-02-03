module br.edu.ifsp.poos {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.edu.ifsp.poos3 to javafx.fxml;
    exports br.edu.ifsp.poos3;
}