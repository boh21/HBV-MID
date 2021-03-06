package is.hi.hbvmid.Controllers;

import is.hi.hbvmid.Persitence.Util.PostTask;
import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Persitence.Util.TaskCategory;
import is.hi.hbvmid.Persitence.Util.TaskStatus;
import is.hi.hbvmid.Services.TaskService;
import is.hi.hbvmid.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;

@RestController
public class TaskControllerAPI {
    private TaskService taskService;
    private UserService userService;

    TaskCategory category1;
    TaskCategory category2;
    TaskCategory category3;
    TaskCategory category4;
    TaskCategory category5;
    TaskCategory category6;
    TaskCategory category7;
    TaskCategory category8;
    TaskStatus status1;
    TaskStatus status2;
    TaskStatus status3;


    @Autowired
    public TaskControllerAPI(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    //Skilar öllum töskum í gagnagrunni (frá öllum userum)
    @RequestMapping(value = "/homeAPI", method = RequestMethod.GET)
    public List<Task> getTasks(){
        return taskService.findAll();
    }

    //Skilar öllum töskum notanda
    @RequestMapping(value = "/homeOAPI", method = RequestMethod.GET)
    public List<Task> getTasksByOwner(@RequestParam(value="user") String username)
    {
        System.out.println(username);
        System.out.println(taskService.findByUser(userService.findByUsername(username)));
        return taskService.findByUser(userService.findByUsername(username));
    }

    @RequestMapping(value = "/homesAPI", method = RequestMethod.GET)
    public List<Task> getTaskByName(@RequestParam(value="name") String name,
    @RequestParam("user") String username){
        List<Task> tasks = taskService.findTask(userService.findByUsername(username), name, false, null, null);
        return tasks;
    }

    //TODO: Breyta í fylkjavinnslu
    //TODO: Athuga hvort hægt er að hafa nafn með
    //Skilar töskum eftir filterum og user
    @RequestMapping(value = "/homefAPI", method = RequestMethod.GET)
    public List<Task> getTasksWFilters(@RequestParam(value="priority1") Boolean priority1,
                                       @RequestParam(value="priority2") Boolean priority2,
                                       @RequestParam(value="category1") String categoryInn1,
                                       @RequestParam(value="category2") String categoryInn2,
                                       @RequestParam(value="category3") String categoryInn3,
                                       @RequestParam(value="category4") String categoryInn4,
                                       @RequestParam(value="category5") String categoryInn5,
                                       @RequestParam(value="category6") String categoryInn6,
                                       @RequestParam(value="category7") String categoryInn7,
                                       @RequestParam(value="category8") String categoryInn8,
                                       @RequestParam(value="status1") String statusInn1,
                                       @RequestParam(value="status2") String statusInn2,
                                       @RequestParam(value="status3") String statusInn3,
                                       @RequestParam("user") String username){

        if (categoryInn1.equals("")) {
            category1 = null;
        } else {
            category1 = TaskCategory.valueOf(categoryInn1);
        }
        if (categoryInn2.equals(""))  {
            category2 = null;
        } else {
            category2 = TaskCategory.valueOf(categoryInn2);
        }
        if (categoryInn3.equals("")) {
            category3 = null;
        } else {
            category3 = TaskCategory.valueOf(categoryInn3);
        }
        if (categoryInn4.equals("")) {
            category4 = null;
        } else {
            category4 = TaskCategory.valueOf(categoryInn4);
        }
        if (categoryInn5.equals("")) {
            category5 = null;
        } else {
            category5 = TaskCategory.valueOf(categoryInn5);
        }
        if (categoryInn6.equals("")) {
            category6 = null;
        } else {
            category6 = TaskCategory.valueOf(categoryInn6);
        }
        if (categoryInn7.equals("")) {
            category7 = null;
        } else {
            category7 = TaskCategory.valueOf(categoryInn7);
        }
        if (categoryInn8.equals("")) {
            category8 = null;
        } else {
            category8 = TaskCategory.valueOf(categoryInn8);
        }
        if (statusInn1.equals("")) {
            status1 = null;
        } else {
            status1 = TaskStatus.valueOf(statusInn1);
        }
        if (statusInn2.equals("")) {
            status2 = null;
        } else {
            status2 = TaskStatus.valueOf(statusInn2);
        }
        if (statusInn3.equals("")) {
            status3 = null;
        } else {
            status3 = TaskStatus.valueOf(statusInn3);
        }

        //Call a method in a Service Class
        System.out.println("Controller");
        List<Task> tasks = taskService.findTasks(userService.findByUsername(username), "",
                priority1, priority2, category1, category2, category3, category4, category5, category6,
                category7, category8, status1, status2, status3);
        System.out.println("Skila task");
        System.out.println("Tasks: " + tasks);
        return tasks;
    }


    //Bætir við taski á user Prufuuser for now
    @PostMapping(value = "/addATaskAPI")
    public Task addATask(@RequestBody PostTask postTask){
        User sessUser = userService.findByUsername(postTask.getOwner());
        System.out.println(postTask.getOwner());
        Task task = new Task(postTask.getName(),
                //Breyta í Boolean úr String
                Boolean.parseBoolean(postTask.getPriority()),
                //Breyta í Date úr String
                Date.valueOf(postTask.getStartDate()),
                //Breyta í Date úr String
                Date.valueOf(postTask.getEndDate()),
                //Breyta í Date úr String
                Date.valueOf(postTask.getDueDate()),
                //Breyta í Enum úr String
                TaskCategory.valueOf(postTask.getCategory()),
                //Breyta í Enum úr String
                TaskStatus.valueOf(postTask.getStatus()));
                System.out.println(postTask.getStatus());
        task.setOwner(sessUser);
        System.out.println("Nafn " + postTask.getName());
        return taskService.save(task);
    }

    //Bætir við taski á user Prufuuser for now
    @PutMapping(value = "/updateATaskAPI/{id}")
    public Task addATask(@RequestParam(value="id") long ID,  @RequestBody PostTask postTask){
        Task task = taskService.findByTaskID(ID);
        User sessUser = userService.findByUsername(postTask.getOwner());
        System.out.println(postTask.getOwner());
        task.setName(postTask.getName());
        task.setCategory(TaskCategory.valueOf(postTask.getCategory()));
        task.setDueDate(Date.valueOf(postTask.getDueDate()));
        task.setStatus(TaskStatus.valueOf(postTask.getStatus()));
        task.setPriority(Boolean.parseBoolean(postTask.getPriority()));
        System.out.println("Nafn " + postTask.getName());
        return taskService.save(task);
    }

    //TODO: Gera error handler
    /*
     * Eyða taski úr gagnagrunni
     */
    @RequestMapping(value = "/deleteTaskAPI/{id}", method = RequestMethod.DELETE)
    public Task deleteTask(@PathVariable long id) {
        System.out.println(id);
        Task taskToDelete = taskService.findByTaskID(id);
        taskService.delete(taskToDelete);
        return taskToDelete;
    }

    //@RequestMapping(value = "addTaskAPI", method = RequestMethod.POST)
    /*@PostMapping(value = "/addTaskAPI")
    public Task addTask(@RequestBody Task task){ //@Valid @RequestBody
        System.out.println("Inni i falli");
        if (task == null) {
            System.out.println("Task null");
            return null;
        } else {
            System.out.println(task);
            User sessUser = userService.findByUsername("PrufuUser");
            task.setOwner(sessUser);
            task.setStatus(TaskStatus.NOT_STARTED);
            System.out.println("Nafn " + task.getName());
            System.out.println("Nafn " + task.getCategory());
            System.out.println("Nafn " + task.getPriority());
            System.out.println("Nafn " + task.getDueDate());
            //return taskService.save(task);
            return task;
        }
        //return null;
    }

    /*@PostMapping(value = "/addTaskNameAPI")
    public Task addTaskName(@RequestBody String name){
        System.out.println("Inni i falli");
        User sessUser = userService.findByUsername("PrufuUser");
        Task task = new Task(name, null, null, null, null,
                null, TaskStatus.NOT_STARTED);
        task.setOwner(sessUser);
        task.setStatus(TaskStatus.NOT_STARTED);
        System.out.println("Nafn " + task.getName());
        //return taskService.save(task);
        return task;
    }*/
}