package examples.android.md.rx.rv.com.org.simplervrx.asyncTask;

import org.json.JSONArray;

/**
 * Created by User on 11/9/2016.
 *
 * This interface is for communicate the JsonAsyncTask.java with the AsyncTaskFragment
 *
 * AsyncTaskFragment implements this interface
 *
 */

public interface AsyncResponse {
    void processFinished(JSONArray jsonArray);
}
