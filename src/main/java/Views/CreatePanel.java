package Views;

import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.GradesDAO;
import Models.DAO.EntityInterfaces.GroupsDAO;
import Models.DAO.EntityInterfaces.StudentsDAO;
import Models.DAO.Implementation.GradesDAOImpl;
import Models.DAO.Implementation.GroupsDAOImpl;
import Models.DAO.Implementation.StudentsDAOImpl;
import Models.Grades;
import Models.Groups;
import Models.Students;
import Views.InputPanels.GradesInputPanel;
import Views.InputPanels.GroupsInputPanel;
import Views.InputPanels.StudentsInputPanel;

import javax.swing.*;
import java.awt.*;

public class CreatePanel extends JPanel {
    private final StudentsDAO studentsDAO;
    private final GroupsDAO groupsDAO;
    private final GradesDAO gradesDAO;

    public CreatePanel() {
        studentsDAO = new StudentsDAOImpl();
        groupsDAO = new GroupsDAOImpl();
        gradesDAO = new GradesDAOImpl();

        setLayout(new BorderLayout());

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Students");
        comboBox.addItem("Groups");
        comboBox.addItem("Grades");

        JButton button = new JButton("Create");

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(comboBox);
        topPanel.add(button);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        button.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();
            switch (selectedOption) {
                case "Students":
                    StudentsInputPanel studentsInputPanel = new StudentsInputPanel();
                    int resultStudents = JOptionPane.showConfirmDialog(null, studentsInputPanel,
                            "Enter Student Information", JOptionPane.OK_CANCEL_OPTION);
                    if (resultStudents == JOptionPane.OK_OPTION) {
                        Students student = studentsInputPanel.getStudentFromInput();
                        try {
                            studentsDAO.insert(student);
                        } catch (DaoException ex) {
                            ex.printStackTrace();
                        }
                        textArea.append("Student created: " + student.toString() + "\n");
                    }
                    break;
                case "Groups":
                    GroupsInputPanel groupsInputPanel = new GroupsInputPanel();
                    int resultGroups = JOptionPane.showConfirmDialog(null, groupsInputPanel,
                            "Enter Group Information", JOptionPane.OK_CANCEL_OPTION);
                    if (resultGroups == JOptionPane.OK_OPTION) {
                        Groups group = groupsInputPanel.getGroupFromInput();
                        try {
                            groupsDAO.insert(group);
                        } catch (DaoException ex) {
                            ex.printStackTrace();
                        }
                        textArea.append("Group created: " + group.toString() + "\n");
                    }
                    break;
                case "Grades":
                    GradesInputPanel gradesInputPanel = new GradesInputPanel();
                    int resultGrades = JOptionPane.showConfirmDialog(null, gradesInputPanel,
                            "Enter Grade Information", JOptionPane.OK_CANCEL_OPTION);
                    if (resultGrades == JOptionPane.OK_OPTION) {
                        Grades grades= null;
                        try {
                            grades = gradesInputPanel.getGradesFromInput();
                        } catch (DaoException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            gradesDAO.insert(grades);
                        } catch (DaoException ex) {
                            ex.printStackTrace();
                        }
                        textArea.append("Grade created "+ grades.toString());
                    }
                    break;
            }
      });
    }
}
