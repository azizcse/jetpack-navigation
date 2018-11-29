package com.w3engineers.jitpackbottomnav;

import android.app.Application;
import android.content.Context;
import com.w3engineers.jitpackbottomnav.data.model.MyObjectBox;
import io.objectbox.BoxStore;

public class App extends Application {
    private static Context context;
    private static BoxStore boxStore;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public Context getContext(){
        return context;
    }
    public BoxStore getBoxStore(){
        return boxStore;
    }

}
