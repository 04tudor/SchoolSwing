package Controllers;

import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.StudentsDAO;
import Models.DAO.Implementation.StudentsDAOImpl;
import Models.Students;

import java.util.List;

public class StudentsController {
StudentsDAO studentsDAO;

public StudentsController(StudentsDAOImpl studentsDAO){
    this.studentsDAO=studentsDAO;
}

}
