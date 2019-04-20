package com.leone.seckill.utils;


import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author leone
 **/
public class HttpUtil {

    private HttpUtil() {
    }

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private final static String UTF8 = StandardCharsets.UTF_8.displayName();

    private static CloseableHttpClient httpClient;

    static {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(4000).setExpectContinueEnabled(true).build();
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
        pool.setMaxTotal(300);
        pool.setDefaultMaxPerRoute(50);
        HttpRequestRetryHandler retryHandler = (IOException exception, int executionCount, HttpContext context) -> {
            if (executionCount > 1) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                log.info("[NoHttpResponseException has retry request:" + context.toString() + "][executionCount:"
                        + executionCount + "]");
                return true;
            } else if (exception instanceof SocketException) {
                log.info("[SocketException has retry request:" + context.toString() + "][executionCount:"
                        + executionCount + "]");
                return true;
            }
            return false;
        };
        // HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
        // DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        // httpClient =
        // HttpClients.custom().setConnectionManager(pool).setDefaultRequestConfig(requestConfig).setRetryHandler(retryHandler)
        // .setRoutePlanner(routePlanner).build();
        httpClient = HttpClients.custom().setConnectionManager(pool).setDefaultRequestConfig(requestConfig)
                .setRetryHandler(retryHandler).build();
    }

    /**
     * @param certPath
     * @param password
     * @return
     * @throws Exception
     */
    public static CloseableHttpClient sslHttpsClient(String certPath, String password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream inputStream = new FileInputStream(new File(certPath))) {
            keyStore.load(inputStream, password.toCharArray());
        }
        SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        // CloseableHttpClient httpClient =
        // HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

        HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory)
                .setRoutePlanner(routePlanner).build();
    }

    /**
     * 设置请求头信息
     *
     * @param headers
     * @param request
     * @return
     */
    private static void setHeaders(Map<String, Object> headers, HttpRequest request) {
        if (null != headers && headers.size() > 0) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }
    }

    /**
     * 发送post请求请求体为xml
     *
     * @param url
     * @param xml
     * @param headers
     * @return
     */
    public static String sendPostXml(String url, String xml, Map<String, Object> headers) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            setHeaders(headers, httpPost);
            StringEntity entity = new StringEntity(xml, StandardCharsets.UTF_8);
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送json请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String sendPostJson(String url, String json, Map<String, Object> headers) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            setHeaders(headers, httpPost);
            StringEntity stringEntity = new StringEntity(json, StandardCharsets.UTF_8);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param body
     * @param headers
     * @param contentType
     * @return
     */
    public static String sendPost(String url, String body, Map<String, Object> headers, String contentType) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            setHeaders(headers, httpPost);
            StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8.displayName());
            stringEntity.setContentType(contentType);
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, StandardCharsets.UTF_8.displayName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送get请求
     *
     * @param url
     * @param params
     * @param header
     * @return
     */
    public static String sendGet(String url, Map<String, Object> params, Map<String, Object> header) {
        String result = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (params != null && params.size() > 0) {
                List<NameValuePair> pairs = new ArrayList<>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
                builder.setParameters(pairs);
            }
            HttpGet httpGet = new HttpGet(builder.build());
            setHeaders(header, httpGet);
            HttpResponse response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String appendParams(String url, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder(url);
        if (ObjectUtils.isEmpty(params)) {
            return url;
        }
        boolean flag = true;
        for (Map.Entry entry : params.entrySet()) {
            if (flag) {
                sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                flag = false;
            } else {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {

    }

    public static String sendGetUrlParam(String url, Map<String, Object> params, Map<String, Object> header) {
        String result = null;
        try {
            url = appendParams(url, params);
            HttpGet httpGet = new HttpGet(url);
            setHeaders(header, httpGet);
            HttpResponse response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
