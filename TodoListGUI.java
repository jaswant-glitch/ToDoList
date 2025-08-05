import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Task {
    String title;
    boolean completed;

    public Task(String title) {
        this.title = title;
        this.completed = false;
    }

    @Override
    public String toString() {
        return (completed ? "✔ " : "❌ ") + title;  // ✅ or ❌
    }
}

public class TodoListGUI extends JFrame {
    private final DefaultListModel<Task> listModel = new DefaultListModel<>();
    private final JList<Task> taskList = new JList<>(listModel);
    private final JTextField taskInput = new JTextField();
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TodoListGUI() {
        setTitle("To-Do List App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        // Set modern font
        Font font = new Font("Segoe UI", Font.PLAIN, 16);
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("List.font", font);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(245, 245, 245));

        // Top input + add button
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBackground(new Color(245, 245, 245));

        taskInput.setPreferredSize(new Dimension(200, 30));
        topPanel.add(taskInput, BorderLayout.CENTER);

        JButton addButton = new JButton("➕ Add Task");
        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> addTask());
        topPanel.add(addButton, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);

        // Center task list
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Bottom buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        bottomPanel.setBackground(new Color(245, 245, 245));

        JButton completeButton = new JButton("✔ Mark Completed");
        completeButton.setBackground(new Color(33, 150, 243));
        completeButton.setForeground(Color.WHITE);
        completeButton.addActionListener(e -> markCompleted());

        JButton deleteButton = new JButton("❌ Delete Task");
        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteTask());

        bottomPanel.add(completeButton);
        bottomPanel.add(deleteButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void addTask() {
        String title = taskInput.getText().trim();
        if (!title.isEmpty()) {
            Task task = new Task(title);
            tasks.add(task);
            listModel.addElement(task);
            taskInput.setText("");
        }
    }

    private void markCompleted() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            Task task = listModel.get(index);
            task.completed = true;
            taskList.repaint();
        }
    }

    private void deleteTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            tasks.remove(index);
            listModel.remove(index);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoListGUI::new);
    }
}
