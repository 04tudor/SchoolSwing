package Views.EditPanels;



import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.GroupsDAO;
import Models.DAO.EntityInterfaces.StudentsDAO;
import Models.DAO.Implementation.GroupsDAOImpl;
import Models.DAO.Implementation.StudentsDAOImpl;
import Models.Groups;
import Models.Students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentsEditPanel extends JPanel {
    private Students students;
    private JTextField codeField;
    private JTextField nameField;
    private JTextField surnameField;
    private JButton button;
    private JComboBox<String> groupComboBox;
    private final GroupsDAO groupsDAO;
    private final StudentsDAO studentsDAO;
    public StudentsEditPanel(Students students){
        this.students=students;
        studentsDAO=new StudentsDAOImpl();
        groupsDAO=new GroupsDAOImpl();

        setLayout(new GridLayout(5, 2));

        JLabel codeLabel = new JLabel("Code_Student:");
        codeField = new JTextField(10);
        codeField.setText(students.getCode_Student());

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);
        nameField.setText(students.getName());
        JLabel surnameLabel = new JLabel("Surname:");
        surnameField = new JTextField(10);
        surnameField.setText(students.getSurname());
        JLabel groupLabel = new JLabel("Group:");
        groupComboBox = new JComboBox<>();
        button=new JButton("Update");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        add(codeLabel);
        add(codeField);
        add(nameLabel);
        add(nameField);
        add(surnameLabel);
        add(surnameField);
        add(groupLabel);
        add(groupComboBox);
        populateGroupComboBox();
    }
    private void populateGroupComboBox() {
        try {
            List<Groups> groups = groupsDAO.findAll();
            for (Groups group : groups) {
                groupComboBox.addItem(group.getName());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    private Groups findGroupByName(String name) {
        try {
            List<Groups> groups = groupsDAO.findAll();
            for (Groups i : groups) {
                if (i.getName().equals(name))return i;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Students getupdatedStudent(){
        String iidname=(String) groupComboBox.getSelectedItem();
        Groups groups=findGroupByName(iidname);
        Students students=new Students(1,codeField.getText(),nameField.getText(),surnameField.getText(),groups);
        return students;
    }
    private void updateStudent() {
        String groupName = (String) groupComboBox.getSelectedItem();
        Groups group = findGroupByName(groupName);
        students.setCode_Student(codeField.getText());
        students.setName(nameField.getText());
        students.setSurname(surnameField.getText());
        students.setGroups(group);

        try {
            studentsDAO.update(codeField.getText(),students);
            JOptionPane.showMessageDialog(null, "Student updated successfully");
        } catch (DaoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
