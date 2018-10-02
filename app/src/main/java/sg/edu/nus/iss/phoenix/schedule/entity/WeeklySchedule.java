package sg.edu.nus.iss.phoenix.schedule.entity;

import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * Author  : liu cao
 */


public class WeeklySchedule {

    private Date startDate;
    private String assignedBy;
    private List<ProgramSlot> programSlots;

    public WeeklySchedule() {
    }
    public Date getStartDate() {
        return startDate;
    }

    public String getAssignedBy() {
        return assignedBy;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

}