package com.taxsee.data.prefs;

import android.content.SharedPreferences;

public class IntPreference {
    private final int defaultValue;
    private final String key;
    private final SharedPreferences preferences;

    public IntPreference(SharedPreferences preferences, String key) {
        this(preferences, key, 0);
    }

    public IntPreference(SharedPreferences preferences, String key, int defaultValue) {
        this.preferences = preferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public int get() {
        return this.preferences.getInt(this.key, this.defaultValue);
    }

    public boolean isSet() {
        return this.preferences.contains(this.key);
    }

    public void set(int value) {
        this.preferences.edit().putInt(this.key, value).apply();
    }

    public void delete() {
        this.preferences.edit().remove(this.key).apply();
    }
}
