package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.ProgramListScreen;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.RadioProgramAdapter;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

public class ScheduleListScreen extends AppCompatActivity {

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);
    }*/

// Tag for logging
private static final String TAG = ScheduleListScreen.class.getName();

// private EditText mRPNameEditText;
// private EditText mRPDescEditText;
// private EditText mDurationEditText;
private ListView mListView;
private ProgramSlotAdapter mPSAdapter;
private ProgramSlot selectedPS = null;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);

        ArrayList<ProgramSlot> programSlots = new ArrayList<ProgramSlot>();
        mPSAdapter = new ProgramSlotAdapter(this, programSlots);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        ControlFactory.getProgramController().selectCreateProgram();
        }
        });

        mListView = (ListView) findViewById(R.id.program_slot_list);
        mListView.setAdapter(mPSAdapter);

        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
@Override
public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        ProgramSlot ps = (ProgramSlot) adapterView.getItemAtPosition(position);
        selectedPS = ps;
        }
@Override
public void onNothingSelected(AdapterView<?> adapterView) {
        // your stuff
        }
        });
        }

@Override
protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setSelection(0);
        ControlFactory.getManageScheduleController().onDisplayScheduleList(this);
        }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
        }

@Override
public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
        // Respond to a click on the "View" menu option
        case R.id.action_view:
        if (selectedPS == null) {
        // Prompt for the selection of a radio program.
        Toast.makeText(this, "Select a radio program first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
        Log.v(TAG, "There is no selected radio program.");
        }
        else {
        Log.v(TAG, "Viewing radio program: " + selectedPS.getProgramName() + "...");
        ControlFactory.getManageScheduleController().selectEditSchedule(selectedPS);
        }
        }

        return true;
        }

@Override
public void onBackPressed() {
        ControlFactory.getProgramController().maintainedProgram();
        }

public void showSchedules(List<ProgramSlot> programSlots) {
    mPSAdapter.clear();
        for (int i = 0; i < programSlots.size(); i++) {
            mPSAdapter.add(programSlots.get(i));
        }
        }
        }
