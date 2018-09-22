package sg.edu.nus.iss.phoenix.schedule.android.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.RetrieveSchedulesDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ReviewSelectScheduledProgramScreen;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class ReviewSelectScheduledProgramController {
    // Tag for logging.
    private static final String TAG = ReviewSelectScheduledProgramController.class.getName();

    private ReviewSelectScheduledProgramScreen reviewSelectScheduledProgramScreen;
    private ProgramSlot psSelected = null;

    public void startUseCase() {
        psSelected = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectScheduledProgramScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplay(ReviewSelectScheduledProgramScreen reviewSelectScheduledProgramScreen) {
        this.reviewSelectScheduledProgramScreen = reviewSelectScheduledProgramScreen;
        new RetrieveSchedulesDelegate(this).execute("all");
    }

    public void schedulesRetrieved(List<ProgramSlot> programSlots) {
        reviewSelectScheduledProgramScreen.showProgramSlots(programSlots);
    }

    public void selectSchedule(ProgramSlot programSlot) {
        psSelected = programSlot;
        Log.v(TAG, "Selected  schedule: " + programSlot.getProgramName() + ".");
        // To call the base use case controller with the selected radio program.
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedScheduledProgram(psSelected);
    }

    public void selectCancel() {
        psSelected = null;
        Log.v(TAG, "Cancelled the seleciton of schedule.");
        // To call the base use case controller without selection;
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedScheduledProgram(psSelected);
    }
}
