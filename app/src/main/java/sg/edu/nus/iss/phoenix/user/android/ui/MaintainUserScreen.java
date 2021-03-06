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

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class MaintainUserScreen extends AppCompatActivity {

    // Tag for logging
    private static final String TAG = MaintainUserScreen.class.getName();

    private EditText mIdEditText;
    private EditText mUserNamEditText;
    private EditText mUserPasswordEditText;
    private TextView passwordLabel;
    private CheckBox mManagerRoleCheckbox;
    private CheckBox mProducerRoleCheckbox;
    private CheckBox mPresenterRoleCheckbox;
    private CheckBox mAdminRoleCheckbox;
    private User editUser = null;
    private ArrayList<Role> roles = new ArrayList<Role>();
    KeyListener mUserNameEditTextKeyListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_user);

        // Find all relevant views that we will need to read user input from
        mIdEditText = (EditText) findViewById(R.id.maintain_id_text_view);
        mUserNamEditText = (EditText) findViewById(R.id.maintain_user_name_text_view);
        mUserPasswordEditText = (EditText) findViewById(R.id.maintain_user_password_text_view);
        passwordLabel  = (TextView) findViewById(R.id.maintain_user_password_label);
        mManagerRoleCheckbox  = (CheckBox) findViewById(R.id.checkbox_manager);
        mProducerRoleCheckbox  = (CheckBox) findViewById(R.id.checkbox_producer);
        mPresenterRoleCheckbox  = (CheckBox) findViewById(R.id.checkbox_presenter);
        mAdminRoleCheckbox  = (CheckBox) findViewById(R.id.checkbox_administrator);

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

        mIdEditText.setText("", TextView.BufferType.EDITABLE);
        mUserNamEditText.setText("", TextView.BufferType.EDITABLE);
        mUserNamEditText.setKeyListener(mUserNameEditTextKeyListener);
    }

    public void editUser(User editUser) {
        this.editUser = editUser;
        if (editUser != null) {
            mUserPasswordEditText.setVisibility(View.INVISIBLE);
            passwordLabel.setVisibility(View.INVISIBLE);

            mIdEditText.setText(editUser.getId(), TextView.BufferType.NORMAL);
            mIdEditText.setKeyListener(null);

            mUserNamEditText.setText(editUser.getName(), TextView.BufferType.EDITABLE);

            for(int i = 0; i < editUser.getRoles().size(); i++) {
                String role = editUser.getRoles().get(i).getRole();

                if(role.equals("manager")) {
                    mManagerRoleCheckbox.setChecked(true);
                    roles.add(new Role("manager", "all"));
                }
                else if(role.equals("producer")) {
                    mProducerRoleCheckbox.setChecked(true);
                    roles.add(new Role("producer", "all"));
                }
                else if(role.equals("presenter")) {
                    mPresenterRoleCheckbox.setChecked(true);
                    roles.add(new Role("presenter", "all"));
                }
                else if(role.equals("admin")) {
                    mAdminRoleCheckbox.setChecked(true);
                    roles.add(new Role("admin", "all"));
                }
            }
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
                    User user = new User(mIdEditText.getText().toString(),
                                    mUserNamEditText.getText().toString(),
                                    mUserPasswordEditText.getText().toString(),
                                    roles);
                    ControlFactory.getUserController().selectCreateUser(user);
                }
                else { // Edited.
                    editUser.setName(mUserNamEditText.getText().toString());
                    editUser.setRoles(roles);
                    ControlFactory.getUserController().selectUpdateUser(editUser);
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                Log.v(TAG, "Deleting user ");
                ControlFactory.getUserController().selectDeleteUser(editUser);
                return true;
            // Respond to a click on the "Cancel" menu option
            case R.id.action_cancel:
                Log.v(TAG, "Canceling creating/editing user...");
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
                    roles.add(new Role("manager", "all"));
                }
                else {
                    roles.remove(findRoleIndex("manager"));
                }
                break;
            case R.id.checkbox_presenter:
                if (checked) {
                    roles.add(new Role("presenter", "all"));
                }
                else{
                    roles.remove(findRoleIndex("presenter"));
                }
                break;
            case R.id.checkbox_producer:
                if (checked) {
                    roles.add(new Role("producer", "all"));
                }
                else{
                    roles.remove(findRoleIndex("producer"));
                }
                    break;
            case R.id.checkbox_administrator:
                if (checked) {
                    roles.add(new Role("admin", "all"));
                }
                else{
                    roles.remove(findRoleIndex("admin"));
                }
                    break;
        }
    }

    protected String getUserId() {
        return mUserNamEditText.getText().toString().split("\\s+")[0];
    }

    protected int findRoleIndex(String role) {
        for(int i =0; i < roles.size(); i++) {
            if(roles.get(i).getRole() == role) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {
        Log.v(TAG, "Canceling creating/editing user...");
        ControlFactory.getUserController().selectCancelCreateEditUser();
    }

}
