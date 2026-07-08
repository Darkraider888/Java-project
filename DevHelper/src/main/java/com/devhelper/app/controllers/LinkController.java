package com.devhelper.app.controllers;

import com.devhelper.app.DatabaseConnection;
import com.devhelper.app.models.LinkItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.sql.*;

public class LinkController {

    @FXML private ListView<LinkItem> linkListView;
    @FXML private TextField linkTitleField;
    @FXML private TextField linkUrlField;

    @FXML
    public void initialize() {
        loadLinks();
    }

    private void loadLinks() {
        linkListView.getItems().clear();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM links")) {

            while (rs.next()) {
                LinkItem link = new LinkItem(rs.getString("title"), rs.getString("url"));
                linkListView.getItems().add(link);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Called by the "＋ Add Link" button (onAction="#handleAddLink")
    @FXML
    private void handleAddLink() {
        String title = linkTitleField.getText();
        String url = linkUrlField.getText();

        if (title == null || title.trim().isEmpty() || url == null || url.trim().isEmpty()) {
            return;
        }

        String sql = "INSERT INTO links (title, url) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, url);
            ps.executeUpdate();

            linkTitleField.clear();
            linkUrlField.clear();
            loadLinks();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}