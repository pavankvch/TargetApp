package kuwait.com.targetlogistics.api;

import com.google.gson.JsonObject;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET
    Call<JsonObject> methodCallGet(@Url String str, @QueryMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST
    Call<JsonObject> methodCallPost(@Url String str, @FieldMap HashMap<String, String> hashMap);
}
