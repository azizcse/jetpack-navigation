package com.w3engineers.jitpackbottomnav;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.w3engineers.jitpackbottomnav.data.model.MyObjectBox;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class App extends Application {
    private static Context context;
    private static BoxStore boxStore;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        boxStore = MyObjectBox.builder().androidContext(this).build();
        if (BuildConfig.DEBUG) {
            boolean started = new AndroidObjectBrowser(boxStore).start(this);
            Log.i("ObjectBrowser", "Started: " + started);
        }
    }

    public static Context getContext(){
        return context;
    }
    public static BoxStore getBoxStore(){
        return boxStore;
    }

}
