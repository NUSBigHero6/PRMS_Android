package sg.edu.nus.iss.phoenix.radioprogram.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

public class ReviewSelectProgramScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = ReviewSelectProgramScreen.class.getName();

    private RadioProgramAdapter mRPAdapter;
    // private ArrayAdapter<String> adapter = null;
    private ListView mListView;
    private RadioProgram selectedRP = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_select_program);

        ArrayList<RadioProgram> radioPrograms = new ArrayList<RadioProgram>();
        // ArrayList<String> radioProgramNames = new ArrayList<String>();
        // mRPAdapter = new ArrayAdapter<String>(this, R.layout.activity_review_select_program,
        //        R.id.maintain_program_name_text_view, radioProgramNames);
        mRPAdapter = new RadioProgramAdapter(this, radioPrograms);

        mListView = (ListView) findViewById(R.id.review_select_pm_list);
        mListView.setAdapter(mRPAdapter);

        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Log.v(TAG, "Radio program at position " + position + " selected.");
                RadioProgram rp = (RadioProgram) adapterView.getItemAtPosition(position);
                // Log.v(TAG, "Radio program name is " + rp.getRadioProgramName());
                selectedRP = rp;
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

        ControlFactory.getReviewSelectProgramController().onDisplay(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_review_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "View" menu option
            case R.id.action_select:
                if (selectedRP == null) {
                    // Prompt for the selection of a radio program.
                    Toast.makeText(this, "Select a radio program first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "There is no selected radio program.");
                }
                else {
                    Log.v(TAG, "Selected radio program: " + selectedRP.getRadioProgramName() + "...");
                    ControlFactory.getReviewSelectProgramController().selectProgram(selectedRP);
                }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        ControlFactory.getReviewSelectProgramController().selectCancel();
    }

    public void showPrograms(List<RadioProgram> radioPrograms) {
        mRPAdapter.clear();
        for (int i = 0; i < radioPrograms.size(); i++) {
            mRPAdapter.add(radioPrograms.get(i));
        }
    }
}

