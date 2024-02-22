package Views;

import Models.DAO.EntityInterfaces.GradesDAO;
import Models.DAO.EntityInterfaces.GroupsDAO;
import Models.DAO.EntityInterfaces.StudentsDAO;
import Models.DAO.Implementation.GradesDAOImpl;
import Models.DAO.Implementation.GroupsDAOImpl;
import Models.DAO.Implementation.StudentsDAOImpl;
import Models.Grades;
import Models.Groups;
import Models.Students;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RetrievePanel extends JPanel {
    private final JTable table;
    private final StudentsDAO studentsDAO;
    private final GroupsDAO groupsDAO;
    private final GradesDAO gradesDAO;

    public RetrievePanel() {
        setLayout(new BorderLayout());
        studentsDAO = new StudentsDAOImpl();
        gradesDAO=new GradesDAOImpl();
        groupsDAO=new GroupsDAOImpl();
        JLabel tableLabel = new JLabel("Select Table: ");
        String[] tableOptions = {"Students", "Groups", "Grades"};
        JComboBox<String> tableComboBox = new JComboBox<>(tableOptions);

        JButton retrieveButton = new JButton("Retrieve");

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(tableLabel);
        topPanel.add(tableComboBox);
        topPanel.add(retrieveButton);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        retrieveButton.addActionListener(e -> {
            String selectedTable = (String) tableComboBox.getSelectedItem();
            if (selectedTable.equals("Students")) {
                retrieveStudentsData();
            }
            if (selectedTable.equals("Groups")) {
                retrieveGroupsData();
            }
            if (selectedTable.equals("Grades")) {
                retrieveGradesData();
            }        });
    }

    private void retrieveStudentsData() {
        try {
            List<Students> studentsList = studentsDAO.findAll();
            displayStudentsData(studentsList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void retrieveGroupsData() {
        try {
            List<Groups> groupsList = groupsDAO.findAll();
            displayGroupsData(groupsList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void retrieveGradesData() {
        try {
            List<Grades> grades = gradesDAO.findAll();
            displayGradesData(grades);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void displayStudentsData(List<Students> studentsList) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Code");
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("Group_ID");
        model.addColumn("Group");

        for (Students student : studentsList) {
            Object[] rowData = {
                    student.getId(),
                    student.getCode_Student(),
                    student.getName(),
                    student.getSurname(),
                    student.getGroups().getId(),
                    student.getGroups().getName()
            };
            model.addRow(rowData);
        }

        table.setModel(model);
    }
    private void displayGroupsData(List<Groups> groupsList) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Group_ID");
        model.addColumn("Name");


        for (Groups groups : groupsList) {
            Object[] rowData = {
                    groups.getId(),
                    groups.getCode_Group(),
                    groups.getName(),

            };
            model.addRow(rowData);
        }

        table.setModel(model);
    }
    private void displayGradesData(List<Grades> gradesList) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Group_ID");
        model.addColumn("Semester Grade");
        model.addColumn("Code Student");
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("Group");

        for (Grades grades : gradesList) {
            Object[] rowData = {
                    grades.getId(),
                    grades.getCode_Grade(),
                    grades.getSemester_Grade(),
                    grades.getStudents().getCode_Student(),
                    grades.getStudents().getName(),
                    grades.getStudents().getSurname(),
                    grades.getStudents().getGroups().getName(),

            };
            model.addRow(rowData);
        }

        table.setModel(model);
    }
}
