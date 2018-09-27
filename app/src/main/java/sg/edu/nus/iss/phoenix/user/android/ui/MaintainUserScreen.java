package sg.edu.nus.iss.phoenix.user.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by priyankajalan on 25/9/18.
 */

public class MaintainUserScreen extends AppCompatActivity {
    // Tag for logging
    private static final String TAG = MaintainUserScreen.class.getName();

    private User user2edit = null;
    private EditText userName;
    private EditText userRole;
    KeyListener mUserNameEditTextKeyListener = null;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getUserController().onDisplayUser(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Find all relevant views that we will need to read user input from
        userName = (EditText) findViewById(R.id.user_name_text_view);
        userRole = (EditText) findViewById(R.id.user_role_text_view);
        // Keep the KeyListener for name EditText so as to enable editing after disabling it.
        mUserNameEditTextKeyListener = userName.getKeyListener();
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
        if (user2edit == null) {
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
                // Save user.
                if (user2edit == null) { // Newly created.
                    Log.v(TAG, "Saving user " + userName.getText().toString());
                    // Set User Information
                    User user = new User(userName.getText().toString(),userName.getText().toString());
                    Role role = new Role();
                    role.setRole(userRole.getText().toString());
                    role.setAccessPrivilege(userRole.getText().toString());
                    ControlFactory.getUserController().selectCreateUser(user);
                }
                else { // Edited.
                    Log.v(TAG, "Saving user " + userName.getText() + "...");
//                    ControlFactory.getProgramController().selectUpdateProgram(rp2edit);
                }
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                Log.v(TAG, "Deleting user ");
//                ControlFactory.getProgramController().selectDeleteProgram(rp2edit);
                return true;
            // Respond to a click on the "Cancel" menu option
            case R.id.action_cancel:
                Log.v(TAG, "Canceling creating/editing user...");
                ControlFactory.getUserController().selectCancelCreateEditUser();
                return true;
        }

        return true;
    }

    public void createUser(){
        this.user2edit = null;
        userName.setText("", TextView.BufferType.EDITABLE);
        userRole.setText("",TextView.BufferType.EDITABLE);
        userName.setKeyListener(mUserNameEditTextKeyListener);
    }

    public void editUser(User user2edit){
        this.user2edit = user2edit;
        userName.setText(user2edit.getName(),TextView.BufferType.EDITABLE);
        userRole.setText("manager",TextView.BufferType.EDITABLE);
        userName.setKeyListener(null);
    }

    @Override
    public void onBackPressed() {
        Log.v(TAG, "Canceling creating/editing user...");
        ControlFactory.getUserController().selectCancelCreateEditUser();
    }

}
