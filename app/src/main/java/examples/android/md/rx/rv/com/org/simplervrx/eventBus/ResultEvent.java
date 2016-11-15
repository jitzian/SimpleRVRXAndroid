package examples.android.md.rx.rv.com.org.simplervrx.eventBus;

import examples.android.md.rx.rv.com.org.simplervrx.model.Result;

/**
 * Created by User on 11/12/2016.
 */

public class ResultEvent {

    private Result result;

    public ResultEvent(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
