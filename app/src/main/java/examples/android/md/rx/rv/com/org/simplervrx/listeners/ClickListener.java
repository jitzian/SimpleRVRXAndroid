package examples.android.md.rx.rv.com.org.simplervrx.listeners;

import android.view.View;

/**
 * Created by User on 11/5/2016.
 * This interface needs also to be defined in order to get the listener of the REcyclerView
 *
 */

public interface ClickListener {
    void onClick(View view,int position);
    void onLongClick(View view, int position);
}
