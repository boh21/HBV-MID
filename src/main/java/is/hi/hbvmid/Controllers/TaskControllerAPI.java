package is.hi.hbvmid.Controllers;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Persitence.Util.TaskCategory;
import is.hi.hbvmid.Persitence.Util.TaskStatus;
import is.hi.hbvmid.Services.TaskService;
import is.hi.hbvmid.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@RestController
public class TaskControllerAPI {
    private TaskService taskService;
    private UserService userService;

    @Autowired
    public TaskControllerAPI(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }


    @RequestMapping(value = "/homeAPI", method = RequestMethod.GET)
    public List<Task> getTasks(){
        List<Task> tasks = taskService.findAll();
        List<User> users = userService.findAll();
        System.out.println("Tösk");
        System.out.println(tasks.get(0).getName());
        System.out.println(tasks.get(1).getName());
        System.out.println("Userar");
        System.out.println(users.get(0).getUsername());
        return taskService.findAll();
    }

    /*@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("id") long id, Model model){
        Task taskToDelete = taskService.findByTaskID(id);
        taskService.delete(taskToDelete);
        return  "redirect:/home";
    }*/
}