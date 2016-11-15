package examples.android.md.rx.rv.com.org.simplervrx.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GithubService extends IntentService{
    private static final String TAG = GithubService.class.getName();
    private HttpURLConnection httpURLConnection;

    public GithubService(){
        super(GithubService.class.getName());
    }

    public GithubService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
        StringBuilder stringBuilder = new StringBuilder();
        JSONArray jsonArray;
        try {
            URL url = new URL("https://api.github.com/users/jitzian/repos");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            jsonArray = new JSONArray(stringBuilder.toString());
            prepareSending(jsonArray);

            stopSelf();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

    }

    public void prepareSending(JSONArray jsonArray){
        Log.d(TAG, "prepareSendind");
        Intent intent = new Intent().setAction("github.service.update.ui");
        intent.putExtra("jsonArray", jsonArray.toString());
        sendBroadcast(intent);
    }
}
