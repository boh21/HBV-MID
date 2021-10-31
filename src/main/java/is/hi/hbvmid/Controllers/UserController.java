package is.hi.hbvmid.Controllers;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class   UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    //End points to add
    //Way to signup (GET, POST)
    //login (GET, POST)
    //loggedin (GET)

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(User user){
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/signup";
        }
        User exists = userService.findByUsername(user.getUsername());
        if(exists == null){
            userService.save(user);
            return "redirect:/";
        }
        else return "redirect:/signup";
    }

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
            return "redirect:/";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/logoff", method = RequestMethod.GET)
    public String loggedoffGET(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        session.setAttribute("LoggedInUser", null);
        model.addAttribute("LoggedInUser", null);
        return "redirect:/";
    }
}
