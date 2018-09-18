package sg.edu.nus.iss.phoenix.schedule.entity;

import java.sql.Time;

import javax.xml.datatype.Duration;

import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 * Created by liu.cao on 17/9/2018.
 */

public class ProgramSlot {
    private  int programSlotId;
    private Time startTime;
    private Time endTime;
    private RadioProgram rp;
}
