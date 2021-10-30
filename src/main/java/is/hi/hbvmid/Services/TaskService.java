package is.hi.hbvmid.Services;

import is.hi.hbvmid.Persitence.Entities.Task;

import java.util.List;

public interface TaskService {
    Task findByTaskID(long ID);
    List<Task> findAll();
    Task save(Task task);
    void delete(Task task);

    //public void save(Task task);
    //public void delete(Task task);
    //public Task findByTaskID(long ID);
    //public <List> java.util.List<Task> findByUser(long userID);
    //TODO: findByFilter
    //TODO: change
}
