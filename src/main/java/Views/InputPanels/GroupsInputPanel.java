package Views.InputPanels;


import Models.Groups;

import javax.swing.*;
import java.awt.*;

public class GroupsInputPanel extends JPanel {
    private JTextField codeField;
    private JTextField nameField;

    public GroupsInputPanel() {
        setLayout(new GridLayout(3, 2));


        JLabel codeLabel = new JLabel("Code_Group:");
        codeField = new JTextField(10);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);


        add(codeLabel);
        add(codeField);
        add(nameLabel);
        add(nameField);
    }

    public Groups getGroupFromInput() {
        String code = codeField.getText();
        String name = nameField.getText();
        Groups group = new Groups(1, code, name);
        return group;
    }
}
