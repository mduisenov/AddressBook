package com.taxsee.domain.repository;

import com.taxsee.data.model.auth.AuthResponse;

import rx.Observable;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public interface AuthRepository {

    Observable<AuthResponse> signIn(String login, String password);
}
