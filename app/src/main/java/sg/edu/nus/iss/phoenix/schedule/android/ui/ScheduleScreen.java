package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class ScheduleScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }


    public void createSchedule() {
        /*this.rp2edit = null;
        mRPNameEditText.setText("", TextView.BufferType.EDITABLE);
        mRPDescEditText.setText("", TextView.BufferType.EDITABLE);
        mDurationEditText.setText("", TextView.BufferType.EDITABLE);
        mRPNameEditText.setKeyListener(mRPNameEditTextKeyListener);*/
    }

    public void editSchedule(ProgramSlot programSlot) {
       /* this.rp2edit = rp2edit;
        if (rp2edit != null) {
            mRPNameEditText.setText(rp2edit.getRadioProgramName(), TextView.BufferType.NORMAL);
            mRPDescEditText.setText(rp2edit.getRadioProgramDescription(), TextView.BufferType.EDITABLE);
            mDurationEditText.setText(rp2edit.getRadioProgramDuration(), TextView.BufferType.EDITABLE);
            mRPNameEditText.setKeyListener(null);
        }*/
    }
}
