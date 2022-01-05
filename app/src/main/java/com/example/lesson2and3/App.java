package com.example.lesson2and3;

import android.app.Application;
import com.example.lesson2and3.data.remote.PostApi;
import com.example.lesson2and3.data.remote.RetrofitClient;

public class App extends Application {

    public static PostApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient client = new RetrofitClient();
        api = client.provideApi();
    }
}
