package Views.EditPanels;

import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.StudentsDAO;
import Models.DAO.Implementation.StudentsDAOImpl;
import Models.Grades;
import Models.Students;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GradesEditPanel extends JPanel {
    private JTextField codeField;
    private JTextField semesterGradeField;
    private JComboBox<String> studentIdComboBox;
    private Grades grades;
    private final StudentsDAO studentsDAO;
    public GradesEditPanel(Grades grades){
        this.grades=grades;
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
    private void updateGrade() throws DaoException {
        String stCode = (String) studentIdComboBox.getSelectedItem();
        Students students = studentsDAO.findByCode(stCode);
        grades.setStudent(students);
        grades.setSemester_Grade(Double.parseDouble(semesterGradeField.getText()));
        grades.setCode_Grade(codeField.getText());

        try {
            studentsDAO.update(codeField.getText(),students);
            JOptionPane.showMessageDialog(null, "Grade updated successfully");
        } catch (DaoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating Grade: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
