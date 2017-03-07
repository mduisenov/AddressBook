package com.taxsee.data.model.auth;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */

public class AuthResponse {

    @Nullable
    @SerializedName("Message")
    private String message;

    @SerializedName("Success")
    private boolean success;

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
