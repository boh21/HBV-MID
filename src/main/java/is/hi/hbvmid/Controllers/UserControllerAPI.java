package is.hi.hbvmid.Controllers;

import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserControllerAPI {
    private UserService userService;

    @Autowired
    public UserControllerAPI(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/userAPI", method = RequestMethod.GET)
    public User getUserByName(@RequestParam(value="username") String username) {
        User exists = userService.findByUsername(username);
        if(exists == null){
            System.out.println("User does not exist");
            User failUser = new User(null, null, null);
            return failUser;
        }
        System.out.println("User does exist");
        System.out.println("User: " + exists.getUsername() + " " + exists.getPassword() + " " + exists.getEmail() + " " + exists.getID());
        return exists;
    }

    @RequestMapping(value = "/signupAPI", method = RequestMethod.POST)
    public User signup(@RequestBody User user){
        User exists = userService.findByUsername(user.getUsername());
        System.out.println("User: " + user.getUsername() + " " + user.getPassword() + " " + user.getEmail());
        if(exists == null){
            System.out.println("User does not exist");
            userService.save(user);
            return user;
        }
        System.out.println("Username taken");
        User failUser = new User(null, null, null);
        return failUser;
    }

    @RequestMapping(value = "/changeUserAPI", method = RequestMethod.POST)
    public User changeUser(@RequestBody User user){
        // Verðum að passa að sami user með sama username og áður
        // nema núna bara breytt credentials
        System.out.println("User: " + user.getUsername() + " " + user.getPassword() + " " + user.getEmail() + " " + user.getID());
        //User backUser = userService.findByUserID(user.getID());
        User backUser = userService.findByUsername(user.getUsername());
        backUser.setUsername(user.getUsername());
        backUser.setPassword(user.getPassword());
        backUser.setEmail(user.getEmail());
        userService.save(backUser);
        System.out.println("User: " + backUser.getUsername() + " " + backUser.getPassword() + " " + backUser.getEmail() + " " + backUser.getID());

        return backUser;
    }

    @RequestMapping(value = "/loginAPI", method = RequestMethod.POST)
    public User loginPOST(@RequestBody User user){
        System.out.println(user.getUsername() + " " + user.getPassword());
        User exists = userService.login(user);
        if(exists != null){
            //If user exists and password matches username
            System.out.println("Username and password match");
            return user;
        }
        System.out.println("Username and password do not match");
        User failUser = new User(null, null, null);
        return failUser;
    }


}
