package Models.DAO;
import java.util.List;

public interface BaseDAO<K, T> {
    List<T> findAll() throws DaoException;
    T findByCode(K code) throws DaoException;
    void update(K code, T entity) throws DaoException;
    void delete(K code) throws DaoException;
    void insert(T entity) throws DaoException;
}
