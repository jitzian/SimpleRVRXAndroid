package examples.android.md.rx.rv.com.org.simplervrx.asyncTask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 11/9/2016.
 */

public class JsonAsyncTask extends AsyncTask<Void, Void, JSONArray> {
    private static final String TAG = JsonAsyncTask.class.getName();
    private String paramURL;
    public AsyncResponse asyncResponse;

    public JsonAsyncTask(String paramURL) {
        super();
        this.paramURL = paramURL;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d(TAG, "onCancelled");
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground::" + paramURL);

        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection httpURLConnection = null;
        JSONArray jsonArray = null;
        try {
            URL url = new URL(paramURL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            jsonArray = new JSONArray(stringBuilder.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            assert httpURLConnection != null;
            httpURLConnection.disconnect();
        }
        return jsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        Log.d(TAG, "onPostExecute::jsonArray::" + jsonArray);
        asyncResponse.processFinished(jsonArray);

    }

}
