package com.example.rumpilstilstkin.lesson5;


import android.app.Application;

import com.orm.SugarContext;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainApp extends Application {

    private static AppComponent component;
    private static AppComponentSingltone singletonComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(this);

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);

        component = DaggerAppComponent.create();

        singletonComponent = DaggerAppComponentSingltone
                .builder()
                .appModule(new AppModule(this))
                .build();
        singletonComponent.inject(this);
    }


    public static AppComponent getComponent() {
        return component;
    }

    public static AppComponentSingltone getComponentSingleton() {
        return singletonComponent;
    }
}

