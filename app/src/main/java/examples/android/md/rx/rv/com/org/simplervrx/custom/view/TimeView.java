package examples.android.md.rx.rv.com.org.simplervrx.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import examples.android.md.rx.rv.com.org.simplervrx.R;

/**
 * Created by User on 11/15/2016.
 */

public class TimeView extends TextView {
    private static final String TAG = TimeView.class.getSimpleName();
    private String titleText;
    private boolean color;

    public TimeView(Context context) {
        super(context);
        setTimeView();
    }

    private void setTimeView() {
        Log.d(TAG, "setTimeView");
        // has the format hour.minuits am/pm
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
        String time = dateFormat.format(Calendar.getInstance().getTime());

        if(this.titleText != null )
            setText(this.titleText+" "+time);
        else
            setText(time);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // retrieved values correspond to the positions of the attributes
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.TimeView);
        int count = typedArray.getIndexCount();
        try{

            for (int i = 0; i < count; ++i) {

                int attr = typedArray.getIndex(i);
                // the attr corresponds to the title attribute
                if(attr == R.styleable.TimeView_title) {

                    // set the text from the layout
                    titleText = typedArray.getString(attr);
                    setTimeView();
                } else if(attr == R.styleable.TimeView_setColor) {
                    // set the color of the attr "setColor"
                    color = typedArray.getBoolean(attr, false);
                    decorateText();
                }
            }
        }

        // the recycle() will be executed obligatorily
        finally {
            // for reuse
            typedArray.recycle();
        }
    }

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTimeView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TimeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void decorateText() {
        // when we set setColor attribute to true in the XML layout
        if(this.color){
            // set the characteristics and the color of the shadow
            setShadowLayer(4, 2, 2, Color.rgb(250, 00, 250));
            setBackgroundColor(Color.BLACK);
        } else {
            setBackgroundColor(Color.RED);
        }
    }
}
