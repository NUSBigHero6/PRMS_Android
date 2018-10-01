package sg.edu.nus.iss.phoenix.schedule.android.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.xml.transform.Result;

import sg.edu.nus.iss.phoenix.radioprogram.android.delegate.RetrieveProgramsDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ScheduleScreen;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.CreateScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.CopyScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.DeleteScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.ModifyScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.RetrieveSchedulesDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ReviewSelectScheduleScreen;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ScheduleListScreen;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.Producer;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.user.delegate.ProducerPresentorRetriveSearch;
import sg.edu.nus.iss.phoenix.user.entity.*;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class ScheduleController {

    // Tag for logging.
    private static final String TAG = ScheduleController.class.getName();

   // private ScheduleListScreen scheduleListScreen;
    private ReviewSelectScheduleScreen reviewSelectScheduleScreen;
    private WeeklySchedule weeklySchedule = null;
    private AnnualSchedule annualSchedule = null;
    private ProgramSlot progamSlot = null;
    private ScheduleScreen scheduleScreen;
    private List<ProgramSlot> psLists;
    private List<RadioProgram> radioPrograms= new ArrayList<RadioProgram>();
    private List<User> users = new ArrayList<User>();


    public void startUseCase() {
        progamSlot = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectScheduleScreen.class);
        MainController.displayScreen(intent);
    }

  /*  public void onDisplayScheduleList(ScheduleListScreen scheduleListScreen) {
       // this.scheduleListScreen = scheduleListScreen;
        new RetrieveSchedulesDelegate(this).execute("all");
    }*/

    public void SchedulesRetrieved(List<ProgramSlot> programSlots) {
        reviewSelectScheduleScreen.showProgramSlots(programSlots);
        psLists=programSlots;
    }

    public void selectCreateSchedule() {
        progamSlot = null;
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }


    public void selectEditSchedule(ProgramSlot progamSlot) {
        this.progamSlot = progamSlot;
        Log.v(TAG, "Editing Scheduled Program: " + progamSlot.getProgramName() + "...");
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectCopySchedule(ProgramSlot progamSlot) {
        this.progamSlot = progamSlot;
        Log.v(TAG, "Copying Scheduled Program: " + progamSlot.getProgramName() + "...");
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }

 /*   public void onDisplaySchedule(ScheduleScreen scheduleScreen) {
        this.scheduleScreen = scheduleScreen;
        if (progamSlot == null)
            this.scheduleScreen.createProgramSlot();

        else
            this.scheduleScreen.editProgramSlot(progamSlot);
    }*/

/*    public void selectCopySchedule(ProgramSlot programSlot) {
        new CopyScheduleDelegate(this).execute(programSlot);
    }*/

    public void selectUpdateSchedule(ProgramSlot programSlot) {
        new ModifyScheduleDelegate(this).execute(programSlot);
    }

    public void selectDeleteSchedule(ProgramSlot programSlot) {
        new DeleteScheduleDelegate(this).execute(programSlot.getProgramSlotId());
    }

    public void ScheduleDeleted(boolean success) {
        // Go back to ManageScheduleList screen with refreshed ManageSchedules.
        startUseCase();
    }

    public void ScheduleUpdated(boolean success) {
        // Go back to ManageScheduleList screen with refreshed ManageSchedules.
        startUseCase();
    }

    public void ScheduleCopied(boolean success) {
        // Go back to ManageScheduleList screen with refreshed ManageSchedules.
        startUseCase();
    }

    public void selectCreateSchedule(ProgramSlot programSlot) {
        //new RetrieveProgramsDelegate(this).execute("all");
        new CreateScheduleDelegate(this).execute(programSlot);

    }

    public void ScheduleCreated(boolean success) {
        // Go back to ManageScheduleList screen with refreshed ManageSchedules.
        startUseCase();
    }

    public void selectCancelCreateEditSchedule() {
        // Go back to ManageScheduleList screen with refreshed ManageSchedules.
        startUseCase();
    }

    public void maintainedSchedule() {
        ControlFactory.getMainController().selectMaintainSchedule();
    }

    //For Testing

    public void onDisplayScheduleTest(ScheduleScreen scheduleScreen) {
        this.scheduleScreen = scheduleScreen;
        this.scheduleScreen.setPsList(psLists);
        if (this.progamSlot == null) {
            this.scheduleScreen.createProgramSlot();
        }

        else {
            this.scheduleScreen.editProgramSlot(progamSlot);
        }

        new RetrieveProgramsDelegate(this).execute("all");
        new ProducerPresentorRetriveSearch(this, "producer").execute("all");
        new ProducerPresentorRetriveSearch(this, "presenter").execute("all");


    }

    public void onDisplayUserList (ScheduleScreen scheduleScreen, String role) {

        String type = role;
        new ProducerPresentorRetriveSearch(this, type).execute("all");
    }

    public void setRadioProgramList(List<RadioProgram> radioPrograms) {
        scheduleScreen.AddRadioPrograms(radioPrograms);
    }

    public void setProducers(List<User> users) {
        scheduleScreen.AddProducers(users);
    }
    public void setPresenters(List<User> users) {
        scheduleScreen.AddPresenters(users);
    }

}
