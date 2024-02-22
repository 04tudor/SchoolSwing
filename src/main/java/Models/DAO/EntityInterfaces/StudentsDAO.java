package Models.DAO.EntityInterfaces;



import Models.DAO.BaseDAO;
import Models.Students;

import java.util.List;

public interface StudentsDAO extends BaseDAO<String, Students> {
     void inserttabledeleted(Students students);
      List<Students> restant();
     void studentsDescendingByGrade();

}
