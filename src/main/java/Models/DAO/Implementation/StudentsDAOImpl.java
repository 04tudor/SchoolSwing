package Models.DAO.Implementation;

import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.StudentsDAO;
import Models.Grades;
import Models.Hibernate.HibernateUtil;
import Models.Students;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class StudentsDAOImpl implements StudentsDAO {
    private final static String HQL_SELECT_ALL_FROM_Students = "FROM Students";

    private final SessionFactory sessionFactory;

    public StudentsDAOImpl(){
        this.sessionFactory = HibernateUtil.getSessionFactory();

    }
    @Override
    public List<Students> findAll() throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Query<Students> query = session.createQuery(HQL_SELECT_ALL_FROM_Students, Students.class);
            return query.list();
        }    }

    @Override
    public Students findByCode(String code) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Students.class)
                    .load(code);
        }
    }
    @Override
    public void update(String code, Students entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Students studentToUpdate = session.bySimpleNaturalId(Students.class).load(code);
            if (studentToUpdate != null) {
                if (entity != null) {
                    studentToUpdate.setName(entity.getName());
                    studentToUpdate.setSurname(entity.getSurname());
                    studentToUpdate.setGroup(entity.getGroup());
                    session.merge(studentToUpdate);
                    session.getTransaction().commit();
                } else {
                    throw new DaoException("Error: Cannot update student with null entity");
                }
            } else {
                throw new DaoException("Error: Student with code " + code + " does not exist");
            }
        } catch (HibernateException e) {
            throw new DaoException("Error updating student: " + e.getMessage(), e);
        }
    }



    @Override
    public void delete(String code) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Students students = session.bySimpleNaturalId(Students.class)
                    .load(code);
            if (students != null) {
                session.beginTransaction();
                session.evict(students);
                session.remove(students);
                session.getTransaction().commit();
            } else {
                throw new DaoException("Student with code " + code + " does not exist");
            }
        } catch (HibernateException e) {
            throw new DaoException("Error deleting student with code: " + code, e);
        }
    }


    @Override
    public void insert(Students entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void inserttabledeleted(Students students) {

    }

    @Override
    public List<Students> restant() {
        return null;
    }

    @Override
    public void studentsDescendingByGrade() {

    }
}
