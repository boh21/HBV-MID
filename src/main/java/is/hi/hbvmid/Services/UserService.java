package is.hi.hbvmid.Services;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUserID(long ID);
    User save(User user);
    void delete(User user);
    User findByUsername(String username);
    User login(User user);
    void sendPasswordResetEmail(User user);
}
