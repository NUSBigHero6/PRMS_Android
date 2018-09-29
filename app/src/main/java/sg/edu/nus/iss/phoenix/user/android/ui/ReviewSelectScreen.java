package sg.edu.nus.iss.phoenix.user.android.ui;

import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.user.entity.User;


public class ReviewSelectScreen extends AppCompatActivity {

    private EditText searchBar;
    private ListView listItems;
    private UserAdapter userAdapter;
    private User selectedUser;
    private FloatingActionButton fab;
    private static final String TAG = "[ReviewSelectScreen]";
    private String type;
    private String searchText = null;

    private ReviewSelectActionEvent listener = null;

    public interface ReviewSelectActionEvent {
        void onBackPressed();
        void onItemSelected(User user);
    }


    public void setReviewSelectListeners(ReviewSelectActionEvent e){
        this.listener = e;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_select_prodpres);
        this.searchBar = (EditText) findViewById(R.id.review_select_search_bar);
        this.listItems = (ListView) findViewById(R.id.review_select_pm_list);
        this.fab = (FloatingActionButton) findViewById(R.id.selectUser);
        this.type = (String) getIntent().getStringExtra("type");

        this.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ReviewSelectScreen.this.searchText = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ReviewSelectScreen.this.searchUsers(ReviewSelectScreen.this.searchText);
            }
        });
        // get all data

        ArrayList<User> users = new ArrayList<>();
        userAdapter = new UserAdapter(this, users);
        listItems.setAdapter(userAdapter);

        // wiring the adapters
        listItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedUser = (User) adapterView.getItemAtPosition(position);
                if(listener != null){
                    listener.onItemSelected(selectedUser);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // your stuff
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlFactory.getReviewSelectProducerPresentorController();
            }
        });
    }



    @Override
    public void onBackPressed(){
//        ControlFactory.getUserController();
        if(this.listener != null) {
            listener.onBackPressed();
        }
    }

    public void displayUser(List<User> users) {
        userAdapter.clear();
        for (int i = 0; i < users.size(); i++) {
            userAdapter.add(users.get(i));
        }
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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        listItems.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listItems.setSelection(0);
        ControlFactory.getReviewSelectProducerPresentorController().onDisplayUserList(this, this.type);
    }

    public void searchUsers(String text){
        ControlFactory.getReviewSelectProducerPresentorController().searchUsers(text, this.type);
    }

}
