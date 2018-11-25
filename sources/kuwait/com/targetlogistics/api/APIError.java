package kuwait.com.targetlogistics.api;

public class APIError {
    private String message;
    private int statusCode;

    public int status() {
        return this.statusCode;
    }

    public String message() {
        return this.message;
    }
}
