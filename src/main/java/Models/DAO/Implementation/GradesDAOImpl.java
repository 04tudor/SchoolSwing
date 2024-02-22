package Models.DAO.Implementation;


import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.GradesDAO;
import Models.Grades;
import Models.Hibernate.HibernateUtil;
import Models.Students;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class GradesDAOImpl implements GradesDAO {
    private final static String HQL_SELECT_ALL_FROM_Grades = "FROM Grades";

    private final SessionFactory sessionFactory;
    public GradesDAOImpl(){
        this.sessionFactory = HibernateUtil.getSessionFactory();

    }

    @Override
    public List<Grades> findAll() throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Query<Grades> query = session.createQuery(HQL_SELECT_ALL_FROM_Grades, Grades.class);
            return query.list();
        }
    }

    @Override
    public Grades findByCode(String code) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Grades.class)
                    .load(code);
        }
    }

    @Override
    public void update(String code, Grades entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Grades grades = session.bySimpleNaturalId(Grades.class)
                    .load(code);
            entity.getStudents().setId(grades.getStudents().getId());

            try {
                grades.setCode_Grade(entity.getCode_Grade());
                grades.setSemester_Grade(entity.getSemester_Grade());
                grades.setStudents(entity.getStudents());

                session.merge(grades);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(String code) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Grades grades = session.bySimpleNaturalId(Grades.class)
                    .load(code);
            session.beginTransaction();
            session.evict(grades);
            session.remove(grades);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Grades entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void semestrialAverage() {

    }

    @Override
    public void AdmittedAverages() {

    }
}
