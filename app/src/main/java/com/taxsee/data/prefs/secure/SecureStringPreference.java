package com.taxsee.data.prefs.secure;

public class SecureStringPreference {
    private final String key;
    private final SecureSharedPreferences preferences;

    public SecureStringPreference(SecureSharedPreferences preferences, String key) {
        this.preferences = preferences;
        this.key = key;
    }

    public String get() {
        return this.preferences.getString(this.key);
    }

    public boolean isSet() {
        return this.preferences.containsKey(this.key);
    }

    public void set(String value) {
        this.preferences.put(this.key, value);
    }

    public void delete() {
        this.preferences.removeValue(this.key);
    }
}
