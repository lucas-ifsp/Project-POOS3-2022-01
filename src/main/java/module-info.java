module br.edu.ifsp.poos {
    requires java.sql;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;

    opens br.edu.ifsp.poos3.practical03.persistence;
    opens br.edu.ifsp.poos3.practical03.controller;
    opens br.edu.ifsp.poos3.practical03.view;
    opens br.edu.ifsp.poos3.practical03.model;
}