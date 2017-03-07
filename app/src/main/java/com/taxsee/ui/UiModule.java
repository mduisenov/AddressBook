package com.taxsee.ui;

import android.app.Application;

import com.taxsee.ui.auth.AuthModule;
import com.taxsee.ui.base.ActivityHierarchyServer;
import com.taxsee.ui.main.MainActivityModule;
import com.taxsee.ui.splash.SplashModule;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import timber.log.Timber;

@Module(
        includes = {SplashModule.class, AuthModule.class, MainActivityModule.class},
        complete = false,
        library = true
)
public final class UiModule {
    @Provides
    @Singleton
    ViewContainer provideViewContainer() {
        return ViewContainer.DEFAULT;
    }

    @Provides
    @Singleton
    ActivityHierarchyServer provideActivityHierarchyServer() {
        return ActivityHierarchyServer.NONE;
    }

    @Provides
    @Singleton
    Picasso providePicasso(Application app, OkHttpClient client) {
        return new Picasso.Builder(app)
                .downloader(new OkHttp3Downloader(client))
                .listener((picasso, uri, e) -> Timber.e(e, "Failed to load image: %s", uri))
                .build();
    }
}
