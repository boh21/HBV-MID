package is.hi.hbvmid.Persitence.Repositories;

import is.hi.hbvmid.Persitence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    void delete(User user);
    List<User> findAll();
    User findByUsername(String username);
}
