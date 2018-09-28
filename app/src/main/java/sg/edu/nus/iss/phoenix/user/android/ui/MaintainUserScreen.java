package sg.edu.nus.iss.phoenix.user.android.ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.UUID;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class MaintainUserScreen extends AppCompatActivity {

    // Tag for logging
    private static final String TAG = MaintainUserScreen.class.getName();

    private EditText mUserNamEditText;
    private EditText mUserPasswordEditText;
    private TextView passwordLabel;
    private User editUser = null;
    private ArrayList<Role> roles = new ArrayList<Role>();
    KeyListener mUserNameEditTextKeyListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_user);

        // Find all relevant views that we will need to read user input from
        mUserNamEditText = (EditText) findViewById(R.id.maintain_user_name_text_view);
        mUserPasswordEditText = (EditText) findViewById(R.id.maintain_user_password_text_view);
        passwordLabel  = (TextView) findViewById(R.id.maintain_user_password_label);
        // Keep the KeyListener for name EditText so as to enable editing after disabling it.
        mUserNameEditTextKeyListener = mUserNamEditText.getKeyListener();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getUserController().onDisplayUser(this);
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

        if (editUser == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    public void createUser() {
        this.editUser = null;
        mUserNamEditText.setText("", TextView.BufferType.EDITABLE);
        mUserNamEditText.setKeyListener(mUserNameEditTextKeyListener);
    }

    public void editUser(User editUser) {
        this.editUser = editUser;
        if (editUser != null) {
            mUserPasswordEditText.setVisibility(View.INVISIBLE);
            passwordLabel.setVisibility(View.INVISIBLE);

            mUserNamEditText.setText(editUser.getName(), TextView.BufferType.NORMAL);
            mUserNamEditText.setKeyListener(null);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save radio program.
                if (editUser == null) { // Newly created.
                    Log.v(TAG, "Saving user " +
                                mUserNamEditText.getText().toString() +
                                " " + roles.size() + "...");
                    User user = new User(UUID.randomUUID().toString(),
                                    mUserNamEditText.getText().toString(),
                                    mUserPasswordEditText.getText().toString(),
                                    roles);
                    ControlFactory.getUserController().selectCreateUser(user);
                }
                else { // Edited.
                    //Log.v(TAG, "Saving radio program " + rp2edit.getRadioProgramName() + "...");
                    /*rp2edit.setRadioProgramDescription(mRPDescEditText.getText().toString());
                    rp2edit.setRadioProgramDuration(mDurationEditText.getText().toString());
                    ControlFactory.getProgramController().selectUpdateProgram(rp2edit);*/
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                //Log.v(TAG, "Deleting radio program " + rp2edit.getRadioProgramName() + "...");
                //ControlFactory.getProgramController().selectDeleteProgram(rp2edit);
                return true;
            // Respond to a click on the "Cancel" menu option
            case R.id.action_cancel:
                Log.v(TAG, "Canceling creating/editing user ...");
                ControlFactory.getUserController().selectCancelCreateEditUser();
                return true;
        }

        return true;
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_manager:
                if (checked) {
                    roles.add(new Role("manager"));
                }
                else {
                    roles.remove(new Role("manager"));
                }
                break;
            case R.id.checkbox_presenter:
                if (checked) {
                    roles.add(new Role("presenter"));
                }
                else{
                    roles.remove(new Role("presenter"));
                }
                break;
            case R.id.checkbox_producer:
                if (checked) {
                    roles.add(new Role("producer"));
                }
                else{
                    roles.add(new Role("producer"));
                }
                    break;
            case R.id.checkbox_administrator:
                if (checked) {
                    roles.add(new Role("admin"));
                }
                else{
                    roles.add(new Role("admin"));
                }
                    break;
        }
    }
}
