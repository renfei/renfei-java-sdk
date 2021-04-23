package net.renfei.sdk.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Http响应对象
 *
 * @author RenFei
 */
public class HttpResult {
    private int code;
    private String responseText;
    private String contentType;
    private String contentEncoding;

    private Map<String, Header> headersMap;

    public HttpResult(int code) {
        this(code, null, null, null, null);
    }

    public HttpResult(int code, Header[] headers) {
        this(code, null, null, null, headers);
    }


    public HttpResult(int code, String responseText, String contentType
            , String contentEncoding, Header[] headers) {
        this.code = code;
        this.responseText = getNotNullString(responseText);
        this.contentType = getNotNullString(contentType);
        this.contentEncoding = getNotNullString(contentEncoding);
        headersMap = new LinkedHashMap<>();
        addHeaders(headers);
    }

    private void addHeaders(Header[] headers) {
        if (null != headers) {
            for (Header header : headers) {
                headersMap.put(header.getName(), header);
            }
        }
    }

    private String getNotNullString(String str) {
        return null == str ? "" : str;
    }

    private Header[] getNotNullHeaders(Header[] headers) {
        return null == headers ? new Header[0] : headers;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = getNotNullString(responseText);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = getNotNullString(contentType);
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = getNotNullString(contentEncoding);
    }

    public Header getHeader(String name) {
        return headersMap.get(name);
    }

    public Header[] getAllHeaders() {
        return headersMap.values().toArray(new Header[headersMap.size()]);
    }

    public <T> T parseObject(Type type) {
        return JSON.parseObject(this.responseText, type);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HttpRequestResult{");
        sb.append("code=").append(code);
        sb.append(", responseText='").append(responseText).append('\'');
        sb.append(", contentType='").append(contentType).append('\'');
        sb.append(", contentEncoding='").append(contentEncoding).append('\'');
        sb.append(", headersMap=").append(headersMap);
        sb.append('}');
        return sb.toString();
    }
}
