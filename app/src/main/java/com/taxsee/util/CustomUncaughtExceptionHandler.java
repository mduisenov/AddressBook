package com.taxsee.util;

import android.app.Application;
import android.util.Log;

import no.taxsee.addressbook.BuildConfig;

/**
 * Created by Marat Duisenov on 24.02.2017.
 */
public class CustomUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = CustomUncaughtExceptionHandler.class.getSimpleName();
    private final Application application; // TODO: 2017-02-24 use or remove it!
    private final Thread.UncaughtExceptionHandler previousUncaughtExceptionHandler;

    public CustomUncaughtExceptionHandler(Application application, Thread.UncaughtExceptionHandler previousUncaughtExceptionHandler) {
        this.application = application;
        this.previousUncaughtExceptionHandler = previousUncaughtExceptionHandler;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            // In debug mode show the problem
            previousUncaughtExceptionHandler.uncaughtException(thread, throwable);
        } else {
            // In release mode just notify the problem
            Log.wtf(TAG, "Coughed the uncaught Exception!", throwable);
            /*
            // Restart app:
            Intent intent = application.getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(application.getBaseContext().getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            application.startActivity(intent);
            System.exit(0);
            */
        }
    }
}
