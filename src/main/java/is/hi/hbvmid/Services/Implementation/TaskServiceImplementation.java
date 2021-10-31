package is.hi.hbvmid.Services.Implementation;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Persitence.Repositories.TaskRepository;
import is.hi.hbvmid.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    public List<Task> findByUserAndName(User user, String name) {
        return taskRepository.findByOwnerAndNameContains(user, name);
    }
}
