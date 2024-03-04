package Models.DAO.Implementation;




import Models.DAO.DaoException;
import Models.DAO.EntityInterfaces.GroupsDAO;
import Models.Grades;
import Models.Groups;
import Models.Hibernate.HibernateUtil;
import Models.Students;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GroupsDAOImpl implements GroupsDAO {
    private final static String HQL_SELECT_ALL_FROM_Groups = "FROM Groups";
    private final SessionFactory sessionFactory;

    public GroupsDAOImpl(){
        this.sessionFactory = HibernateUtil.getSessionFactory();

    }

    @Override
    public List<Groups> findAll() throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Query<Groups> query = session.createQuery(HQL_SELECT_ALL_FROM_Groups, Groups.class);
            return query.list();
        }
    }

    @Override
    public Groups findByCode(String code) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            return session.bySimpleNaturalId(Groups.class)
                    .load(code);
        }
    }

    @Override
    public void update(String code, Groups entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Groups groupToUpdate = session.bySimpleNaturalId(Groups.class).load(code);
            groupToUpdate.setCode_Group(entity.getCode_Group());
            groupToUpdate.setName(entity.getName());

            try {
                session.merge(groupToUpdate);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
                throw new DaoException("Error updating group: " + e.getMessage(), e);
            }
        } catch (HibernateException e) {
            throw new DaoException("Error updating group: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String code) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Groups groups = session.bySimpleNaturalId(Groups.class)
                    .load(code);
            if (groups != null) {
                session.beginTransaction();
                session.evict(groups);
                session.remove(groups);
                session.getTransaction().commit();
            } else {
                throw new DaoException("Group with code " + code + " does not exist");
            }
        } catch (HibernateException e) {
            throw new DaoException("Error deleting group with code: " + code, e);
        }
    }


    @Override
    public void insert(Groups entity) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new DaoException("Error inserting group: " + e.getMessage(), e);
        }
    }

    @Override
    public void averageGroup() {

    }
}
