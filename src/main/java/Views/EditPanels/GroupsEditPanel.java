package Views.EditPanels;

import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.GroupsDAO;
import Models.DAO.Implementation.GroupsDAOImpl;
import Models.Groups;
import Models.Students;

import javax.swing.*;
import java.awt.*;

public class GroupsEditPanel extends JPanel {
    private JTextField codeField;
    private JTextField nameField;
    private Groups groups;
    private Groups updatedGroup;

    private final GroupsDAO groupsDAO;

    public GroupsEditPanel(Groups groups) {
        this.groups = groups;
        groupsDAO = new GroupsDAOImpl();
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2));

        JLabel codeLabel = new JLabel("Code_Group:");
        codeField = new JTextField(10);
        codeField.setText(groups.getCode_Group());

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);
        nameField.setText(groups.getName());

        JButton button = new JButton("Update");
        button.addActionListener(e -> updateGroup());

        add(codeLabel);
        add(codeField);
        add(nameLabel);
        add(nameField);
    }

    public Groups getUpdatedGroup(){
        updateGroup();
        return  groups;
    }
    private void updateGroup() {
        groups.setCode_Group(codeField.getText());
        groups.setName(nameField.getText());

        updatedGroup=new Groups();
        updatedGroup.setCode_Group(groups.getCode_Group());
        updatedGroup.setName(groups.getName());
        try {
            groupsDAO.update(codeField.getText(), updatedGroup);
            JOptionPane.showMessageDialog(null, "Group updated successfully");
        } catch (DaoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating Group: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
