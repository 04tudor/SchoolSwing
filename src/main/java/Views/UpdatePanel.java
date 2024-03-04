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
import Views.EditPanels.GradesEditPanel;
import Views.EditPanels.GroupsEditPanel;
import Views.EditPanels.StudentsEditPanel;

import javax.swing.*;
import java.awt.*;

public class UpdatePanel extends JPanel {
    private final StudentsDAO studentsDAO;
    private final GroupsDAO groupsDAO;
    private final GradesDAO gradesDAO;
    public UpdatePanel() {
        gradesDAO=new GradesDAOImpl();
        groupsDAO=new GroupsDAOImpl();
        studentsDAO=new StudentsDAOImpl();
        setLayout(new BorderLayout());

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Students");
        comboBox.addItem("Groups");
        comboBox.addItem("Grades");

        JButton button = new JButton("Update");

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
                    try {
                        updateStudent(textArea);
                    } catch (DaoException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "Groups":
                    try {
                        updateGroup(textArea);
                    } catch (DaoException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "Grades":
                    try {
                        updateGrade(textArea);
                    } catch (DaoException ex) {
                        ex.printStackTrace();
                    }
                    break;
            }
        });
    }

    private void updateStudent(JTextArea textArea) throws DaoException {
        String codeStudent = JOptionPane.showInputDialog("Enter Code Student:");
        if (codeStudent != null && !codeStudent.isEmpty()) {
            Students student = fetchStudentByCode(codeStudent);
            if (student != null) {
                StudentsEditPanel studentsEditPanel = new StudentsEditPanel(student);
                int result = JOptionPane.showConfirmDialog(null, studentsEditPanel,
                        "Update Student Information", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Students updatedStudent = studentsEditPanel.getUpdatedStudent();
                    textArea.append("Student updated: " + updatedStudent.toString() + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Student not found!");
            }
        }
    }


    private void updateGroup(JTextArea textArea) throws DaoException {
        String codeGroup = JOptionPane.showInputDialog("Enter Code Group:");
        if (codeGroup != null && !codeGroup.isEmpty()) {
            Groups group = fetchGroupByCode(codeGroup);

            if (group != null) {
                GroupsEditPanel groupsinput=new GroupsEditPanel(group);

                int resultStudents = JOptionPane.showConfirmDialog(null, groupsinput,
                        "Update Group Information", JOptionPane.OK_CANCEL_OPTION);
                if (resultStudents == JOptionPane.OK_OPTION) {
                    Groups groups=groupsinput.getUpdatedGroup();
                    textArea.append("Group updated: " + groups.toString() + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Group not found!");
            }
        }
    }

    private void updateGrade(JTextArea textArea) throws DaoException {
        String codeGrade = JOptionPane.showInputDialog("Enter Code Grade:");
        if (codeGrade != null && !codeGrade.isEmpty()) {
            Grades grades = fetchGradeByCode(codeGrade);

            if (grades != null) {
                GradesEditPanel gradesinput=new GradesEditPanel(grades);

                int resultStudents = JOptionPane.showConfirmDialog(null, gradesinput,
                        "Update Grade Information", JOptionPane.OK_CANCEL_OPTION);
                if (resultStudents == JOptionPane.OK_OPTION) {
                    textArea.append("Grade updated: " + gradesinput.toString() + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Grade not found!");
            }
        }
    }

    private Students fetchStudentByCode(String codeStudent) throws DaoException {
        return studentsDAO.findByCode(codeStudent);

    }
    private Groups fetchGroupByCode(String Code) throws DaoException {

        return groupsDAO.findByCode(Code);
    }
    private Grades fetchGradeByCode(String Code) throws DaoException {

        return gradesDAO.findByCode(Code);
    }

}
