package sg.edu.nus.iss.phoenix.schedule.entity;

import java.sql.Date;
import java.sql.Time;

import javax.xml.datatype.Duration;

import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;


/**
 * Author  : liu cao
 */


public class ProgramSlot {
    private String programSlotId;
    private String duration;
    private String dateOfProgram;
    private String startTime;
    private String programName;

    private String producerName;
    private String presenterName;
    public ProgramSlot(String programSlotId,String dateOfProgram,String programName,String producerName,String presenterName,String startTime,String duration)
    {
        this.programSlotId=programSlotId;
        this.duration=duration;
        this.dateOfProgram=dateOfProgram;
        this.startTime=startTime;
        this.programName=programName;
        this.presenterName=presenterName;
        this.producerName=producerName;
    }
    public ProgramSlot (String programSlotId )
    {
        this.programSlotId=programSlotId;
    }
    public String getProgramSlotId (){
        return this.programSlotId;
    }
    public void  setProgramSlotId(String programSlotId)
    {
        this.programSlotId=programSlotId;
    }

    public String getDuration (){
        return duration;
    }
    public void  setDuration(String duration)
    {
        this.duration=duration;
    }

    public String getDateOfProgram (){
        return dateOfProgram;
    }
    public void  setDateOfProgram(String dateOfProgram)
    {
        this.dateOfProgram=dateOfProgram;
    }
    public String getStartTime (){
        return startTime;
    }
    public void  setStartTime(String startTime)
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
