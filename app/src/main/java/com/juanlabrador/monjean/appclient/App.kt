package com.juanlabrador.monjean.appclient

import android.app.Application
import com.parse.Parse
import com.parse.ParseObject

/**
 * Created by juanlabrador on 29/10/15.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ParseObject.registerSubclass(Pants::class.java)
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "KEY", "KEY");
    }
}