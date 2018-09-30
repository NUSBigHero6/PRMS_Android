package sg.edu.nus.iss.phoenix.schedule.android.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import sg.edu.nus.iss.phoenix.schedule.android.delegate.RetrieveProducerDelegate;
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
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.Producer;

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
    private Producer producer = null;
    private ScheduleScreen scheduleScreen;
    private List<ProgramSlot> psLists;
    private List<Producer> prodLists;


    public void startUseCase() {
        progamSlot = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayScheduleList(ScheduleListScreen scheduleListScreen) {
        // this.scheduleListScreen = scheduleListScreen;
        new RetrieveSchedulesDelegate(this).execute("all");
    }

    public void onDisplayProducerList(ScheduleScreen scheduleScreen) {
         //this.scheduleScreen = scheduleScreen;
        new RetrieveProducerDelegate(this).execute("all");
    }

    public void SchedulesRetrieved(List<ProgramSlot> programSlots) {
        reviewSelectScheduleScreen.showProgramSlots(programSlots);
        psLists=programSlots;
    }

    public void ProducersRetrieved(List<Producer> producers) {
        //scheduleScreen.showProducers(producers);
        this.prodLists=producers;
        //this.reviewSelectScheduleScreen.setValidPSId(getValidPsId());
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
    }


}
