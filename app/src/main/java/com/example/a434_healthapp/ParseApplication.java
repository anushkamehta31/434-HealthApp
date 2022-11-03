package com.example.a434_healthapp;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("fCcUjfzob7Ez2nzmIrnPix5D6MX4hqVmmTxPBLTU")
                .clientKey("ZlA7dIHNaoDNTQHiZnnclKMSdI1anHs7QLW5oben")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
