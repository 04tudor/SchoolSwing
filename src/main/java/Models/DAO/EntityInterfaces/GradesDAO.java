package Models.DAO.EntityInterfaces;


import Models.DAO.BaseDAO;
import Models.Grades;

public interface GradesDAO extends BaseDAO<String, Grades> {
     void semestrialAverage() ;
     void AdmittedAverages();
}
