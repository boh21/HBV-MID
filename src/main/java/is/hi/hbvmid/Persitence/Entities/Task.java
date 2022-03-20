package is.hi.hbvmid.Persitence.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import is.hi.hbvmid.Persitence.Util.TaskCategory;
import is.hi.hbvmid.Persitence.Util.TaskStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long ID;

    private String name;
    private Boolean priority;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
    private TaskCategory category;
    private TaskStatus status;

    //@ManyToOne(cascade = CascadeType.ALL) //Error
    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //Error
    @ManyToOne(fetch = FetchType.LAZY)
    private Task motherTask;

    //@ManyToOne(cascade = CascadeType.ALL) //Error
    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //Error
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Task() {
    }

    public Task(String name, Boolean priority, Date startDate, Date endDate, Date dueDate, TaskCategory taskCategory, TaskStatus taskStatus) {
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
        this.category = taskCategory;
        this.status = taskStatus;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setOwner(User owner){
        this.owner = owner;
    }

    public User getOwner(){
        return owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public Task getMotherTask() {
        return motherTask;
    }

    public void setMotherTask(Task motherTask) {
        this.motherTask = motherTask;
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
}
