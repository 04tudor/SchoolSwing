package Models.DAO.EntityInterfaces;


import Models.DAO.BaseDAO;
import Models.Groups;

public interface GroupsDAO extends BaseDAO<String, Groups> {
    void averageGroup();
}
