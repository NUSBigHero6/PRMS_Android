package sg.edu.nus.iss.phoenix.schedule.android.ui;

/**
 * Created by liu.cao on 25/9/2018.
 */

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
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
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

public class ScheduleListScreen extends AppCompatActivity {

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);
    }*/

    // Tag for logging
    private static final String TAG = ScheduleListScreen.class.getName();

    private ListView mListView;
    private ProgramSlotAdapter mPSAdapter;
    private ProgramSlot selectedPS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);

        ArrayList<ProgramSlot> programSlots = new ArrayList<ProgramSlot>();
        mPSAdapter = new ProgramSlotAdapter(this, programSlots);
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
        ControlFactory.getScheduleController().onDisplayScheduleList(this);
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
                    Toast.makeText(this, "Select a schedule first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "There is no selected schedule.");
                } else {
                    Log.v(TAG, "Viewing schedule of program: " + selectedPS.getProgramName() + "...");
                    ControlFactory.getScheduleController().selectEditSchedule(selectedPS);
                }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        ControlFactory.getScheduleController().maintainedSchedule();
    }

    public void showSchedules(List<ProgramSlot> programSlots) {
        mPSAdapter.clear();
        for (int i = 0; i < programSlots.size(); i++) {
            mPSAdapter.add(programSlots.get(i));
        }
    }
}

