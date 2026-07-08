package com.devhelper.app.controllers;

import com.devhelper.app.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import java.sql.*;

public class ProgressController {

    @FXML private TextArea progressTextArea;
    @FXML private ListView<String> historyListView;

    @FXML
    public void initialize() {
        loadHistory();
    }

    @FXML
    private void handleSaveLog() {
        String logText = progressTextArea.getText();
        if (logText == null || logText.trim().isEmpty()) return;

        String sql = "INSERT INTO progress_logs (log_text) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, logText);
            ps.executeUpdate();

            progressTextArea.clear();
            loadHistory();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadHistory() {
        historyListView.getItems().clear();

        String sql = "SELECT log_text, log_date FROM progress_logs ORDER BY log_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String entry = rs.getTimestamp("log_date") + " - " + rs.getString("log_text");
                historyListView.getItems().add(entry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}