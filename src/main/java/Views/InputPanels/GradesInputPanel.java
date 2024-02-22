package Views.InputPanels;

import Models.Grades;
import Models.Students;

import javax.swing.*;
import java.awt.*;

public class GradesInputPanel extends JPanel {
    private JTextField codeField;
    private JTextField studentIdField;
    private JTextField semesterGradeField;

    public GradesInputPanel() {
        setLayout(new GridLayout(4, 2));

        JLabel codeLabel = new JLabel("Code:");
        codeField = new JTextField(10);

        JLabel studentIdLabel = new JLabel("Student_ID:");
        studentIdField = new JTextField(10);

        JLabel semesterGradeLabel = new JLabel("Semester Grade:");
        semesterGradeField = new JTextField(10);

        add(codeLabel);
        add(codeField);
        add(studentIdLabel);
        add(studentIdField);
        add(semesterGradeLabel);
        add(semesterGradeField);
    }

    public Grades getGradesFromInput() {
        String code = codeField.getText();
        int studentId = Integer.parseInt(studentIdField.getText());
        double semesterGrade = Double.parseDouble(semesterGradeField.getText());

        Students student = new Students();
        student.setId(studentId);

        Grades grades = new Grades();
        grades.setCode_Grade(code);
        grades.setStudents(student);
        grades.setSemester_Grade(semesterGrade);

        return grades;
    }
}
