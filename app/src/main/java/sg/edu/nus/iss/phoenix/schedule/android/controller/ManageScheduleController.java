package sg.edu.nus.iss.phoenix.schedule.android.controller;

import android.content.Intent;
import android.util.Log;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.radioprogram.android.controller.ProgramController;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.MaintainProgramScreen;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.ProgramListScreen;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class ManageScheduleController {

    // Tag for logging.
    private static final String TAG = ManageScheduleController.class.getName();

/*    private ProducerListScreen producerListScreen;
    private ScheduleScreen scheduleScreen;
    private RadioProgram rp2edit = null;

    public void startUseCase() {
        rp2edit = null;
        Intent intent = new Intent(MainController.getApp(), ManageScheduleListScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayManageScheduleList(ManageScheduleListScreen ManageScheduleListScreen) {
        this.ManageScheduleListScreen = ManageScheduleListScreen;
        new RetrieveManageSchedulesDelegate(this).execute("all");
    }

    public void ManageSchedulesRetrieved(List<RadioManageSchedule> radioManageSchedules) {
        ManageScheduleListScreen.showManageSchedules(radioManageSchedules);
    }

    public void selectCreateManageSchedule() {
        rp2edit = null;
        Intent intent = new Intent(MainController.getApp(), MaintainManageScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectEditManageSchedule(RadioManageSchedule radioManageSchedule) {
        rp2edit = radioManageSchedule;
        Log.v(TAG, "Editing radio ManageSchedule: " + radioManageSchedule.getRadioManageScheduleName() + "...");

        Intent intent = new Intent(MainController.getApp(), MaintainManageScheduleScreen.class);
*//*        Bundle b = new Bundle();
        b.putString("Name", radioManageSchedule.getRadioManageScheduleName());
        b.putString("Description", radioManageSchedule.getRadioManageScheduleDescription());
        b.putString("Duration", radioManageSchedule.getRadioManageScheduleDuration());
        intent.putExtras(b);
*//*
        MainController.displayScreen(intent);
    }

    public void onDisplayManageSchedule(MaintainManageScheduleScreen maintainManageScheduleScreen) {
        this.maintainManageScheduleScreen = maintainManageScheduleScreen;
        if (rp2edit == null)
            maintainManageScheduleScreen.createManageSchedule();
        else
            maintainManageScheduleScreen.editManageSchedule(rp2edit);
    }

    public void selectUpdateManageSchedule(RadioManageSchedule rp) {
        new UpdateManageScheduleDelegate(this).execute(rp);
    }

    public void selectDeleteManageSchedule(RadioManageSchedule rp) {
        new DeleteManageScheduleDelegate(this).execute(rp.getRadioManageScheduleName());
    }

    public void ManageScheduleDeleted(boolean success) {
        // Go back to ManageScheduleList screen with refreshed ManageSchedules.
        startUseCase();
    }

    public void ManageScheduleUpdated(boolean success) {
        // Go back to ManageScheduleList screen with refreshed ManageSchedules.
        startUseCase();
    }

    public void selectCreateManageSchedule(RadioManageSchedule rp) {
        new CreateManageScheduleDelegate(this).execute(rp);
    }

    public void ManageScheduleCreated(boolean success) {
        // Go back to ManageScheduleList screen with refreshed ManageSchedules.
        startUseCase();
    }

    public void selectCancelCreateEditManageSchedule() {
        // Go back to ManageScheduleList screen with refreshed ManageSchedules.
        startUseCase();
    }*/

    public void maintainedSchedule() {
        ControlFactory.getManageScheduleController();
    }


}
