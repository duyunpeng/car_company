package pengyi.core.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pengyi.core.exception.ApiRemoteCallFailedException;
import pengyi.core.util.CharsetConstant;
import pengyi.core.util.CoreStringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by YJH on 2016/3/15.
 */
public class ApiRequest {

    private ApiConfig config;

    private BaseResponse response;

    public void setConfig(ApiConfig config) {
        this.config = config;
    }

    private static Logger logger = LoggerFactory.getLogger(ApiRequest.class);

    public BaseResponse urlConnection(String url, String pa) throws ApiRemoteCallFailedException {

        String result = null;

        url = config.getUrl() + url;
        try {
            logger.info(url, "http发送请求-----" + pa + "-----" + new Date());
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            // Send data
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), CharsetConstant.UTF8_STRING));
            // pa为请求的参数
            pw.print(pa);
            pw.flush();
            pw.close();

            // Get the api!
            int httpResponseCode = conn.getResponseCode();
            if (httpResponseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("HTTP api code: " + httpResponseCode +
                        "\nurl:" + url);
            }

            InputStream inputStream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, CharsetConstant.UTF8_STRING));
            StringBuilder builder = new StringBuilder();
            String readLine;
            while (null != (readLine = br.readLine())) {
                builder.append(readLine);
            }
            inputStream.close();
            result = builder.toString();
            logger.info(url, "http返回-----" + response + "-----" + new Date());

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response = JSON.parseObject(result, BaseResponse.class);
            logger.info("Api请求成功, code: [{}]", response.getCode().getCode());
            logger.info("响应结果: [{}]", CoreStringUtils.urlDecode(result, CharsetConstant.UTF8_STRING));
        } catch (JSONException e) {
            logger.warn("API调用返回结果格式不正确", e);
            logger.info("响应结果: [{}]", CoreStringUtils.urlDecode(result, CharsetConstant.UTF8_STRING));
            throw new ApiRemoteCallFailedException("API响应结果Json转换出错", BaseResponse.DEFAULT_FAILED);
        }

        if (response.getCode() != ResponseCode.RESPONSE_CODE_SUCCESS) {
            throw new ApiRemoteCallFailedException("API请求失败,返回内容: code[" + response.getCode() + "] message[" + response.getMessage() + "]", response);
        }

        return this.response;
    }

    public <T> T convertJsonTo(TypeReference<T> clazz) {
        T t = null;
        try {
            t = JSON.parseObject(CoreStringUtils.urlDecode(this.response.getData(), CharsetConstant.DEFAULT_STRING),
                    clazz);
        } catch (JSONException e) {
            logger.error("转换json异常.", e);
        }
        return t;
    }

}
