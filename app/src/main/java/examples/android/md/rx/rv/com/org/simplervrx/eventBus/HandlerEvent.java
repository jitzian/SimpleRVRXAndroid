package examples.android.md.rx.rv.com.org.simplervrx.eventBus;

/**
 * Created by User on 11/11/2016.
 */

public class HandlerEvent {
    private String message;

    public HandlerEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
