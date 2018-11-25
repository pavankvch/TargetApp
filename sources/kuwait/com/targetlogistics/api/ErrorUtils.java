package kuwait.com.targetlogistics.api;

import java.io.IOException;
import java.lang.annotation.Annotation;
import retrofit2.Response;

public class ErrorUtils {
    public static APIError parseError(Response<?> response) {
        try {
            return (APIError) ApiClient.getClient().responseBodyConverter(APIError.class, new Annotation[0]).convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }
    }
}
