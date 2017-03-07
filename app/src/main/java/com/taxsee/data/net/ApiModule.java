package com.taxsee.data.net;

import com.taxsee.util.TreeNodeDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tellh.com.recyclertreeview_lib.TreeNode;

@Module(
        complete = false,
        library = true,
        injects = {
        }
)
public final class ApiModule {

    private static final Gson GSON = new GsonBuilder()
//            .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
            .registerTypeAdapter(TreeNode.class, new TreeNodeDeserializer())
            .create();

    public static final String EMPLOYEE_IMAGE_URL =  "https://contact.taxsee.com/Contacts.svc/GetWPhoto?";
    public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("https://contact.taxsee.com");

    @Provides
    @Singleton
    HttpUrl provideBaseUrl() {
        return PRODUCTION_API_URL;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(HttpUrl baseUrl, OkHttpClient client) {
        return new Retrofit.Builder() //
                .client(client) //
                .baseUrl(baseUrl) //
                .addConverterFactory(GsonConverterFactory.create(GSON)) //
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //
                .build();
    }

    @Provides
    @Singleton
    ContentService provideContentService(Retrofit retrofit){
        return retrofit.create(ContentService.class);
    }

    @Provides
    AuthService provideAuthService(Retrofit retrofit){
        return  retrofit.create(AuthService.class);
    }

}
