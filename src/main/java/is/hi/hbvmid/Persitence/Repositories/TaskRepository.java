package is.hi.hbvmid.Persitence.Repositories;

import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/*

 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task save(Task task);
    void delete(Task task);

    List<Task> findAll();
    Task findByID(long id);

    List<Task> findByOwner(User owner);

}
