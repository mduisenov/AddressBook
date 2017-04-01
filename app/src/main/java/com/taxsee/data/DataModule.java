package com.taxsee.data;

import android.app.Application;
import android.content.SharedPreferences;

import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.jakewharton.byteunits.DecimalByteUnit;
import com.taxsee.data.net.ApiModule;
import com.taxsee.data.net.RestApiInterceptor;
import com.taxsee.data.prefs.SecureSharedPreferences;
import com.taxsee.data.prefs.SecureStringPreference;
import com.taxsee.data.repository.RepositoryModule;
import com.taxsee.data.repository.auth.PasswordPref;
import com.taxsee.data.repository.auth.UserNamePref;

import org.threeten.bp.Clock;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

import static android.content.Context.MODE_PRIVATE;

@Module(
        includes = {ApiModule.class, RepositoryModule.class},
        complete = false,
        library = true
)
public final class DataModule {

    public static final int DISK_CACHE_SIZE = (int) DecimalByteUnit.MEGABYTES.toBytes(50);
    private static final String SECURE_KEY = "lPwzDHr5fQ23N9P0tSHpk66uy01nGLwukrgECrSbSzoWWrFHRP0icWXGPf6enLI";

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences("address_book_pref", MODE_PRIVATE);
    }

    @Provides
    @Singleton
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences prefs) {
        return RxSharedPreferences.create(prefs);
    }

    @Provides
    @Singleton
    Clock provideClock() {
        return Clock.systemDefaultZone();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application app, RestApiInterceptor restApiInterceptor) {
        return createOkHttpClient(app).addInterceptor(restApiInterceptor).build();
    }

    @Singleton
    @Provides
    final SecureSharedPreferences providesSecurePreferences(Application app) {
        return new SecureSharedPreferences(app, "address_book_secure_pref", SECURE_KEY, true);
    }

    @UserNamePref
    @Provides
    @Singleton
    SecureStringPreference providesUserNamePref(SecureSharedPreferences preferences) {
        return new SecureStringPreference(preferences, "user");
    }

    @PasswordPref
    @Provides
    @Singleton
    SecureStringPreference providesPasswordPref(SecureSharedPreferences preferences) {
        return new SecureStringPreference(preferences, "pass");
    }

    public static OkHttpClient.Builder createOkHttpClient(Application app) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        return new OkHttpClient.Builder().cache(cache);
    }
}
