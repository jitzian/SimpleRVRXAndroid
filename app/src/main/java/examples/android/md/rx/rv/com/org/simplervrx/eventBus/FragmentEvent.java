package examples.android.md.rx.rv.com.org.simplervrx.eventBus;

/**
 * Created by User on 11/14/2016.
 */

public class FragmentEvent {
    private String message;
    public FragmentEvent(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
