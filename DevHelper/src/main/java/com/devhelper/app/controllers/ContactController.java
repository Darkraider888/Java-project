package com.devhelper.app.controllers;

import com.devhelper.app.DatabaseConnection;
import com.devhelper.app.models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

public class ContactController {

    @FXML private TableView<Contact> contactTable;
    @FXML private TableColumn<Contact, String> nameColumn;
    @FXML private TableColumn<Contact, String> roleColumn;
    @FXML private TableColumn<Contact, String> emailColumn;

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadContacts();
    }
    private void loadContacts() {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM contacts")) {

            while (rs.next()) {
                Contact c = new Contact(
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("email")
                );
                contacts.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        contactTable.setItems(contacts);
    }
}