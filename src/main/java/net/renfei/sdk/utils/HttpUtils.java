package net.renfei.sdk.utils;

import net.renfei.sdk.http.HttpBuilder;
import net.renfei.sdk.http.HttpRequest;
import net.renfei.sdk.http.HttpRequestMethod;
import net.renfei.sdk.http.HttpResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Http请求工具
 *
 * @author RenFei
 */
public class HttpUtils {
    /**
     * 默认采用的http协议的HttpClient对象
     */
    private static HttpClient httpClient;

    /**
     * 默认采用的https协议的HttpClient对象
     */
    private static HttpClient httpsClient;

    private final static Object SYNCLOCK = new Object();


    private static final int CONNECT_TIMEOUT = 10000;
    private static final int SOCKET_TIMEOUT = 10000;
    private static final int CONNECT_REQUEST_TIMEOUT = 10000;
    private static final int MAX_TOTAL = 100;
    private static final int MAX_PER_ROUTE = 100;
    private static final int RETRY_TIMES = 3;


    /**
     * 初始化 httpCient
     *
     * @param httpRequest
     */
    private static void setHttpClinet(HttpRequest httpRequest) {

        if (httpRequest.getHttpClient() == null) {
            if (httpRequest.isUseSSL()) {
                if (httpsClient == null) {
                    synchronized (SYNCLOCK) {
                        if (httpsClient == null) {
                            httpsClient = HttpBuilder.custom()
                                    .ssl()
                                    .timeout(CONNECT_TIMEOUT, SOCKET_TIMEOUT, CONNECT_REQUEST_TIMEOUT)
                                    .pool(MAX_TOTAL, MAX_PER_ROUTE)
                                    .retry(RETRY_TIMES, false).build();

                        }
                    }
                }
                httpRequest.client(httpsClient);
            } else {
                if (httpClient == null) {
                    synchronized (SYNCLOCK) {
                        if (httpClient == null) {
                            httpClient = HttpBuilder.custom()
                                    .timeout(CONNECT_TIMEOUT, SOCKET_TIMEOUT, CONNECT_REQUEST_TIMEOUT)
                                    .pool(MAX_TOTAL, MAX_PER_ROUTE)
                                    .retry(RETRY_TIMES, false).build();

                        }
                    }
                }
                httpRequest.client(httpClient);
            }
        }
    }

    /**
     * GET 请求
     *
     * @param httpRequest 请求配置类
     * @return 请求结果
     * @throws IOException
     */
    public static HttpResult get(HttpRequest httpRequest) throws IOException {
        return transformHttpResult(execute(httpRequest.requestMethod(HttpRequestMethod.GET)), httpRequest);
    }

    /**
     * POST 请求
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public static HttpResult post(HttpRequest httpRequest) throws IOException {
        return transformHttpResult(execute(httpRequest.requestMethod(HttpRequestMethod.POST)), httpRequest);
    }

    /**
     * PUT 请求
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public static HttpResult put(HttpRequest httpRequest) throws IOException {
        return transformHttpResult(execute(httpRequest.requestMethod(HttpRequestMethod.PUT)), httpRequest);
    }

    /**
     * HEADER 请求
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public static HttpResult header(HttpRequest httpRequest) throws IOException {
        return transformHttpResult(execute(httpRequest.requestMethod(HttpRequestMethod.HEAD)), httpRequest);
    }

    /**
     * PATCH 请求
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public static HttpResult patch(HttpRequest httpRequest) throws IOException {
        return transformHttpResult(execute(httpRequest.requestMethod(HttpRequestMethod.PATCH)), httpRequest);
    }

    /**
     * DELETE 请求
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public static HttpResult delete(HttpRequest httpRequest) throws IOException {
        return transformHttpResult(execute(httpRequest.requestMethod(HttpRequestMethod.DELETE)), httpRequest);
    }


    /**
     * TRACE 请求
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public static HttpResult trace(HttpRequest httpRequest) throws IOException {
        return transformHttpResult(execute(httpRequest.requestMethod(HttpRequestMethod.TRACE)), httpRequest);
    }

    /**
     * OPTIONS 请求
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public static HttpResult options(HttpRequest httpRequest) throws IOException {
        return transformHttpResult(execute(httpRequest.requestMethod(HttpRequestMethod.OPTIONS)), httpRequest);
    }

    /**
     * 下载
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public static OutputStream down(HttpRequest httpRequest) throws IOException {
        HttpResponse response = execute(httpRequest.requestMethod(HttpRequestMethod.GET));
        response.getEntity().writeTo(HttpRequest.getOut());
        close(response);
        return HttpRequest.getOut();
    }


    /**
     * 请求资源或服务
     *
     * @param httpRequest 请求参数配置
     * @return 返回HttpResponse对象
     */
    private static HttpResponse execute(HttpRequest httpRequest) throws IOException {

        if (httpRequest.getRequestMethod() == null) {
            throw new NullPointerException("request method is null!");
        }

        //设置httpClient
        setHttpClinet(httpRequest);

        //创建请求对象
        HttpRequestBase request = getRequest(HttpRequest.getUrl(), httpRequest.getRequestMethod());

        //设置header信息
        request.setHeaders(httpRequest.getHeaders());

        //判断是否支持设置entity(仅HttpPost、HttpPut、HttpPatch支持)
        if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
            //装填参数
            HttpEntity entity = httpRequest.getEntity();
            //设置参数到请求对象中
            ((HttpEntityEnclosingRequestBase) request).setEntity(entity);

        }
        //执行请求操作，并拿到结果（同步阻塞）
        if (httpRequest.getContext() == null) {
            return httpRequest.getHttpClient().execute(request);
        } else {
            return httpRequest.getHttpClient().execute(request, httpRequest.getContext());
        }


    }

    private static HttpResult transformHttpResult(HttpResponse response, HttpRequest config) throws IOException {
        //获取结果实体
        HttpResult result = new HttpResult(response.getStatusLine().getStatusCode(), response.getAllHeaders());
        if (response.getEntity() != null) {
            //content 编码
            String charset = (response.getEntity().getContentEncoding() == null) ? config.getResponseCharset() : response.getEntity().getContentEncoding().getValue();
            result.setContentEncoding(charset);
            //content 类型
            String contentType = (response.getEntity().getContentType() == null) ? "" : response.getEntity().getContentType().getValue();
            result.setContentType(contentType);
            result.setResponseText(EntityUtils.toString(response.getEntity(), config.getResponseCharset()));
        } else {//有可能是head请求
            result.setResponseText(response.getStatusLine().toString());
        }

        close(response);
        return result;
    }

    /**
     * 尝试关闭
     *
     * @param response Http返回对象
     */
    private static void close(HttpResponse response) {
        try {
            EntityUtils.consume(response.getEntity());
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if (CloseableHttpResponse.class.isAssignableFrom(response.getClass())) {
                ((CloseableHttpResponse) response).close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据请求方法名，获取request对象
     *
     * @param url    资源地址
     * @param method 请求方式
     * @return 返回Http处理request基类
     */
    private static HttpRequestBase getRequest(String url, HttpRequestMethod method) {
        HttpRequestBase request;
        switch (method.getCode()) {
            case 0:
                request = new HttpGet(url);
                break;
            case 1:
                request = new HttpPost(url);
                break;
            case 2:
                request = new HttpHead(url);
                break;
            case 3:
                request = new HttpPut(url);
                break;
            case 4:
                request = new HttpDelete(url);
                break;
            case 5:
                request = new HttpTrace(url);
                break;
            case 6:
                request = new HttpPatch(url);
                break;
            case 7:
                request = new HttpOptions(url);
                break;
            default:
                request = new HttpPost(url);
                break;
        }
        return request;
    }
}
