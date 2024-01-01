module com.example.musicplayerfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires com.almasb.fxgl.all;

    opens com.example.musicplayerfx to javafx.fxml;
    exports com.example.musicplayerfx;
    exports com.example.musicplayerfx.controllers;
    exports com.example.musicplayerfx.models;
    opens com.example.musicplayerfx.controllers to javafx.fxml;



}