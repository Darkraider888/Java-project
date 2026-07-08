package com.devhelper.app.controllers;

import com.devhelper.app.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import java.sql.*;

public class SnippetController {

    @FXML private TextArea snippetTextArea;
    @FXML private ListView<String> savedSnippetsList;

    @FXML
    public void initialize() {
        loadSnippets();
    }

    @FXML
    private void handleSaveSnippet() {
        String code = snippetTextArea.getText();

        if (code == null || code.trim().isEmpty()) {
            return;
        }

        String sql = "INSERT INTO snippets (code_text) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Snippet saved!");
            alert.showAndWait();

            snippetTextArea.clear();
            loadSnippets(); // refresh the history list

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSnippets() {
        savedSnippetsList.getItems().clear();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT code_text FROM snippets ORDER BY id DESC")) {

            while (rs.next()) {
                savedSnippetsList.getItems().add(rs.getString("code_text"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}