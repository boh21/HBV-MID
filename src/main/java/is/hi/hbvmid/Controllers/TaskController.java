package is.hi.hbvmid.Controllers;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService =taskService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(Model model, HttpSession session, String taskName){
        //Call a method in a Service Class
        User sessiUser = (User) session.getAttribute("LoggedInUser");
        //Search for Tasks
        List<Task> allTasks;
        System.out.println(taskName);
        if(taskName != null) {
            allTasks = taskService.findByUserAndName(sessiUser, taskName);
        } else {
            allTasks = taskService.findByUser(sessiUser);
        }
        //Add some data to the Model
        model.addAttribute("tasks", allTasks);
        return "home";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") long id, Model model){
        Task taskToDelete = taskService.findByTaskID(id);
        taskService.delete(taskToDelete);
        return  "redirect:/home";
    }

    @RequestMapping(value = "/addtask", method = RequestMethod.GET)
    public String addTaskForm(Task task){

        return "newTask";
    }

    @RequestMapping(value = "/addtask", method = RequestMethod.POST)
    public String addTask(Task task, BindingResult result, HttpSession session, Model model){
        if(result.hasErrors()){
            return "newTask";
        }
        //TODO: Add default values not given by user.
        User sessUser = (User) session.getAttribute("LoggedInUser");
        task.setOwner(sessUser);
        taskService.save(task);
        return "redirect:/home";
    }

    @RequestMapping(value = "/pomodoro", method = RequestMethod.GET)
    public String pomodoroG(User user){
        return "pomodoro";
    }

    @RequestMapping(value = "/pomodoro", method = RequestMethod.POST)
    public String pomodoroPOST(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/home";
        }
        return "redirect:/home";
    }
}
