package is.hi.hbvmid.Services.Implementation;

import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Persitence.Repositories.UserRepository;
import is.hi.hbvmid.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* TODO
- change(use: User): User
 */

@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;

    @Autowired
    UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User login(User user) {
        User doesExist = findByUsername(user.getUsername());
        if(doesExist != null){
            if(doesExist.getPassword().equals(user.getPassword())){
                return doesExist;
            }
        }
        return null;
    }
}
