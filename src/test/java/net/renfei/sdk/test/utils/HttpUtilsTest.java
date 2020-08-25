package net.renfei.sdk.test.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.renfei.sdk.entity.MyIP;
import net.renfei.sdk.http.HttpRequest;
import net.renfei.sdk.http.HttpResult;
import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.HttpUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

@Slf4j
public class HttpUtilsTest extends Tests {

    @Test
    public void testHttpGet() throws IOException, InterruptedException {
        Runnable taskTemp = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("==== " + this.getClass().getName() + " ====");
                HttpRequest request = HttpRequest.create().url("http://ip.renfei.net");
                request.headers(new HashMap<>());
                request.addHeader("Test", "Test");
                System.out.println(request.toString());
                HttpRequest requestSSL = HttpRequest.create().url("https://ip.renfei.net").useSSL();
                HttpResult result = HttpUtils.get(request);
                HttpResult resultSSL = HttpUtils.get(requestSSL);
                HttpResult resultPost = HttpUtils.post(request);
                HttpResult resultHeader = HttpUtils.header(request);
                HttpResult resultPut = HttpUtils.put(request);
                HttpResult resultPatch = HttpUtils.patch(request);
                HttpResult resultDelete = HttpUtils.delete(request);
                HttpResult resultTrace = HttpUtils.trace(request);
                HttpResult resultOptions = HttpUtils.options(request);
                OutputStream outputStream = HttpUtils.down(request);
                log.info(result.getResponseText());
                MyIP myIP = result.parseObject(MyIP.class);
                log.info(myIP.getClientIP());
                System.out.println(result.getCode());
                System.out.println(result.getContentType());
                System.out.println(result.getContentEncoding());
                System.out.println(result.getHeader(""));
                System.out.println(result.getAllHeaders());
                result.setCode(200);
            }
        };
        startTaskAllInOnce(10, taskTemp);
    }
}
