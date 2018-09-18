package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.List;

/**
 * Created by liu.cao on 17/9/2018.
 */

public class AnnualSchedule {

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

}
