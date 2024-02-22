package Views.EditPanels;



import Models.Groups;
import Models.Students;

import javax.swing.*;
import java.awt.*;

public class StudentsEditPanel extends JPanel {
    private Students students;
    private JTextField codeField;
    private JTextField nameField;
    private JTextField surnameField;
    private JComboBox<Groups> groupComboBox;
    public StudentsEditPanel(Students students){
        this.students=students;

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

        add(codeLabel);
        add(codeField);
        add(nameLabel);
        add(nameField);
        add(surnameLabel);
        add(surnameField);
        add(groupLabel);
        add(groupComboBox);
    }

}
