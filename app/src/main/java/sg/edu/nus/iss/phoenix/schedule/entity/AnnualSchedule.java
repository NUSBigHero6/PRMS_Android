package sg.edu.nus.iss.phoenix.schedule.entity;

/**
 * Author  : liu cao
 */

import java.util.List;

public class AnnualSchedule {
    private int year;
    private String assignedBy;
    private List<WeeklySchedule> weeklySchedules;

    public int getYear() {
        return year;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }
    public AnnualSchedule ()
    {
    }
}
