package is.hi.hbvmid.Controllers;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Persitence.Util.TaskCategory;
import is.hi.hbvmid.Persitence.Util.TaskStatus;
import is.hi.hbvmid.Services.StatsService;
import is.hi.hbvmid.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(Model model, HttpSession session, String name, Boolean priority, TaskCategory category, TaskStatus status){
        //Call a method in a Service Class
        User sessiUser = (User) session.getAttribute("LoggedInUser");
        if (sessiUser != null) {
            //Search for Tasks
            List<Task> tasks = taskService.findTask(sessiUser, name, priority, category, status);
            System.out.println("Tasks: " + tasks);
            //Add some data to the Model
            model.addAttribute("tasks", tasks);
            return "home";
        }
        else return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("id") long id, Model model){
        Task taskToDelete = taskService.findByTaskID(id);
        taskService.delete(taskToDelete);
        return  "redirect:/home";
    }

    @RequestMapping(value = "/change/{id}", method = RequestMethod.GET)
    public String changeTaskForm(Model model, HttpSession session, @PathVariable("id") long id){
        User sessiUser = (User) session.getAttribute("LoggedInUser");
        Task taskToChange = taskService.findByTaskID(id);
        if (sessiUser != null) {
            model.addAttribute("taskToChange", taskToChange);
            return  "changeTask";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/change/{id}", method = RequestMethod.POST)
    public String changeTask(Task task, BindingResult result, Model model, @PathVariable("id") long id){
        if(result.hasErrors()) {
            return "redirect:/home";
        }
        Task taskToChange = taskService.findByTaskID(id);
        String nname = task.getName();
        Boolean npriority = task.getPriority();
        //TODO Fix the checked according to user preference
        TaskCategory ncategory = task.getCategory();
        TaskStatus nstatus = task.getStatus();
        Date ndueDate = task.getDueDate();

        taskToChange.setName(nname);
        taskToChange.setPriority(npriority);
        taskToChange.setCategory(ncategory);
        taskToChange.setStatus(nstatus);
        taskToChange.setDueDate(ndueDate);
        taskService.save(taskToChange);
        return "redirect:/home";
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
