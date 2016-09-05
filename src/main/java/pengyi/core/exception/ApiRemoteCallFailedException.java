package pengyi.core.exception;

import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/16.
 */
public class ApiRemoteCallFailedException extends Exception {
    private BaseResponse response;

    public ApiRemoteCallFailedException() {
        super("API请求失败");
        this.response = null;
    }

    public ApiRemoteCallFailedException(BaseResponse response) {
        super("API请求失败");
        this.response = response;
    }

    public ApiRemoteCallFailedException(String message) {
        super(message);
        this.response = null;
    }

    public ApiRemoteCallFailedException(String message, BaseResponse response) {
        super(message);
        this.response = response;
    }

    public ApiRemoteCallFailedException(String message, Throwable throwable) {
        super(message, throwable);
        this.response = null;
    }

    public ApiRemoteCallFailedException(String message, Throwable throwable, BaseResponse response) {
        super(message, throwable);
        this.response = response;
    }

    public ApiRemoteCallFailedException(Throwable throwable) {
        super(throwable);
        this.response = null;
    }

    public ApiRemoteCallFailedException(Throwable throwable, BaseResponse response) {
        super(throwable);
        this.response = response;
    }

    public BaseResponse getResponse() {
        return response;
    }
}
