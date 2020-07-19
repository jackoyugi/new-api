package exceptions;

public class ApiException RuntimeException {
    private final int statusCode;
    public ApiException(int statusCode, String msg){
        super(msg);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
