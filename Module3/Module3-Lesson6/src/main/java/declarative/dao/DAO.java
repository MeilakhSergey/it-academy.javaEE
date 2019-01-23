package declarative.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.sql.SQLException;

public interface DAO<T> {
    T add (T t);
    T update(T t);
    T get(Serializable id);
    void delete(Serializable id);

    void getExcept(Serializable id) throws SQLException;
}
