package is.hi.hbvmid.Services;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Persitence.Util.TaskCategory;
import is.hi.hbvmid.Persitence.Util.TaskStatus;

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
    List<Task> findByUser(User user);
    List<Task> findTask(User user, String name, Boolean priority, TaskCategory category, TaskStatus status);
    List<Task> findTasks(User user, String name, Boolean priority1, Boolean priority2,
                         TaskCategory category1, TaskCategory category2,
                         TaskCategory category3, TaskCategory category4,
                         TaskCategory category5, TaskCategory category6,
                         TaskCategory category7, TaskCategory category8,
                         TaskStatus status1, TaskStatus status2, TaskStatus status3);
}
