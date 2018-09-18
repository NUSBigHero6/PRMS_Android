package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.List;

/**
 * Created by liu.cao on 17/9/2018.
 */

public class WeeklySchedule {

    private String scheduleName;
    private String scheduleDescription;
    private List<ProgramSlot> programSlots;
    public WeeklySchedule(String scheduleName, String scheduleDescription, String radioProgramDuration) {
        this.scheduleName = scheduleName;
        this.scheduleDescription = scheduleDescription;
    }
    public String getScheduleName() {
        return scheduleName;
    }

    public String getScheduleDescription() {
        return scheduleDescription;
    }

    public void setScheduleDescription(String radioProgramDescription) {
        this.scheduleDescription = radioProgramDescription;
    }

}