package com.taxsee.data.net;

import com.taxsee.data.model.auth.AuthResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */

public interface AuthService {
    @GET("/Contacts.svc/Hello")
    Observable<AuthResponse> signIn(@Query("login") String login,
                                    @Query("password") String password);
}
