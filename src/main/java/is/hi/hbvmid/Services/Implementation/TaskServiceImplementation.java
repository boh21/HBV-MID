package is.hi.hbvmid.Services.Implementation;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Persitence.Repositories.TaskRepository;
import is.hi.hbvmid.Persitence.Util.TaskCategory;
import is.hi.hbvmid.Persitence.Util.TaskStatus;
import is.hi.hbvmid.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/* TODO
- change(changedTask: Task, oldID: Long): Task
- findByFilter(userID: Long, filter: Filter): List<Task>
 */

@Service
public class TaskServiceImplementation implements TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findByTaskID(long ID) {
        return taskRepository.findByID(ID);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        //task.setStatus(TaskStatus.NOT_STARTED);
        return taskRepository.save(task);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public List<Task> findByUser(User user) {
        return taskRepository.findByOwner(user);
    }

    @Override
    public List<Task> findTask(User user, String name, Boolean priority, TaskCategory category, TaskStatus status) {
        if(name == null) { name = "%%";}
        else { name = "%" + name + "%";}
        //return taskRepository.findByOwnerAndNameContains(user, name);

        System.out.println("Taskname: " + name);
        List<TaskCategory> categories = new ArrayList<>();
        if(category == null) {
            for(int i = 0; i < TaskCategory.values().length; i++) {
                categories.add(TaskCategory.values()[i]);
            }
        } else {
            categories.add(category);
        }
        System.out.println(categories);

        List<TaskStatus> statuses = new ArrayList<>();
        if(status == null) {
            for(int i = 0; i < TaskStatus.values().length; i++) {
                statuses.add(TaskStatus.values()[i]);
            }
        } else {
            statuses.add(status);
        }
        System.out.println(statuses);
        System.out.println(priority);
        
        if(priority == null) {
            return taskRepository.findByOwnerAndNameLikeAndCategoryInAndStatusIn(
                    user, name, categories, statuses);
            //return taskRepository.findByOwnerAndNameAndCategoryInAndStatusIn(
            //        user, name, categories, statuses);
        } else {
            System.out.println("Trying to filter out low prio");
            return taskRepository.findByOwnerAndNameLikeAndPriorityAndCategoryInAndStatusIn(
                    user, name, true, categories, statuses);
        }
    }
}