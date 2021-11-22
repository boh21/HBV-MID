package is.hi.hbvmid.Services.Implementation;

import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Persitence.Repositories.UserRepository;
import is.hi.hbvmid.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) { return userRepository.save(user);}

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

    @Override
    public void sendPasswordResetEmail(User user) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("Your MID Password");
        smm.setFrom("markitdownapplication@gmail.com");
        smm.setTo(user.getEmail());
        smm.setText("Your Password is: " + user.getPassword());
        System.out.println("Attempting to Send Email too: " + user.getEmail());
        try {
            javaMailSender.send(smm);
        } catch (MailException e) {
            System.out.println(e);
        }
    }
}
