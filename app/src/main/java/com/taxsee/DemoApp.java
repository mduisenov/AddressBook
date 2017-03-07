package com.taxsee;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.taxsee.data.LumberYard;
import com.taxsee.ui.base.ActivityHierarchyServer;
import com.taxsee.ui.rx_activity_result.RxActivityResult;
import com.taxsee.util.CustomUncaughtExceptionHandler;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.ObjectGraph;
import io.fabric.sdk.android.Fabric;
import no.taxsee.addressbook.BuildConfig;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public final class DemoApp extends Application {
    private ObjectGraph objectGraph;
    private static DemoApp sDemoApp;

    @Inject ActivityHierarchyServer activityHierarchyServer;
    @Inject LumberYard lumberYard;

    @Override
    public void onCreate() {
        super.onCreate();
        sDemoApp = this;
        AndroidThreeTen.init(this);
        RxActivityResult.register(this);
        LeakCanary.install(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        } else {
            Fabric.with(this, new Crashlytics());
            // TODO Crashlytics.start(this);
//             Timber.plant(new CrashlyticsTree());
        }

        objectGraph = ObjectGraph.create(Modules.list(this));
        objectGraph.inject(this);

        lumberYard.cleanUp();

        Thread.setDefaultUncaughtExceptionHandler(new CustomUncaughtExceptionHandler(this,
                Thread.getDefaultUncaughtExceptionHandler()));

        Timber.plant(lumberYard.tree());
        registerActivityLifecycleCallbacks(activityHierarchyServer);
    }

    public static Context getContext() {
        return sDemoApp;
    }

    public static ObjectGraph obtain() {
        return sDemoApp.objectGraph;
    }
}
