package is.hi.hbvmid.Services;

import is.hi.hbvmid.Persitence.Entities.Task;

import java.util.List;

/* TODO
- change(changedTask: Task, oldID: Long): Task
- findByUser(userID: Long): List<Task>
- findByFilter(userID: Long, filter: Filter): List<Task>
 */

public interface TaskService {
    Task findByTaskID(long ID);
    List<Task> findAll();
    Task save(Task task);
    void delete(Task task);

}
