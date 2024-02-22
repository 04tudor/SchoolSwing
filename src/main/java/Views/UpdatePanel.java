package Views;

import Models.Grades;
import Models.Groups;
import Models.Students;

import javax.swing.*;
import java.awt.*;

public class UpdatePanel extends JPanel {
    public UpdatePanel() {
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
                    updateStudent(textArea);
                    break;
                case "Groups":
                    updateGroup(textArea);
                    break;
                case "Grades":
                    updateGrade(textArea);
                    break;
            }
        });
    }

    private void updateStudent(JTextArea textArea) {
        String codeStudent = JOptionPane.showInputDialog("Enter Code Student:");
        if (codeStudent != null && !codeStudent.isEmpty()) {
            Students student = fetchStudentByCode(codeStudent);

            if (student != null) {
                Students studentsInputPanel = new Students();
                studentsInputPanel.setCode_Student(student.getCode_Student());
                studentsInputPanel.setName(student.getName());
                studentsInputPanel.setSurname(student.getSurname());
                studentsInputPanel.setGroups(student.getGroups());

                int resultStudents = JOptionPane.showConfirmDialog(null, studentsInputPanel,
                        "Update Student Information", JOptionPane.OK_CANCEL_OPTION);
                if (resultStudents == JOptionPane.OK_OPTION) {
                    textArea.append("Student updated: " + studentsInputPanel.toString() + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Student not found!");
            }
        }
    }

    private void updateGroup(JTextArea textArea) {
        String codeGroup = JOptionPane.showInputDialog("Enter Code Group:");
        if (codeGroup != null && !codeGroup.isEmpty()) {
            Groups group = fetchGroupByCode(codeGroup);

            if (group != null) {
                Groups groupsinput = new Groups();
                groupsinput.setCode_Group(group.getCode_Group());
                groupsinput.setName(group.getName());

                int resultStudents = JOptionPane.showConfirmDialog(null, groupsinput,
                        "Update Group Information", JOptionPane.OK_CANCEL_OPTION);
                if (resultStudents == JOptionPane.OK_OPTION) {
                    textArea.append("Group updated: " + groupsinput.toString() + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Group not found!");
            }
        }
    }

    private void updateGrade(JTextArea textArea) {
        String codeGrade = JOptionPane.showInputDialog("Enter Code Grade:");
        if (codeGrade != null && !codeGrade.isEmpty()) {
            Grades grades = fetchGradeByCode(codeGrade);

            if (grades != null) {
                Grades gradesinput = new Grades();
                gradesinput.setCode_Grade(grades.getCode_Grade());
                gradesinput.setSemester_Grade(grades.getSemester_Grade());
                gradesinput.setStudents(grades.getStudents());

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

    private Students fetchStudentByCode(String codeStudent) {

        Students student = new Students();
        student.setCode_Student(codeStudent);
        student.setName("John");
        student.setSurname("Doe");
        return student;
    }
    private Groups fetchGroupByCode(String Code) {
        Groups groups=new Groups();
        groups.setName("");
        groups.setCode_Group("");
        return groups;
    }
    private Grades fetchGradeByCode(String Code) {
        Grades grades=new Grades();
        Students students=new Students();
       grades.setStudents(students);
        grades.setCode_Grade("");
        grades.setSemester_Grade(8);

        return grades;
    }

}
