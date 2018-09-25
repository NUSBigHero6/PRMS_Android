package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

public class ScheduleScreen extends AppCompatActivity implements View.OnClickListener {
    // Tag for logging
    private static final String TAG = ScheduleScreen.class.getName();
    private EditText programNameText;
    KeyListener mRPNameEditTextKeyListener = null;
    private ProgramSlot rp2edit = null;
    private EditText mSStartTimeEditText;
    private EditText mSProgramDateEditText;
    private EditText producerEditorText;
    private EditText presenterEditorText;

    String[] programs = {"News", "Movie", "Drama"};
    String[] producers = {"Me", "You", "We"};
    String[] presenters = {"She", "he", "Us"};
    String[] duration = {"00:30:00", "01:00:00", "01:30:00"};
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        // Keep the KeyListener for name EditText so as to enable editing after disabling it.
        programNameText = (EditText) findViewById(R.id.maintain_program_text_view);
        mRPNameEditTextKeyListener = programNameText.getKeyListener();

        mSProgramDateEditText = (EditText) findViewById(R.id.maintain_Date_text_view);

        mSStartTimeEditText = (EditText) findViewById(R.id.maintain_starttime_text_view);
        producerEditorText = (EditText) findViewById(R.id.maintain_producer_text_view);
        producerEditorText.setClickable(true);
        producerEditorText.setOnClickListener(this);

        presenterEditorText = (EditText) findViewById(R.id.maintain_presenter_text_view);
        presenterEditorText.setClickable(true);
        presenterEditorText.setOnClickListener(this);

        programNameText.setClickable(true);
        programNameText.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.maintain_producer_text_view:
                listView = new ListView(this);
                showDialogListView(v, "Producer list"); //improve if time permit
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, producers);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ViewGroup vg = (ViewGroup) view;
                        String producer = producers[i];
                        producerEditorText.setText(producer);
                    }
                });
                break;

            case R.id.maintain_presenter_text_view:
                listView = new ListView(this);
                showDialogListView(v, "Presenter list"); //improve if time permit
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, presenters);
                listView.setAdapter(adapter1);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ViewGroup vg = (ViewGroup) view;
                        String presenter = presenters[i];
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
                        String program = programs[i];
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
        // If this is a new radioprogram, hide the "Delete" menu item.
        if (rp2edit == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save radio program.
                if (rp2edit == null) { // Newly created.
                    Log.v(TAG, "Saving radio program " + programNameText.getText().toString() + "...");

                } else { // Edited.
                    Log.v(TAG, "Saving radio program " + rp2edit.getProgramName() + "...");


                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                Log.v(TAG, "Deleting radio program " + rp2edit.getProgramName() + "...");
                return true;
            // Respond to a click on the "Cancel" menu option
            case R.id.action_cancel:
                Log.v(TAG, "Canceling creating/editing radio program...");
                return true;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Log.v(TAG, "Canceling creating/editing radio program...");
        //ControlFactory.getProgramController().selectCancelCreateEditProgram();
    }

    public void createProgramSlot() {
        this.rp2edit = null;
        programNameText.setText("", TextView.BufferType.EDITABLE);

        programNameText.setKeyListener(mRPNameEditTextKeyListener);
    }

    public void editProgramSlot(ProgramSlot rp2edit) {
        this.rp2edit = rp2edit;
        if (rp2edit != null) {
            programNameText.setText(rp2edit.getProgramName(), TextView.BufferType.NORMAL);
            programNameText.setKeyListener(null);
        }
    }
}

