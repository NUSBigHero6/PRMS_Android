package sg.edu.nus.iss.phoenix.schedule.android.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.radioprogram.android.controller.ProgramController;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.MaintainProgramScreen;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.ProgramListScreen;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.CreateScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.DeleteScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.ui.PresenterListScreen;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ProducerListScreen;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ScheduleScreen;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class ManageScheduleController {

    // Tag for logging.
    private static final String TAG = ManageScheduleController.class.getName();

    private ProducerListScreen producerListScreen;
    private PresenterListScreen presenterListScreen;
    private ScheduleScreen scheduleScreen;
    private WeeklySchedule weeklySchedule = null;
    private AnnualSchedule annualSchedule=null;
    private ProgramSlot progamSlot=null;

    public void startUseCase() {
        progamSlot = null;
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayManageScheduleList(ScheduleScreen scheduleScreen) {
        this.scheduleScreen = scheduleScreen;
       // new RetrieveManageSchedulesDelegate(this).execute("all");
    }

    public void ManageSchedulesRetrieved(List<AnnualSchedule> annualSchedules) {
        //ScheduleScreen.showManageSchedules(annualSchedules);
    }

    public void selectCreateSchedule() {
        progamSlot = null;
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectEditSchedule(ProgramSlot progamSlot) {
        progamSlot = progamSlot;
        Log.v(TAG, "Editing Schedule: " + progamSlot.getProgramName() + "...");

        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        Bundle b = new Bundle();
        b.putString("Name", progamSlot.getProgramName());
     /*   b.putString("Description", radioManageSchedule.getRadioManageScheduleDescription());
        b.putString("Duration", radioManageSchedule.getRadioManageScheduleDuration());*/
        intent.putExtras(b);
        MainController.displayScreen(intent);
    }

    public void onDisplaySchedule(ScheduleScreen scheduleScreen) {
        this.scheduleScreen = scheduleScreen;
        if (progamSlot == null)
            scheduleScreen.createSchedule();

        else
            scheduleScreen.editSchedule(progamSlot);
    }

    public void selectUpdateSchedule(ProgramSlot programSlot) {
        //new UpdateScheduleDelegate(this).execute(programSlot);
    }

    public void selectDeleteSchedule(ProgramSlot programSlot) {
        //new DeleteScheduleDelegate(this).execute(programSlot.getRadioManageScheduleName());
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
       // new CreateScheduleDelegate(this).execute(programSlot);
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
        ControlFactory.getManageScheduleController();
    }

}
