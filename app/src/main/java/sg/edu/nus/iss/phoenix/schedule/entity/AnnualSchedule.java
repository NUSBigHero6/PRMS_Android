package sg.edu.nus.iss.phoenix.schedule.entity;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by liu.cao on 17/9/2018.
 */

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
