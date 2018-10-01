package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.support.annotation.Nullable;
import android.support.design.widget.CheckableImageButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.radioprogram.android.delegate.RetrieveProgramsDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.android.controller.ScheduleController;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.user.entity.*;

public class ScheduleScreen extends AppCompatActivity implements View.OnClickListener {
    // Tag for logging
    private static final String TAG = ScheduleScreen.class.getName();
    private EditText programNameText;
    KeyListener mRPNameEditTextKeyListener = null;
    private ProgramSlot ps2edit = null;
    private EditText mSStartTimeEditText;
    private EditText mSProgramDateEditText;
    private EditText producerEditorText;
    private EditText presenterEditorText;
    private EditText durationEditorText;
    private CheckableImageButton mBtnSearchProducer;
    private CheckableImageButton mBtnSearchPresenter;
    private List<ProgramSlot> psList;
    private String CurrentProgramSlotId;

    private List<String> programs = new ArrayList<String>();
    private List<String> producers = new ArrayList<String>();
    private List<String> presenters =new ArrayList<String>();
    String[] durations = {"00:30:00", "01:00:00", "02:00:00"};
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        // Keep the KeyListener for name EditText so as to enable editing after disabling it.


        CurrentProgramSlotId=getIntent().getStringExtra("CurrentProgramSlotId");
        programNameText = (EditText) findViewById(R.id.maintain_program_text_view);
        mRPNameEditTextKeyListener = programNameText.getKeyListener();

        mSProgramDateEditText = (EditText) findViewById(R.id.maintain_Date_text_view);

        mSStartTimeEditText = (EditText) findViewById(R.id.maintain_starttime_text_view);
        producerEditorText = (EditText) findViewById(R.id.maintain_producer_text_view);producerEditorText.setClickable(true);
        producerEditorText.setKeyListener(null);

        presenterEditorText = (EditText) findViewById(R.id.maintain_presenter_text_view);
        presenterEditorText.setKeyListener(null);

        programNameText.setClickable(true);
        programNameText.setOnClickListener(this);

        durationEditorText =(EditText)findViewById(R.id.maintain_programslot_duration_text_view);
        durationEditorText.setClickable(true);
        durationEditorText.setOnClickListener(this);

        mBtnSearchProducer = (CheckableImageButton)findViewById(R.id.btn_select_producer);
        mBtnSearchProducer.setOnClickListener(this);

        mBtnSearchPresenter = (CheckableImageButton)findViewById(R.id.btn_select_presenter);
        mBtnSearchPresenter.setOnClickListener(this);

        //Get RadioProgram list

    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_select_producer:
                //ControlFactory.getScheduleController().onDisplayUserList(this, "producer");
                listView = new ListView(this);
                showDialogListView(v, "Producer list"); //improve if time permit
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, producers);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ViewGroup vg = (ViewGroup) view;
                        String producer = producers.get(i);
                        producerEditorText.setText(producer);
                    }
                });
                break;

            case R.id.btn_select_presenter:
               // ControlFactory.getScheduleController().onDisplayUserList(this, "presenter");
                listView = new ListView(this);
                showDialogListView(v, "Presenter list"); //improve if time permit
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, presenters);
                listView.setAdapter(adapter1);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ViewGroup vg = (ViewGroup) view;
                        String presenter = presenters.get(i);
                        presenterEditorText.setText(presenter);
                    }
                });
                break;

            case R.id.maintain_program_text_view:
                listView = new ListView(this);
                showDialogListView(v, "Program list"); //improve if time permit
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, programs);
                listView.setAdapter(adapter2);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ViewGroup vg = (ViewGroup) view;
                        String program = programs.get(i);
                        programNameText.setText(program);
                    }
                });
                break;

            case R.id.maintain_programslot_duration_text_view:
                listView = new ListView(this);
                showDialogListView(v, "Duration list"); //improve if time permit
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, durations);
                listView.setAdapter(adapter3);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ViewGroup vg = (ViewGroup) view;
                        String duration = durations[i];
                        durationEditorText.setText(duration);
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
        ControlFactory.getScheduleController().onDisplayScheduleTest(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    /**
     * This method is called after invalidateOptionsMenu(), so that the
     * menu can be updated (some menu items can be hidden or made visible).
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new schedule, hide the "Delete" menu item.
        if (ps2edit == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        // If this is a copyed scheudle, hide delete and update save
        else if (TextUtils.isEmpty( ps2edit.getStartTime())) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
            MenuItem menuItemSave = menu.findItem(R.id.action_save);
            menuItemSave.setVisible(false);
            MenuItem menuItemSaveSchedule = menu.findItem(R.id.action_copysave);
            menuItemSaveSchedule.setVisible(true);
        }
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save schedule.
                if (ps2edit == null) { // Newly created.
                    Log.v(TAG, "Saving scheduled program " + programNameText.getText().toString() + "...");

                    ProgramSlot ps = new ProgramSlot(
                            CurrentProgramSlotId,
                            mSProgramDateEditText.getText().toString(),
                            programNameText.getText().toString(),
                            producerEditorText.getText().toString(),
                            presenterEditorText.getText().toString(),
                            mSStartTimeEditText.getText().toString(),
                            durationEditorText.getText().toString()
                    );
                    if(!IsProgamSlotValid(ps))
                    {
                        Toast.makeText(this, "Pls fill in all the fields!", Toast.LENGTH_SHORT).show();
                        Log.v(TAG, "Pls fill in all the fields!");
                    }
                    else
                    ControlFactory.getScheduleController().selectCreateSchedule(ps);

                }

                else { // Edited.
                    Log.v(TAG, "Saving scheduled program " + ps2edit.getProgramName() + "...");
                    ps2edit.setDuration(durationEditorText.getText().toString());
                    ps2edit.setStartTime(mSStartTimeEditText.getText().toString());
                    ps2edit.setPresenterName(presenterEditorText.getText().toString());
                    ps2edit.setProducerName(producerEditorText.getText().toString());
                    ps2edit.setProgramName(programNameText.getText().toString());
                    ps2edit.setDateOfProgram( mSProgramDateEditText.getText().toString());
                    if(!IsProgamSlotValid(ps2edit))
                    {
                        Toast.makeText(this, "Pls fill in all the fields!", Toast.LENGTH_LONG).show();
                        Log.v(TAG, "Pls fill in all the fields!");
                        return  false;
                    }
                    else
                   ControlFactory.getScheduleController().selectUpdateSchedule(ps2edit);
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_copysave:
                Log.v(TAG, "Saving Copyed scheduled program " + ps2edit.getProgramName() + "...");
                if(!IsProgamSlotValid(ps2edit))
                {
                    Toast.makeText(this, "Pls fill in all the fields!", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "Pls fill in all the fields!");
                }
                else
                {
                   ps2edit.setProgramSlotId(CurrentProgramSlotId);
                    ControlFactory.getScheduleController().selectCreateSchedule(ps2edit);
                    return true;
                }
            case R.id.action_delete:
                Log.v(TAG, "Deleting scheduled program " + ps2edit.getProgramName() + "...");
                ControlFactory.getScheduleController().selectDeleteSchedule(ps2edit);
                ControlFactory.getScheduleController().ScheduleDeleted(true);
                return true;
            // Respond to a click on the "Cancel" menu option
            case R.id.action_cancel:
                Log.v(TAG, "Canceling creating/editing radio program...");
                ControlFactory.getScheduleController().selectCancelCreateEditSchedule();
                return true;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Log.v(TAG, "Canceling creating/editing schedule...");
        ControlFactory.getScheduleController().selectCancelCreateEditSchedule();
    }

    public void createProgramSlot() {
        this.ps2edit = null;
        programNameText.setText("", TextView.BufferType.EDITABLE);

        programNameText.setKeyListener(mRPNameEditTextKeyListener);
    }

    public void editProgramSlot(ProgramSlot ps) {
        this.ps2edit = ps;
        if (ps2edit != null) {
            programNameText.setText(ps2edit.getProgramName(), TextView.BufferType.NORMAL);
            programNameText.setKeyListener(null);

            mSProgramDateEditText.setText(ps2edit.getDateOfProgram(), TextView.BufferType.NORMAL);
            mSProgramDateEditText.setKeyListener(null);

            mSStartTimeEditText.setText(ps2edit.getStartTime(), TextView.BufferType.NORMAL);
            mSStartTimeEditText.setKeyListener(null);

            producerEditorText.setText(ps2edit.getProducerName(), TextView.BufferType.NORMAL);
            producerEditorText.setKeyListener(null);

            presenterEditorText.setText(ps2edit.getPresenterName(), TextView.BufferType.NORMAL);
            presenterEditorText.setKeyListener(null);

            durationEditorText.setText(ps2edit.getDuration(), TextView.BufferType.NORMAL);
            durationEditorText.setKeyListener(null);

        }
    }
    public  List<ProgramSlot> getPsList ()
    {
        return  psList;
    }
    public  void setPsList(List<ProgramSlot> psList)
    {
        this.psList=psList;
    }
    private boolean IsProgamSlotValid(ProgramSlot ps)
    {
        if(TextUtils.isEmpty(ps.getDateOfProgram())
                || TextUtils.isEmpty(ps.getDuration())
                ||TextUtils.isEmpty(ps.getPresenterName())
                ||TextUtils.isEmpty(ps.getProducerName())
                ||TextUtils.isEmpty(ps.getProgramName())
                ||TextUtils.isEmpty(ps.getStartTime())
                )
            return  false;
            else
                return  true;

    }

  public  void AddRadioPrograms (List<RadioProgram> radioPrograms)
    {
        for (RadioProgram rp: radioPrograms )
        {
            programs.add(rp.getRadioProgramName());
        }

    }

    public  void AddProducers (List<User> users)
    {
        for (User ur: users )
        {
            this.producers.add(ur.getName());
        }

    }

    public  void AddPresenters(List<User> users)
    {
        for (User ur: users )
        {
            this.presenters.add(ur.getName());
        }
    }
}

