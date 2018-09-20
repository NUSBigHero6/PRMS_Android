package sg.edu.nus.iss.phoenix.schedule.entity;

import java.sql.Date;
import java.sql.Time;

import javax.xml.datatype.Duration;

import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 * Created by liu.cao on 17/9/2018.
 */

public class ProgramSlot {
    private  Time duration;
    private Date dateOfProgram;
    private Date startTime;
    private String programName;

    private String producerName;
    private String presenterName;
    public Time getDuration (){
        return duration;
    }
    public void  setDuration(Time duration)
    {
        this.duration=duration;
    }
    public Date getDateOfProgram (){
        return dateOfProgram;
    }
    public void  setDateOfProgram(Date dateOfProgram)
    {
        this.dateOfProgram=dateOfProgram;
    }
    public Date getStartTime (){
        return startTime;
    }
    public void  setStartTime(Date startTime)
    {
        this.startTime=startTime;
    }
    public  String getProgramName()
    {
        return  programName;
    }
    public  void setProgramName(String programName)
    {
        this.programName=programName;
    }

    public  String getProducerName()
    {
        return  producerName;
    }
    public  void setProducerName(String producerName)
    {
        this.producerName=producerName;
    }

    public  String getPresenterName()
    {
        return  presenterName;
    }
    public  void setPresenterName(String presenterName)
    {
        this.presenterName=presenterName;
    }
}
