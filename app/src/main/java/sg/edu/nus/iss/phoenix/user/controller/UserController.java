package sg.edu.nus.iss.phoenix.user.controller;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.user.android.ui.MaintainUserScreen;
import sg.edu.nus.iss.phoenix.user.android.ui.UserListScreen;
import sg.edu.nus.iss.phoenix.user.delegate.CreateUserDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.RetrieveUsersDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.DeleteUserDelegate;
import sg.edu.nus.iss.phoenix.user.delegate.UpdateUserDelegate;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by wengweichen on 25/9/18.
 */

public class UserController {
    // Tag for logging.
    private static final String TAG = UserController.class.getName();

    private UserListScreen userListScreen;
    private MaintainUserScreen maintainUserScreen;
    private User editUser = null;

    public void onDisplayUserList(UserListScreen userListScreen) {
        this.userListScreen = userListScreen;
        new RetrieveUsersDelegate(this).execute("all");
    }

    public void onDisplayUser(MaintainUserScreen maintainUserScreen) {
        this.maintainUserScreen = maintainUserScreen;
        if (editUser == null)
            maintainUserScreen.createUser();
        else
            maintainUserScreen.editUser(editUser);
    }

    public void usersRetrieved(List<User> users) {
        userListScreen.showUsers(users);
    }

    public void selectCreateUser() {
        editUser = null;
        Intent intent = new Intent(MainController.getApp(), MaintainUserScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectCreateUser(User user) {
        new CreateUserDelegate(this).execute(user);
    }

    public void userCreated(boolean success) {
        startUseCase();
    }

    public void selectUpdateUser(User user) {
        new UpdateUserDelegate(this).execute(user);
    }

    public void selectEditUser(User user){
        editUser = user;
        Intent intent = new Intent(MainController.getApp(), MaintainUserScreen.class);
        MainController.displayScreen(intent);
    }

    public void userUpdated(boolean success){
        startUseCase();
    }

    public void startUseCase() {
        Intent intent = new Intent(MainController.getApp(), UserListScreen.class);
        MainController.displayScreen(intent);
    }

    public void selectDeleteUser(User user) {
        new DeleteUserDelegate(this).execute(user.getId());
    }

    public void selectCancelCreateEditUser() {
        startUseCase();
    }

    public void maintainedUser() {
        ControlFactory.getMainController().maintainedUser();
    }

    public void userDeleted(boolean success){
        startUseCase();
    }
}
