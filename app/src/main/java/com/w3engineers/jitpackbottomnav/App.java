package com.w3engineers.jitpackbottomnav;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public Context getContext(){
        return context;
    }


}
