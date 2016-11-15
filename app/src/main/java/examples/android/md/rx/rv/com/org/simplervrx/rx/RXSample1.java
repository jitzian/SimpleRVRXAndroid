package examples.android.md.rx.rv.com.org.simplervrx.rx;

import java.util.List;

import rx.Observable;

/**
 * Created by User on 11/13/2016.
 */

public class RXSample1 {

    public static Observable<List<String>>getEnhancedList(List<String>lstString){

        for(int i = 0; i < lstString.size(); i ++){
            String temp = lstString.get(i) + "ENHANCED";
            lstString.remove(i);
            lstString.add(temp);
        }
        return (Observable<List<String>>) lstString;

    }

}
