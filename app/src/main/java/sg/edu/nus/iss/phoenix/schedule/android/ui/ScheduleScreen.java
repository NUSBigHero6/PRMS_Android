package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.Nullable;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.Producer;
import sg.edu.nus.iss.phoenix.schedule.entity.Presenter;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class ScheduleScreen extends AppCompatActivity {

    // Tag for logging
    //private static final String TAG = ScheduleScreen.class.getName();

    private EditText mSStartTimeEditText;
    private EditText mSProgramDateEditText;
    //private RadioProgram rp2edit = null;

    String[] programs ={"News","Movie","Drama"};
    String[] producers ={"Me","You","We"};
    String[] presenters ={"She","he","Us"};
    String[] duration ={"00:30:00","01:00:00","01:30:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        // Find all relevant views that we will need to read user input from
        mSProgramDateEditText = (EditText) findViewById(R.id.maintain_date_text_view);
        mSStartTimeEditText = (EditText) findViewById(R.id.maintain_starttime_text_view);

        //Creating the instance of ArrayAdapter containing list of program names
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>
                (this,android.R.layout.select_dialog_item,programs);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv1 = (AutoCompleteTextView)findViewById(R.id.maintain_program_autocomplete_text_view);
        actv1.setThreshold(1);//will start working from first character
        actv1.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView

        //Creating the instance of ArrayAdapter containing list of producer
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>
                (this,android.R.layout.select_dialog_item,producers);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv2 = (AutoCompleteTextView)findViewById(R.id.maintain_producer_autocomplete_text_view);
        actv2.setThreshold(1);//will start working from first character
        actv2.setAdapter(adapter2);//setting the adapter data into the AutoCompleteTextView

        //Creating the instance of ArrayAdapter containing list of presenter
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>
                (this,android.R.layout.select_dialog_item,presenters);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv3 = (AutoCompleteTextView)findViewById(R.id.maintain_presenter_autocomplete_text_view);
        actv3.setThreshold(1);//will start working from first character
        actv3.setAdapter(adapter3);//setting the adapter data into the AutoCompleteTextView

        //Creating the instance of ArrayAdapter containing list of duration
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>
                (this,android.R.layout.select_dialog_item,duration);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv4 = (AutoCompleteTextView)findViewById(R.id.maintain_programslot_autocomplete_duration_text_view);
        actv4.setThreshold(1);//will start working from first character
        actv4.setAdapter(adapter4);//setting the adapter data into the AutoCompleteTextView

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getManageScheduleController().onDisplaySchedule(this);
    }

    public void createSchedule() {
        //this.rp2edit = null;
        mSStartTimeEditText.setText("", TextView.BufferType.EDITABLE);
        mSProgramDateEditText.setText("", TextView.BufferType.EDITABLE);
        //mRPNameEditText.setKeyListener(mRPNameEditTextKeyListener);
    }

    public void editSchedule(ProgramSlot programSlot) {
       /* this.rp2edit = rp2edit;
        if (rp2edit != null) {
            mSStartTimeEditText.setText(rp2edit.getRadioProgramName(), TextView.BufferType.NORMAL);
            mSProgramDateEditText.setText(rp2edit.getRadioProgramDescription(), TextView.BufferType.EDITABLE);
            mRPNameEditText.setKeyListener(null);
        }*/
    }
}
