package is.hi.hbvmid.Controllers;

import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserControllerAPI {
    private UserService userService;

    @Autowired
    public UserControllerAPI(UserService userService){
        this.userService = userService;
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
        else return null;
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
        return null;
    }

    /*

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "login";
        }
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "redirect:/home";
        }
        return "redirect:/requestpassword";
    }

    @RequestMapping(value = "/requestpassword", method = RequestMethod.GET)
    public String requestpasswordGET(User user){
        return "requestpassword";
    }

    @RequestMapping(value = "/requestpassword", method = RequestMethod.POST)
    public String requestpasswordPOST(User user, BindingResult result){
        User exists = userService.findByUsername(user.getUsername());
        System.out.println(exists);
        if(result.hasErrors()){
            System.out.println("Result Had Errors!");
            return "requestpassword";
        }
        if(exists != null) {
            userService.sendPasswordResetEmail(exists);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("LoggedInUser", sessionUser);
            return "redirect:/home";
        }
        return "redirect:/";
    }

    //Viðbót 30/10 15:32
    @RequestMapping(value = "/manageaccount", method = RequestMethod.GET)
    public String manageaccountForm(User user, HttpSession session, Model model){
        User sessiUser = (User) session.getAttribute("LoggedInUser");
        if (sessiUser != null) {
            model.addAttribute("LoggedInUser", sessiUser);
            return "accountManagement";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/manageaccount", method = RequestMethod.POST)
    public String manageaccount(User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()) {
            return "redirect:/manageaccount";
        }
        String un = user.getUsername();
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        User exists = userService.findByUsername(un);
        if(exists == null || un.equals(sessionUser.getUsername())){
            String em = user.getEmail();
            String pw = user.getPassword();
            sessionUser.setEmail(em);
            sessionUser.setUsername(un);
            sessionUser.setPassword(pw);
            userService.save(sessionUser);
            return "redirect:/home";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/logoff", method = RequestMethod.GET)
    public String loggedoffGET(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        session.setAttribute("LoggedInUser", null);
        model.addAttribute("LoggedInUser", null);
        return "redirect:/";
    }*/
}
