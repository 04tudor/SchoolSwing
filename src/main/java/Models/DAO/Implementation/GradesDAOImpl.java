package Models.DAO.Implementation;

import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.GradesDAO;
import Models.Grades;
import Models.Hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class GradesDAOImpl implements GradesDAO {
    private final static String HQL_SELECT_ALL_FROM_GRADES = "FROM Grades";

    private final SessionFactory sessionFactory;

    public GradesDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Grades> findAll() throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Query<Grades> query = session.createQuery(HQL_SELECT_ALL_FROM_GRADES, Grades.class);
            return query.list();
        } catch (HibernateException e) {
            throw new DaoException("Error finding all grades: " + e.getMessage(), e);
        }
    }

    @Override
    public Grades findByCode(String code) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Grades.class).load(code);
        } catch (HibernateException e) {
            throw new DaoException("Error finding grade by code: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(String code, Grades entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Grades gradeToUpdate = session.bySimpleNaturalId(Grades.class).load(code);
            entity.getStudent().setId(gradeToUpdate.getStudent().getId());
            gradeToUpdate.setCode_Grade(entity.getCode_Grade());
            gradeToUpdate.setSemester_Grade(entity.getSemester_Grade());
            gradeToUpdate.setStudent(entity.getStudent());
            try {
                session.merge(gradeToUpdate);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
                throw new DaoException("Error updating grade: " + e.getMessage(), e);
            }
        } catch (HibernateException e) {
            throw new DaoException("Error updating grade: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String code) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Grades gradeToDelete = session.bySimpleNaturalId(Grades.class).load(code);
            if (gradeToDelete != null) {
                session.beginTransaction();
                session.delete(gradeToDelete);
                session.getTransaction().commit();
            } else {
                throw new DaoException("Grade with code " + code + " does not exist");
            }
        } catch (HibernateException e) {
            throw new DaoException("Error deleting grade with code: " + code, e);
        }
    }

    @Override
    public void insert(Grades entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new DaoException("Error inserting grade: " + e.getMessage(), e);
        }
    }

    @Override
    public void semestrialAverage() {

    }

    @Override
    public void AdmittedAverages() {
    }
}
