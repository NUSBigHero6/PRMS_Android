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

    public void onDisplayUserList(UserListScreen userListScreen) {
        this.userListScreen = userListScreen;
        new RetrieveUsersDelegate(this).execute("all");
    }

    public void usersRetrieved(List<User> users) {
        userListScreen.showUsers(users);
    }

    public void startUseCase() {
        Intent intent = new Intent(MainController.getApp(), UserListScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectCreateUser(){
        Intent intent = new Intent(MainController.getApp(), MaintainUserScreen.class);
        MainController.displayScreen(intent);
    }

    public void maintainedUser() {
        ControlFactory.getMainController().selectMaintainUser();
    }

    public void selectCreateUser(User user){
        new CreateUserDelegate(this).execute(user);

    }

    public void userCreated(boolean success) {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

    public void selectCancelCreateEditUser() {
        // Go back to ProgramList screen with refreshed programs.
        startUseCase();
    }

}
