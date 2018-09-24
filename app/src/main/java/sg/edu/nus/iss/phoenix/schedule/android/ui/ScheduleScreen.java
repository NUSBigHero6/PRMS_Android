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

import static sg.edu.nus.iss.phoenix.R.*;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class ScheduleScreen extends AppCompatActivity implements View.OnClickListener {

    // Tag for logging
    //private static final String TAG = ScheduleScreen.class.getName();

    private EditText mSStartTimeEditText;
    private EditText mSProgramDateEditText;
    private EditText producerEditorText;
    private EditText presenterEditorText;
    private EditText programNameText;
    //private RadioProgram rp2edit = null;

    String[] programs = {"News", "Movie", "Drama"};
    String[] producers = {"Me", "You", "We"};
    String[] presenters = {"She", "he", "Us"};
    String[] duration = {"00:30:00", "01:00:00", "01:30:00"};
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_schedule);
        mSProgramDateEditText = (EditText) findViewById(id.maintain_Date_text_view);
        mSStartTimeEditText = (EditText) findViewById(id.maintain_starttime_text_view);

        producerEditorText = (EditText) findViewById(id.maintain_producer_text_view);
        producerEditorText.setClickable(true);
        producerEditorText.setOnClickListener(this);

        presenterEditorText = (EditText) findViewById(id.maintain_presenter_text_view);
        presenterEditorText.setClickable(true);
        presenterEditorText.setOnClickListener(this);

        programNameText = (EditText) findViewById(id.maintain_program_text_view);
        programNameText.setClickable(true);
        programNameText.setOnClickListener(this);

    }

    @Override

    public void onClick(View v) {

        switch (v.getId()) {

            case id.maintain_producer_text_view:
                listView= new ListView(this);
                showDialogListView(v, "Producer list"); //improve if time permit
                ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, layout.list_item, id.txtitem ,producers);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ViewGroup vg=(ViewGroup) view;
                        String producer= producers[i];
                        producerEditorText.setText(producer);
                    }
                });
                break;

            case id.maintain_presenter_text_view:
                listView= new ListView(this);
                showDialogListView(v, "Presenter list"); //improve if time permit
                ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this, layout.list_item, id.txtitem ,presenters);
                listView.setAdapter(adapter1);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ViewGroup vg=(ViewGroup) view;
                        String presenter= presenters[i];
                        presenterEditorText.setText(presenter);
                    }
                });
                break;

            case id.maintain_program_text_view:
                listView= new ListView(this);
                showDialogListView(v, "Program list"); //improve if time permit
                ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this, layout.list_item, id.txtitem ,programs);
                listView.setAdapter(adapter2);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ViewGroup vg=(ViewGroup) view;
                        String program= programs[i];
                        programNameText.setText(program);
                    }
                });
                break;
        }
    }

    public void showDialogListView(View view, String listName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleScreen.this);
        builder.setTitle(listName);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", null);
        builder.setView(listView);
        AlertDialog dialog = builder.create();
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


