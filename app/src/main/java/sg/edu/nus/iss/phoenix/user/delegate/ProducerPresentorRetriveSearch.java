package sg.edu.nus.iss.phoenix.user.delegate;


import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;


import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper;
import sg.edu.nus.iss.phoenix.user.controller.ReviewSelectProducerPresentorController;
import sg.edu.nus.iss.phoenix.user.entity.*;
import sg.edu.nus.iss.phoenix.schedule.android.controller.ScheduleController;

public class ProducerPresentorRetriveSearch extends  AsyncTask<String, Void, String> {

    private ReviewSelectProducerPresentorController controller;
    private String type;
    private  ScheduleController scheduleController=null;

    public ProducerPresentorRetriveSearch(ReviewSelectProducerPresentorController controller, String type) {
        this.controller = controller;
        this.type = type;
    }

    public ProducerPresentorRetriveSearch(ScheduleController scheduleController, String type)
    {
        this.scheduleController= scheduleController;
        this.type = type;
    }

    @Override
    protected String doInBackground(String ...params) {
        Uri builder = Uri.parse(this.getUrl(this.type)).buildUpon().build();
        URL url = null;
        String jsonResp = "";
        HttpURLConnection urlConnection = null;
        Log.i("[ReviewSelects]", params.toString());
        if(params.length >= 2){
            Uri buildURL = Uri.withAppendedPath(builder, "search?q="+params[1]).buildUpon().build();
            try {
                url = new URL(buildURL.toString());
            }
            catch(MalformedURLException ex) {
                Log.i("[ReviewSelectERror]", ex.toString());
                return "";
            }

        } else {
            Uri buildURL = Uri.withAppendedPath(builder, "all").buildUpon().build();
            try {
                url = new URL(buildURL.toString());
            }
            catch(MalformedURLException ex) {
                Log.i("[ReviewSelectERror]", ex.toString());
                return "";
            }
        }
        try {
            urlConnection = (HttpURLConnection)  url.openConnection();
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) jsonResp = scanner.next();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                 urlConnection.disconnect();
        }
        return jsonResp;
    }

    private String getUrl(String type) {
        if (type == "producer") {
            return DelegateHelper.PRMS_BASE_URL_PRODUCER;
        } else {
            return DelegateHelper.PRMS_BASE_URL_PRESENTER;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        List<User> users = new ArrayList<User>();

        if (result != null && !result.equals("")) {
            try {
                JSONArray rpArray = new JSONArray(result);

                for (int i = 0; i < rpArray.length(); i++) {
                    JSONObject userJson = rpArray.getJSONObject(i);
                    String id = (String) userJson.getString("id");
                    String name = (String) userJson.getString("name");

                    users.add(new User(id, name));
                }
            } catch (JSONException e) {
                Log.v("[ReviewSelect JSON]", e.getMessage());
            }
        } else {
            Log.v("[ReviewSelect JSON]", "JSON response error.");
        }

        if (controller != null)
            controller.gotUsers(users);
        else if (scheduleController !=null) {
            if(this.type=="producer")
            scheduleController.setProducers(users);
            else
                scheduleController.setPresenters(users);
        }
    }


}
