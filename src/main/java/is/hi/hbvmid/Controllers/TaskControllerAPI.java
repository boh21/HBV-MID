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
        return taskService.findAll();
    }

    @RequestMapping(value = "/homefAPI", method = RequestMethod.GET)
    public List<Task> getTasksWFilters(@RequestParam(value="priority") Boolean priority,
                                       @RequestParam(value="category") String categoryInn,
                                       @RequestParam(value="status") String statusInn){
        TaskCategory category = TaskCategory.valueOf(categoryInn);
        TaskStatus status = TaskStatus.valueOf(statusInn);
        //Call a method in a Service Class
        List<Task> tasks = taskService.findTask(userService.findByUsername("PrufuUser"), "",  priority, category, status);
        System.out.println("Skila task");
        return tasks;
    }

    @RequestMapping(value = "/homesAPI", method = RequestMethod.GET)
    public List<Task> getTasksWFilters(@RequestParam(value="name") String name){
        List<Task> tasks = taskService.findTask(userService.findByUsername("PrufuUser"), name, false, null, null);
        return tasks;
    }

    @RequestMapping(value = "/addTaskAPI", method = RequestMethod.POST)
    public Task addTask(Task task){
        if (task == null) {
            System.out.println("Task null");
            return null;
        } else {
            System.out.println(task);
            User sessUser = userService.findByUsername("PrufuUser");
            task.setOwner(sessUser);
            task.setStatus(TaskStatus.NOT_STARTED);
            System.out.println("Nafn " + task.getName());
            //return taskService.save(task);
        }
        return null;
    }

    /*@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("id") long id, Model model){
        Task taskToDelete = taskService.findByTaskID(id);
        taskService.delete(taskToDelete);
        return  "redirect:/home";
    }*/
}