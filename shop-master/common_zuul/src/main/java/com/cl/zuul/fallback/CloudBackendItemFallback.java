package com.cl.zuul.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 代理服务，服务超时时的托底数据
 * @Author l
 * @Date 2022/1/3 21:05
 */
@Component
public class CloudBackendItemFallback implements FallbackProvider {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    /**
     * 代理的哪个服务
     * @return
     */
    @Override
    public String getRoute() {
        return "cloud-backend-item";
    }

    /**
     * 超时时的托底数据
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if(cause!=null && cause.getCause()!=null){
            String c=cause.getCause().getMessage();
            logger.info("服务降级："+route +"\t" +c);
        }
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                String content = "服务超时， 请重试";
                return new ByteArrayInputStream(content.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders=new HttpHeaders();
                MediaType mediaType=new MediaType("application","json", Charset.forName("utf-8"));
                httpHeaders.setContentType(mediaType);
                return httpHeaders;
            }
        };
    }
}
