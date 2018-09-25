package sg.edu.nus.iss.phoenix.schedule.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sg.edu.nus.iss.phoenix.schedule.android.controller.ScheduleController;
import sg.edu.nus.iss.phoenix.schedule.android.controller.ReviewSelectScheduleController;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_RADIO_PROGRAM;

/**
 * Created by liu.cao on 18/9/2018.
 */

public class RetrieveSchedulesDelegate extends AsyncTask<String, Void, String> {
    // Tag for logging
    private static final String TAG = RetrieveSchedulesDelegate.class.getName();

    private ScheduleController scheduleController = null;
    private ReviewSelectScheduleController reviewSelectScheduleController = null;

    public RetrieveSchedulesDelegate(ScheduleController scheduleController) {
        this.reviewSelectScheduleController = null;
        this.scheduleController = scheduleController;
    }

    public RetrieveSchedulesDelegate(ReviewSelectScheduleController reviewSelectScheduleController) {
        this.scheduleController = null;
        this.reviewSelectScheduleController = reviewSelectScheduleController;
    }

    @Override
    protected String doInBackground(String... params) {
        Uri builtUri1 = Uri.parse(PRMS_BASE_URL_RADIO_PROGRAM).buildUpon().build();

        Uri builtUri = Uri.withAppendedPath(builtUri1, params[0]).buildUpon().build();
        Log.v(TAG, builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.v(TAG, e.getMessage());
            return e.getMessage();
        }

        String jsonResp = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) {
                //hardcode here , waiting for backend service
                // jsonResp = scanner.next();
                jsonResp = "{\"psList\":[{\"date\":\"2018-9-20\",\"producername\":\"allen\",\"presentername\":\"jim\",\"programname\":\"TV show\",\"starttime\":\"9pm\",\"duration\":\"00:30:00+07:30\"},{\"date\":\"2018-9-21\",\"producername\":\"nick\",\"presentername\":\"mary\",\"programname\":\"music show\",\"starttime\":\"10pm\",\"duration\":\"00:30:00+07:30\"}]}";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

        return jsonResp;
    }

    @Override
    protected void onPostExecute(String result) {
        List<ProgramSlot> programSlots = new ArrayList<>();

        if (result != null && !result.equals("")) {
            try {
                JSONObject reader = new JSONObject(result);
                JSONArray rpArray = reader.getJSONArray("psList");

                for (int i = 0; i < rpArray.length(); i++) {
                    JSONObject psJson = rpArray.getJSONObject(i);
                    String date = psJson.getString("date");
                    String producername = psJson.getString("producername");
                    String presentername = psJson.getString("presentername");
                    String programname = psJson.getString("programname");
                    String starttime = psJson.getString("starttime");
                    String duration = psJson.getString("duration");
                    programSlots.add(new ProgramSlot(date, programname, producername, presentername, starttime, duration));
                }
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }
        } else {
            Log.v(TAG, "JSON response error.");
        }

        if (scheduleController != null)
            scheduleController.SchedulesRetrieved(programSlots);
        else if (reviewSelectScheduleController != null)
            reviewSelectScheduleController.schedulesRetrieved(programSlots);
    }

}
