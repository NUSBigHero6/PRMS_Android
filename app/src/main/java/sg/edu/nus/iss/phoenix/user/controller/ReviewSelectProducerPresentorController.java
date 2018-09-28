package sg.edu.nus.iss.phoenix.user.controller;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.user.android.ui.ReviewSelectScreen;
import sg.edu.nus.iss.phoenix.user.delegate.ProducerPresentorRetriveSearch;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class ReviewSelectProducerPresentorController {

    private static final String TAG = "[ReviewSelectProducerPresentor]";
    private ReviewSelectScreen rScreen ;
    private String searchPrefix = null;


    public void onDisplayUserList(ReviewSelectScreen rsScreen, String type) {
        this.rScreen = rsScreen;
        if (this.searchPrefix != null) {
            new ProducerPresentorRetriveSearch(this, type).execute("all");
        }
        else {
            new ProducerPresentorRetriveSearch(this, type).execute("search", this.searchPrefix);
        }
    }

    public void setText(String prefix) {
        this.searchPrefix = prefix;
    }

    public void startUseCase(String type){
        Intent intent = new Intent(MainController.getApp(), ReviewSelectScreen.class);
        intent.putExtra("type", type);
        MainController.displayScreen(intent);
    }

    public void gotUsers(List<User> users) {
        rScreen.displayUser(users);
    }
}
