package sg.edu.nus.iss.phoenix.schedule.android.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.schedule.android.ui.ScheduleScreen;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.schedule.android.delegate.RetrieveSchedulesDelegate;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ReviewSelectScheduleScreen;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class ReviewSelectScheduleController {
    // Tag for logging.
    private static final String TAG = ReviewSelectScheduleController.class.getName();

    private ReviewSelectScheduleScreen reviewSelectScheduleScreen;
    private ProgramSlot psSelected = null;
    private List<ProgramSlot> psLists;


    public void startUseCase() {
        psSelected = null;
        Intent intent = new Intent(MainController.getApp(), ReviewSelectScheduleScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplay(ReviewSelectScheduleScreen reviewSelectScheduleScreen) {
        this.reviewSelectScheduleScreen = reviewSelectScheduleScreen;
        new RetrieveSchedulesDelegate(this).execute("allProgramSlot");
    }

    public void schedulesRetrieved(List<ProgramSlot> programSlots) {
        reviewSelectScheduleScreen.showProgramSlots(programSlots);
        this.psLists=programSlots;
        this.reviewSelectScheduleScreen.setValidPSId(getValidPsId());
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
        Log.v(TAG, "Cancelled the selection of schedule.");
        // To call the base use case controller without selection;
        // At present, call the MainController instead.
        ControlFactory.getMainController().selectedScheduledProgram(psSelected);
    }

    public void selectCreateSchedule() {
        psSelected = null;
        Intent intent = new Intent(MainController.getApp(), ScheduleScreen.class);
        intent.putExtra("CurrentProgramSlotId", getValidPsId());
        MainController.displayScreen(intent);
    }
    public  String getValidPsId()
    {
        int i=1;
        for(int j=0; j<psLists.size();j++)
        {
            if( Integer.parseInt(psLists.get(j).getProgramSlotId()) >i)
            {
                i=Integer.parseInt(psLists.get(j).getProgramSlotId());
            }
        }
        return  String.valueOf(i+1);
    }

}
