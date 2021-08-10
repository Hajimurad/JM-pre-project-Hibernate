package service;

import dao.UserDaoHibernateImpl;
import model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    //private final UserDaoJDBCImpl daoReuse = new UserDaoJDBCImpl();

    private final UserDaoHibernateImpl daoReuse = new UserDaoHibernateImpl();


    public void createUsersTable() { daoReuse.createUsersTable();
    }

    public void dropUsersTable() { daoReuse.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) { daoReuse.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) { daoReuse.removeUserById(id);
    }

    public List<User> getAllUsers() { return daoReuse.getAllUsers();
    }

    public void cleanUsersTable() { daoReuse.cleanUsersTable();
    }
}
