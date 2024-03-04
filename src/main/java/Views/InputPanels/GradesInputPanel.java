package Views.InputPanels;

import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.StudentsDAO;
import Models.DAO.Implementation.StudentsDAOImpl;
import Models.Grades;
import Models.Students;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GradesInputPanel extends JPanel {
    private JTextField codeField;
    private JTextField semesterGradeField;
    private JComboBox<String> studentIdComboBox;
    private final StudentsDAO studentsDAO;
    public GradesInputPanel() {
        studentsDAO=new StudentsDAOImpl();
        setLayout(new GridLayout(4, 2));

        JLabel codeLabel = new JLabel("Code:");
        codeField = new JTextField(10);

        JLabel studentIdLabel = new JLabel("Student_ID:");
        studentIdComboBox = new JComboBox<>();

        JLabel semesterGradeLabel = new JLabel("Semester Grade:");
        semesterGradeField = new JTextField(10);

        add(codeLabel);
        add(codeField);
        add(studentIdLabel);
        add(studentIdComboBox);
        add(semesterGradeLabel);
        add(semesterGradeField);
        populateStudentID();
    }
    private void populateStudentID() {
        try {
            List<Students> students = studentsDAO.findAll();
            for (Students students1 : students) {
                studentIdComboBox.addItem(students1.getCode_Student());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public Grades getGradesFromInput() throws DaoException {
        String code = codeField.getText();
        String codeStudent= (String) studentIdComboBox.getSelectedItem();
        Students st=studentsDAO.findByCode(codeStudent);
        double semesterGrade = Double.parseDouble(semesterGradeField.getText());

        Grades grades = new Grades();
        grades.setCode_Grade(code);
        grades.setStudent(st);
        grades.setSemester_Grade(semesterGrade);

        return grades;
    }
}
