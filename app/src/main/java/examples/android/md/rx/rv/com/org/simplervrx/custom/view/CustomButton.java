package examples.android.md.rx.rv.com.org.simplervrx.custom.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by User on 11/15/2016.
 */

public class CustomButton extends Button {
    private static final String TAG = CustomButton.class.getSimpleName();
    private boolean color;
    public CustomButton(Context context) {
        super(context);
        setBackgroundColor(Color.DKGRAY);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.DKGRAY);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.DKGRAY);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setBackgroundColor(Color.CYAN);
    }
    private void decorateText() {
        // when we set setColor attribute to true in the XML layout
        if(this.color){
            // set the characteristics and the color of the shadow
            setShadowLayer(4, 2, 2, Color.rgb(250, 00, 250));
            setBackgroundColor(Color.DKGRAY);
        } else {
            setBackgroundColor(Color.RED);
        }
    }
}
