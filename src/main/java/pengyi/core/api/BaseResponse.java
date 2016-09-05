package pengyi.core.api;

/**
 * Created by pengyi on 2016/1/18.
 */
public class BaseResponse {

    public static BaseResponse DEFAULT_FAILED = new BaseResponse(ResponseCode.ERROR_API_FAILURE);

    private ResponseCode code;                     //错误码
    private long debug_time;                       //后台处理时间
    private String data;                           //返回对象
    private String message;                        //信息

    public BaseResponse() {
    }

    public BaseResponse(ResponseCode code, String message, String data) {
        this(code, message, data, 0);
    }

    public BaseResponse(ResponseCode code, String message, String data, long debug_time) {
        this.code = code;
        this.debug_time = debug_time;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(ResponseCode code) {
        this(code, code.getMessage(), null);
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public long getDebug_time() {
        return debug_time;
    }

    public void setDebug_time(long debug_time) {
        this.debug_time = debug_time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
