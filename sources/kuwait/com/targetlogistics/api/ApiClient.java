package kuwait.com.targetlogistics.api;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiInterface service;

    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Level.BODY);
        return new Builder().baseUrl(BaseUrl.GLOBAL_URL).client(new OkHttpClient.Builder().connectTimeout(10, TimeUnit.MINUTES).retryOnConnectionFailure(true).followSslRedirects(true).addInterceptor(interceptor).build()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiInterface getService() {
        if (service == null) {
            service = (ApiInterface) getClient().create(ApiInterface.class);
        }
        return service;
    }
}
