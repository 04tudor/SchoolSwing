package Views.InputPanels;



import Models.Groups;
import Models.Students;

import javax.swing.*;
import java.awt.*;

public class StudentsInputPanel extends JPanel {
    private JTextField codeField;
    private JTextField nameField;
    private JTextField surnameField;
    private JComboBox<Groups> groupComboBox;

    public StudentsInputPanel() {
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
    }

    public Students getStudentFromInput() {
        String code = codeField.getText();
        String name = nameField.getText();
        String surname = surnameField.getText();
        Groups selectedGroup = (Groups) groupComboBox.getSelectedItem();

        Students student = new Students();
        student.setCode_Student(code);
        student.setName(name);
        student.setSurname(surname);
        student.setGroups(selectedGroup);

        return student;
    }
}
