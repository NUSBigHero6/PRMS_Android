package sg.edu.nus.iss.phoenix.user.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.user.controller.UserController;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_USER;

/**
 * Created by priyankajalan on 25/9/18.
 */

public class CreateUserDelegate extends AsyncTask<User, Void, Boolean> {
    // Tag for logging
    private static final String TAG = CreateUserDelegate.class.getName();

    private final UserController userController;

    public CreateUserDelegate(UserController userController) {
        this.userController = userController;
    }

    @Override
    protected Boolean doInBackground(User... params) {
        Uri builtUri1 = Uri.parse(PRMS_BASE_URL_USER).buildUpon().build();
        Uri builtUri = Uri.withAppendedPath(builtUri1, "create").buildUpon().build();
        Log.v(TAG, builtUri.toString());
        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return new Boolean(false);
        }
        JSONObject userJson = new JSONObject();
        JSONObject userRoleJson = new JSONObject();
        JSONArray userRoles = new JSONArray();
        try {
            userRoleJson.put("role", "manager");
            userRoleJson.put("accessPrivilege","manager");
            userRoles.put(userRoleJson);

            userJson.put("id", params[0].getId());
            userJson.put("name", params[0].getName());
            userJson.put("roles",userRoles);

        } catch (JSONException e) {
            Log.v(TAG, e.getMessage());
        }

        boolean success = false;
        HttpURLConnection httpURLConnection = null;
        DataOutputStream dos = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.writeUTF(userJson.toString());
            dos.write(256);
            Log.v(TAG, "Http POST response " + httpURLConnection.getResponseCode());
            success = true;
        } catch (IOException exception) {
            Log.v(TAG, exception.getMessage());
        } finally {
            if (dos != null) {
                try {
                    dos.flush();
                    dos.close();
                } catch (IOException exception) {
                    Log.v(TAG, exception.getMessage());
                }
            }
            if (httpURLConnection != null) httpURLConnection.disconnect();
        }
        return new Boolean(success);
    }

}
