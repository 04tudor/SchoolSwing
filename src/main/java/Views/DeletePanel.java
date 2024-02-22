package Views;

import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.GradesDAO;
import Models.DAO.EntityInterfaces.GroupsDAO;
import Models.DAO.EntityInterfaces.StudentsDAO;
import Models.DAO.Implementation.GradesDAOImpl;
import Models.DAO.Implementation.GroupsDAOImpl;
import Models.DAO.Implementation.StudentsDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePanel extends JPanel {
    private JComboBox<String> optionComboBox;
    private JTextField codeField;
    private JButton deleteButton;
    private final StudentsDAO studentsDAO;
    private final GroupsDAO groupsDAO;
    private final GradesDAO gradesDAO;

    public DeletePanel() {
        studentsDAO = new StudentsDAOImpl();
        groupsDAO = new GroupsDAOImpl();
        gradesDAO = new GradesDAOImpl();

        setLayout(new BorderLayout());

        JLabel optionLabel = new JLabel("Select Option: ");
        String[] options = {"Student", "Group", "Grade"};
        optionComboBox = new JComboBox<>(options);

        JLabel codeLabel = new JLabel("Code: ");
        codeField = new JTextField(10);

        deleteButton = new JButton("Delete");

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(optionLabel);
        topPanel.add(optionComboBox);
        topPanel.add(codeLabel);
        topPanel.add(codeField);
        topPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerformed();
            }
        });
    }

    private void deleteButtonActionPerformed() {
        String selectedOption = (String) optionComboBox.getSelectedItem();
        String code = codeField.getText();

        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a code for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            switch (selectedOption) {
                case "Student":
                    studentsDAO.delete(code);
                    deleteEntity(code, "Student");
                    break;
                case "Group":
                    groupsDAO.delete(code);
                    deleteEntity(code, "Group");
                    break;
                case "Grade":
                    gradesDAO.delete(code);
                    deleteEntity(code, "Grade");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option selected.");
                    break;
            }
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting entry: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEntity(String code, String entityName) {
        JOptionPane.showMessageDialog(null, entityName + " with code " + code + " deleted successfully.");
    }
}
