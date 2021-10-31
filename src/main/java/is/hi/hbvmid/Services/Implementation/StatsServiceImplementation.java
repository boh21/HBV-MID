package is.hi.hbvmid.Services.Implementation;


import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;
import is.hi.hbvmid.Persitence.Repositories.TaskRepository;
import is.hi.hbvmid.Services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* TODO
- countTasks(minDate: Date, maxDate: Date) : Int
- countTasksByStatus(minDate: Date, maxDate: Date) : List<Int>
- showAvgByStatus(minDate: Date, maxDate: Data) : List<Double>
- countTasksByCategory(minDate: Date, maxDate: Date) : List <Int>
- showAvgByCategory(minDate: Date, maxDate: Date) : List <Double>
- countTasksByPriority(minDate: Date, maxDate: Date) : List <Int>
- showAvgByPriority(minDate: Date, maxDate: Date) : List <Double>
- findByTasksByPriorityAndStartDateBetween(minDate: Date, maxDate: Date) : List<Task>
- compareEndDateAndDueDate(minDate: Date, maxDate: Date) : Double
 */
@Service
public class StatsServiceImplementation implements StatsService {

    private TaskRepository taskRepository;

    @Autowired
    public StatsServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public int countTasks(User owner) {
        //Loopa í gegnum öll tasks og telja??
        System.out.println("Listi 1");
        List<Task> listi = taskRepository.findByOwner(owner);
        System.out.println("Listi");
        return listi.size();
    }
}
