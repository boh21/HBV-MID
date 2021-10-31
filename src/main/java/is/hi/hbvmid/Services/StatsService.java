package is.hi.hbvmid.Services;

import is.hi.hbvmid.Persitence.Entities.User;

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
public interface StatsService {
    int countTasks(User owner);
}
