package is.hi.hbvmid.Persitence.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long ID;

    private String name;
    private Boolean priority;
    //private Date startDate;
    //private Date endDate;
    //private Date dueDate;
    //private Enum<TaskCategory> category;
    //private Enum<TaskStatus> status;
    private long motherTaskID;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;


    /*public Date getStartDate() {
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
    }*/


    public Task() {
    }

    public Task(String name, Boolean priority /*, Date startDate, Date endDate, Date dueDate*/) {
        this.name = name;
        this.priority = priority;
        /*this.startDate = startDate;
        this.endDate = endDate;
        this.dueDate = dueDate;*/
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public long getMotherTaskID() {
        return motherTaskID;
    }

    public void setMotherTaskID(long motherTaskID) {
        this.motherTaskID = motherTaskID;
    }
}
