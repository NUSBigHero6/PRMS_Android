package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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

public class ReviewSelectScheduleScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = ReviewSelectScheduleScreen.class.getName();

    private ProgramSlotAdapter mPSadapter;
    // private ArrayAdapter<String> adapter = null;
    private ListView mListView;
    private ProgramSlot selectedPS = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_select_schedule);

        ArrayList<ProgramSlot> programSlots = new ArrayList<ProgramSlot>();
        mPSadapter = new ProgramSlotAdapter(this, programSlots);
        mListView = (ListView) findViewById(R.id.review_select_ps_list);
        mListView.setAdapter(mPSadapter);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.schedulefab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ControlFactory.getScheduleController().selectCreateSchedule();
                ControlFactory.getReviewSelectScheduleController().selectCreateSchedule();
            }
        });
        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Log.v(TAG, "Radio program at position " + position + " selected.");
                ProgramSlot ps = (ProgramSlot) adapterView.getItemAtPosition(position);
                // Log.v(TAG, "Radio program name is " + rp.getRadioProgramName());
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

        ControlFactory.getReviewSelectScheduleController().onDisplay(this);
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
                    Toast.makeText(this, "Select a  schedule first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "There is no selected schedule.");
                } else {
                    Log.v(TAG, "Selected schedule: " + selectedPS.getProgramName() + "...");
                    ControlFactory.getScheduleController().selectEditSchedule(selectedPS);
                }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        ControlFactory.getReviewSelectProgramController().selectCancel();
    }

    public void showProgramSlots(List<ProgramSlot> programSlots) {
        mPSadapter.clear();
        for (int i = 0; i < programSlots.size(); i++) {
            mPSadapter.add(programSlots.get(i));
        }
    }
}


