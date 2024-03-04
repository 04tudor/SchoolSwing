package Views.InputPanels;

import Models.DAO.Implementation.GroupsDAOImpl;
import Models.Groups;
import Models.Students;
import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.GroupsDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentsInputPanel extends JPanel {
    private JTextField codeField;
    private JTextField nameField;
    private JTextField surnameField;
    private JComboBox<String> groupComboBox;

    private final GroupsDAO groupsDAO;

    public StudentsInputPanel() {
        groupsDAO = new GroupsDAOImpl();

        setLayout(new GridLayout(5, 2));

        JLabel codeLabel = new JLabel("Code_Student:");
        codeField = new JTextField(10);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);

        JLabel surnameLabel = new JLabel("Surname:");
        surnameField = new JTextField(10);

        JLabel groupLabel = new JLabel("Group:");
        groupComboBox = new JComboBox<>();

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
    public Students getStudentFromInput() {
        String code = codeField.getText();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String iidname=(String) groupComboBox.getSelectedItem();



        Groups selectedGroup = findGroupByName(iidname);

        Students student = new Students();
        student.setCode_Student(code);
        student.setName(name);
        student.setSurname(surname);
        student.setGroup(selectedGroup);

        return student;
    }
}
