package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class ScheduleScreen extends AppCompatActivity {

    // Tag for logging
    //private static final String TAG = ScheduleScreen.class.getName();

    private EditText mSStartTimeEditText;
    private EditText mSProgramDateEditText;
    private EditText producerEditorText;
    //private RadioProgram rp2edit = null;

    String[] programs ={"News","Movie","Drama"};
    String[] producers ={"Me","You","We"};
    String[] presenters ={"She","he","Us"};
    String[] duration ={"00:30:00","01:00:00","01:30:00"};
    ListView listView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        mSProgramDateEditText = (EditText) findViewById(R.id.maintain_Date_text_view);
        mSStartTimeEditText = (EditText) findViewById(R.id.maintain_starttime_text_view);
        producerEditorText=(EditText) findViewById(R.id.maintain_producer_text_view);

        listView= new ListView(this);

        producerEditorText.setClickable(true);
        producerEditorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogListView(v);
            }
        });

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem ,producers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViewGroup vg=(ViewGroup) view;
                String produer= producers[i];
                producerEditorText.setText(produer);
            }
        });
    }
    public  void  showDialogListView(View view )
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(ScheduleScreen.this);
        builder.setTitle("Producer List");
        builder.setCancelable(true);
        builder.setPositiveButton("OK",null);
        builder.setView(listView);
        AlertDialog dialog=builder.create();
        dialog.show();
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
