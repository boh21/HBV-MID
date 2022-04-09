package is.hi.hbvmid.Persitence.Util;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import is.hi.hbvmid.Persitence.Entities.Task;
import is.hi.hbvmid.Persitence.Entities.User;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

public class PostTask {

    public long ID;

    private String name;
    private String priority;
    private String startDate;
    private String endDate;
    private String dueDate;
    private String category;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("motherTask")
    private Task motherTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("owner")
    private User owner;

    public Task getMotherTask() {
        return motherTask;
    }

    public void setMotherTask(Task motherTask) {
        this.motherTask = motherTask;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public PostTask(String name, String priority, String startDate, String endDate, String dueDate, String category, String status) {
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
        this.category = category;
        this.status = status;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
