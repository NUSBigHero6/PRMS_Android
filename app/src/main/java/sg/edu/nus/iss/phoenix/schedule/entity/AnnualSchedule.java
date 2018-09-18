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
   private String name;
    private List<WeeklySchedule> weeklySchedules;
    public void add(WeeklySchedule ws)
    {
        if(!weeklySchedules.contains(ws))
        {
            weeklySchedules.add(ws);
        }
    }
    public  void remove(WeeklySchedule ws)
    {
        if(weeklySchedules.contains(ws))
        {
            weeklySchedules.remove(ws);
        }
    }

    public AnnualSchedule ()
    {
    }
}
