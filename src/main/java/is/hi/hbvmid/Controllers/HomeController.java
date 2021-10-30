package is.hi.hbvmid.Controllers;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {
    private TaskService taskService;

    @Autowired
    public HomeController(TaskService taskService){
        this.taskService =taskService;
    }

    @RequestMapping("/")
    public String homePage(Model model){
        //Call a method in a Service Class
        List<Task> allTasks = taskService.findAll();
        //Add some data to the Model
        model.addAttribute("tasks", allTasks);
        return "home";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") long id, Model model){
        Task taskToDelete = taskService.findByTaskID(id);
        taskService.delete(taskToDelete);
        return  "redirect:/";
    }

    @RequestMapping(value = "/addtask", method = RequestMethod.GET)
    public String addTaskForm(Task task){

        return "newTask";
    }

    @RequestMapping(value = "/addtask", method = RequestMethod.POST)
    public String addTask(Task task, BindingResult result, Model model){
        if(result.hasErrors()){
            return "newTask";
        }
        taskService.save(task);
        return "redirect:/";
    }
}
