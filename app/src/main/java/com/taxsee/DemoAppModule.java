package com.taxsee;

import android.app.Application;

import com.taxsee.data.DataModule;
import com.taxsee.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                UiModule.class,
                DataModule.class
        },
        injects = {
                DemoApp.class
        }
)
public final class DemoAppModule {
    private final DemoApp app;

    public DemoAppModule(DemoApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

}
