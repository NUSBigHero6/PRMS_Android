package sg.edu.nus.iss.phoenix.user.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.user.android.ui.MaintainUserScreen;
import sg.edu.nus.iss.phoenix.user.android.ui.UserListScreen;
import sg.edu.nus.iss.phoenix.user.delegate.CreateUserDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.RetrieveUsersDelegate;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by wengweichen on 25/9/18.
 */

public class UserController {
    // Tag for logging.
    private static final String TAG = UserController.class.getName();

    private UserListScreen userListScreen;
    private MaintainUserScreen maintainUserScreen;
    private User user2edit = null;

    public void onDisplayUserList(UserListScreen userListScreen) {
        this.userListScreen = userListScreen;
        new RetrieveUsersDelegate(this).execute("all");
    }

    public void usersRetrieved(List<User> users) {
        Log.v("Users Retrieved",users.toString());
        userListScreen.showUsers(users);
    }

    public void startUseCase() {
        Intent intent = new Intent(MainController.getApp(), UserListScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayUser(MaintainUserScreen maintainUserScreen){
        this.maintainUserScreen = maintainUserScreen;
        if (user2edit == null)
            maintainUserScreen.createUser();
        else
            maintainUserScreen.editUser(user2edit);
    }

    public void selectCreateUser(){
        Intent intent = new Intent(MainController.getApp(), MaintainUserScreen.class);
        MainController.displayScreen(intent);
    }

    public void maintainedUser() {
        ControlFactory.getMainController().maintainedProgram();
    }

    public void selectCreateUser(User user){
        new CreateUserDelegate(this).execute(user);

    }

    public void userCreated(boolean success) {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

    public void selectEditUser(User user){
        user2edit = user;
        Log.v(TAG, "Editing radio program: " + user.getName() + "...");
        Intent intent = new Intent(MainController.getApp(), MaintainUserScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectCancelCreateEditUser() {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

}
