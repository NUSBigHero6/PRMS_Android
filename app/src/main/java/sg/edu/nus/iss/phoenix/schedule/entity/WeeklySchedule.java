package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.List;

/**
 * Created by liu.cao on 17/9/2018.
 */

public class WeeklySchedule {

    private String scheduleName;
    private List<ProgramSlot> programSlots;
    public WeeklySchedule(String scheduleName, String scheduleDescription, String radioProgramDuration) {
        this.scheduleName = scheduleName;
    }
    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

}