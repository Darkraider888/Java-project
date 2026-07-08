package com.devhelper.app.controllers;

import com.devhelper.app.DatabaseConnection;
import com.devhelper.app.models.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.ArrayList;

public class TaskController {

    @FXML
    private ListView<CheckBox> taskListView;


    @FXML
    private TextField newTaskField;

    private ArrayList<Task> taskList = new ArrayList<>();

    @FXML
    public void initialize() {
        loadTasksFromDatabase();
    }

    private void loadTasksFromDatabase() {
        taskList.clear();
        taskListView.getItems().clear();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tasks")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("task_name");
                boolean done = rs.getBoolean("is_done");

                Task task = new Task(id, name, done);
                taskList.add(task);

                CheckBox cb = new CheckBox(task.getName());
                cb.setSelected(task.isDone());
                cb.setOnAction(e -> updateTaskStatus(task.getId(), cb.isSelected()));

                taskListView.getItems().add(cb);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleAddTask() {
        String taskName = newTaskField.getText();


        if (taskName == null || taskName.trim().isEmpty()) {
            return;
        }

        String sql = "INSERT INTO tasks (task_name) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, taskName);
            ps.executeUpdate();

            newTaskField.clear();
            loadTasksFromDatabase(); // refresh the list so the new task shows up

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTaskStatus(int taskId, boolean isDone) {
        String sql = "UPDATE tasks SET is_done = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, isDone);
            ps.setInt(2, taskId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}