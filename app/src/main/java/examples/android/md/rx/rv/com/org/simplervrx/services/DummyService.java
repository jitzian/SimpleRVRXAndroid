package examples.android.md.rx.rv.com.org.simplervrx.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import org.greenrobot.eventbus.EventBus;

import examples.android.md.rx.rv.com.org.simplervrx.eventBus.HandlerEvent;

/**
 * Created by User on 11/11/2016.
 */

public class DummyService extends Service {
    private static final String TAG = DummyService.class.getName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        Bundle bundle = intent.getExtras();
        String strParameter = bundle.getString("parameter");

        strParameter += " adding something else";
        EventBus.getDefault().post(new HandlerEvent(strParameter));

        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
}
