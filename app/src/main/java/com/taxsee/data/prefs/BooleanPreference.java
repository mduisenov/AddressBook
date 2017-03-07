package com.taxsee.data.prefs;

import android.content.SharedPreferences;

public class BooleanPreference {
    private final boolean defaultValue;
    private final String key;
    private final SharedPreferences preferences;

    public BooleanPreference(SharedPreferences preferences, String key) {
        this(preferences, key, false);
    }

    public BooleanPreference(SharedPreferences preferences, String key, boolean defaultValue) {
        this.preferences = preferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public boolean get() {
        return this.preferences.getBoolean(this.key, this.defaultValue);
    }

    public boolean isSet() {
        return this.preferences.contains(this.key);
    }

    public void set(boolean value) {
        this.preferences.edit().putBoolean(this.key, value).apply();
    }

    public void delete() {
        this.preferences.edit().remove(this.key).apply();
    }
}
