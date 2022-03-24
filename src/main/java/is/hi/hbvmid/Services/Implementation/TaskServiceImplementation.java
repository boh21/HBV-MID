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
        System.out.println(taskRepository.save(task));
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

    @Override
    public List<Task> findTasks(User user, String name, Boolean priority1, Boolean priority2,
                         TaskCategory category1, TaskCategory category2,
                         TaskCategory category3, TaskCategory category4,
                         TaskCategory category5, TaskCategory category6,
                         TaskCategory category7, TaskCategory category8,
                         TaskStatus status1, TaskStatus status2, TaskStatus status3)
    {
        //Notandi sendir inn hvort hann er að leita eftir high priority, low eða bæði
        //Ef bæði er null eða bæði hakað sýnir hann allt
        //Notandi sendir inn categories, er allir eða enginn er hakaður sýnir hann allt
        //Sama með status
        if(name == null) { name = "%%";}
        else { name = "%" + name + "%";}
        List<TaskCategory> categories = new ArrayList<>();

        if((category1 == null && category2 == null && category3 == null && category4 == null
            && category5 == null && category6 == null && category7 == null && category7 == null)
                || (category1 != null && category2 != null && category3 != null && category4 != null
                && category5 != null && category6 != null && category7 != null && category7 != null))
        {
            for(int i = 0; i < TaskCategory.values().length; i++) {
                categories.add(TaskCategory.values()[i]);
            }
        } else {
            if (category1 != null) {
                categories.add(category1);
            }
            if (category2 != null) {
                categories.add(category2);
            }
            if (category3 != null) {
                categories.add(category3);
            }
            if (category4 != null) {
                categories.add(category4);
            }
            if (category5 != null) {
                categories.add(category5);
            }
            if (category6 != null) {
                categories.add(category6);
            }
            if (category7 != null) {
                categories.add(category7);
            }
            if (category8 != null) {
                categories.add(category8);
            }
        }
        System.out.println(categories);

        List<TaskStatus> statuses = new ArrayList<>();
        if((status1 == null && status2 == null && status3 == null)
                || (status1 != null && status2 != null && status3 != null) )
        {
            for(int i = 0; i < TaskStatus.values().length; i++) {
                statuses.add(TaskStatus.values()[i]);
            }
        } else {
            if (status1 != null) {
                statuses.add(status1);
            }
            if (status2 != null) {
                statuses.add(status2);
            }
            if (status3 != null) {
                statuses.add(status3);
            }
        }
        System.out.println(statuses);
        System.out.println(priority1);
        System.out.println(priority2);

        if((priority1 == true && priority2 == true) || (priority1 == false && priority2 == false)){
            //Sýna allt, bæði með true og false
            System.out.println("Sýna allt, bæði með true og false");
            return taskRepository.findByOwnerAndNameLikeAndCategoryInAndStatusIn(
                    user, name, categories, statuses);
        } else if (priority1 == false){
            //Sýna bara það sem er low priority
            System.out.println("Sýna bara það sem er low priority");
            return taskRepository.findByOwnerAndNameLikeAndPriorityAndCategoryInAndStatusIn(
                    user, name, false, categories, statuses);
        } else {
            System.out.println("Sýna bara það sem er high priority");
            return taskRepository.findByOwnerAndNameLikeAndPriorityAndCategoryInAndStatusIn(
                    user, name, true, categories, statuses);
            //Sýna bara það sem er high priority
        }
    }
}