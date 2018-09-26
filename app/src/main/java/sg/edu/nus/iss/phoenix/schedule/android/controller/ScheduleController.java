package sg.edu.nus.iss.phoenix.schedule.android.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.schedule.android.ui.ScheduleScreen;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.CreateScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.DeleteScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.ModifyScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.RetrieveSchedulesDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.ui.PresenterListScreen;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ProducerListScreen;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ReviewSelectScheduleScreen;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ScheduleListScreen;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class ScheduleController {

    // Tag for logging.
    private static final String TAG = ScheduleController.class.getName();

    private ProducerListScreen producerListScreen;
    private PresenterListScreen presenterListScreen;
    private ScheduleListScreen scheduleListScreen;
    private ReviewSelectScheduleScreen reviewSelectScheduleScreen;
    private WeeklySchedule weeklySchedule = null;
    private AnnualSchedule annualSchedule = null;
    private ProgramSlot progamSlot = null;
    private ScheduleScreen scheduleScreen;

    public void startUseCase() {
        progamSlot = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayScheduleList(ScheduleListScreen scheduleListScreen) {
        this.scheduleListScreen = scheduleListScreen;
        new RetrieveSchedulesDelegate(this).execute("all");
    }


    public void SchedulesRetrieved(List<ProgramSlot> programSlots) {
        reviewSelectScheduleScreen.showProgramSlots(programSlots);
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


    public void onDisplaySchedule(ScheduleScreen scheduleScreen) {
        this.scheduleScreen = scheduleScreen;
        if (progamSlot == null)
            this.scheduleScreen.createProgramSlot();

        else
            this.scheduleScreen.editProgramSlot(progamSlot);
    }

    public void selectUpdateSchedule(ProgramSlot programSlot) {
        new ModifyScheduleDelegate(this).execute(programSlot);
    }

    public void selectDeleteSchedule(ProgramSlot programSlot) {
        new DeleteScheduleDelegate(this).execute(programSlot.getProgramName());
    }

    public void ScheduleDeleted(boolean success) {
        // Go back to ManageScheduleList screen with refreshed ManageSchedules.
        startUseCase();
    }

    public void ScheduleUpdated(boolean success) {
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

        if (this.progamSlot == null)
            this.scheduleScreen.createProgramSlot();

        else
            this.scheduleScreen.editProgramSlot(progamSlot);
    }


}
