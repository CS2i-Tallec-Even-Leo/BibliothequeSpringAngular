module org.leotalleceven.bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires annotations;
    requires javafx.graphics;
    requires java.sql;


    opens org.leotalleceven.bibliotheque to javafx.fxml;
    exports org.leotalleceven.bibliotheque;
    exports org.leotalleceven.bibliotheque.controller;
    opens org.leotalleceven.bibliotheque.controller to javafx.fxml;
}