package is.hi.hbvmid.Persitence.Util;

import is.hi.hbvmid.Persitence.Entities.Task;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

//The filter is not currently used in the project, and will likely be cut

public class TaskFilter {
    private Date minDate;
    private Date maxDate;
    private TaskStatus status;
    private TaskCategory category;
    private Task motherTask;
    private String name;
    private final CriteriaBuilder builder;

    public TaskFilter(CriteriaBuilder builder) {
        this.minDate = null;
        this.maxDate = null;
        this.status = null;
        this.category = null;
        this.motherTask = null;
        this.name = null;

        this.builder = builder;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public Task getMotherTask() {
        return motherTask;
    }

    public void setMotherTask(Task motherTask) {
        this.motherTask = motherTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Constructs a CriteriaQuery from the variables in this filter

    //TODO: Add a JPA Static Metamodel generator to to allow for easier refactoring:

    public CriteriaQuery<Task> getQuery() {
        CriteriaQuery<Task> query = this.builder.createQuery(Task.class);
        Root<Task> task = query.from(Task.class);

        if(this.minDate != null) {
            Predicate afterDate = builder.greaterThan(task.get("dueDate"), this.minDate);
            query.where(builder.and(afterDate));
        }
        if(this.maxDate != null) {
            Predicate beforeDate = builder.lessThan(task.get("dueDate"), this.maxDate);
            query.where(builder.and(beforeDate));
        }
        if(this.status != null) {
            Predicate status = builder.equal(task.get("status"), this.status);
            query.where(builder.and(status));
        }
        if(this.category != null) {
            Predicate category = builder.equal(task.get("category"), this.category);
            query.where(builder.and(category));
        }
        if(this.motherTask != null) {
            Predicate motherTask = builder.equal(task.get("motherTask"), this.motherTask);
            query.where(builder.and(motherTask));
        }
        if(this.name != null) {
            Predicate nameLike = builder.like(task.get("name"), this.name);
            query.where(builder.and(nameLike));
        }
        return query;
    }
}