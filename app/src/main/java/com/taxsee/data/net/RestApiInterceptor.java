package com.taxsee.data.net;

import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Marat Duisenov on 23.02.2017.
 */
@Singleton
public class RestApiInterceptor implements Interceptor {
    private static final String HEADER_LANG = "App-language";
    private final String mLang;

    @Inject
    public RestApiInterceptor() {
        mLang = Locale.getDefault().getLanguage();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder();
        builder.addHeader(HEADER_LANG, mLang);
        request = builder.build();


        return chain.proceed(request);
    }

}
