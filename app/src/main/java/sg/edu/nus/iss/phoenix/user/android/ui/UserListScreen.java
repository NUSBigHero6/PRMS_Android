package sg.edu.nus.iss.phoenix.user.android.ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.android.ui.UserAdapter;
import sg.edu.nus.iss.phoenix.schedule.android.ui.ScheduleListScreen;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class UserListScreen extends AppCompatActivity {

    // Tag for logging
    private static final String TAG = ScheduleListScreen.class.getName();

    private ListView mListView;
    private UserAdapter mUserAdapter;
    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        mUserAdapter = new UserAdapter(this, arrayOfUsers);
        mListView = (ListView) findViewById(R.id.user_list);
        mListView.setAdapter(mUserAdapter);

        // Setup the item selection listener
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedUser = (User) adapterView.getItemAtPosition(position);
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
        ControlFactory.getUserController().onDisplayUserList(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "View" menu option
            case R.id.action_view:
                if (selectedUser == null) {
                    // Prompt for the selection of a radio program.
                    Toast.makeText(this, "Select a user first! Use arrow keys on emulator", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, "There is no selected user.");
                } else {
                    Log.v(TAG, "Viewing user: " + selectedUser.getName() + "...");
                    //ControlFactory.getScheduleController().selectEditSchedule(selectedPS);
                }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        ControlFactory.getUserController().maintainedUser();
    }

    public void showUsers(List<User> users) {
        mUserAdapter.clear();
        for (int i = 0; i < users.size(); i++) {
            mUserAdapter.add(users.get(i));
        }
    }
}
