package is.hi.hbvmid.Persitence.Repositories;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Persitence.Util.TaskCategory;
import is.hi.hbvmid.Persitence.Util.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/*

 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task save(Task task);
    void delete(Task task);

    List<Task> findAll();
    Task findByID(long id);

    List<Task> findByOwner(User owner);
    List<Task> findByOwnerAndNameLike(User owner, String name);
    //@Query("select t from Task t where t.owner = ?1 and t.name like ?2 and t.priority = true and(t.category in ?3) and(t.status in ?4)")
    List<Task> findByOwnerAndNameLikeAndPriorityAndCategoryInAndStatusIn(User owner, String name, boolean priority, List<TaskCategory> category, List<TaskStatus> status);
    //@Query("select t from Task t where t.owner = ?1 and t.name like ?2 and t.category in ?3 and t.status in ?4 ")
    List<Task> findByOwnerAndNameLikeAndCategoryInAndStatusIn(User owner, String name, List<TaskCategory> category, List<TaskStatus> status);
}
