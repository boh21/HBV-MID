package is.hi.hbvmid.Controllers;

import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class StatsController {
    private StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }


    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public String statsPage(Model model, HttpSession session){
        //Call a method in a Service Class
        User sessiUser = (User) session.getAttribute("LoggedInUser");
        if (sessiUser != null) {
            int taskCount = statsService.countTasks(sessiUser);
            //Add some data to the Model
            model.addAttribute("stats", taskCount);
            return "stat";
        }
        else return "redirect:/";
    }
}
