package com.mrathena.transaction.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HttpKit {

    private HttpKit() {}

    public static void main(String[] args) throws Exception {

    }

    private static Logger log = LoggerFactory.getLogger(HttpKit.class);

    private static final String EMPTY = "";
    private static final String CHARSET_UTF_8 = "UTF-8";

    public static String postWithMap(String url, Map<String, String> map) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            // 创建默认的httpClient实例.
            client = HttpClients.createDefault();
            // 创建httppost
            HttpPost post = new HttpPost(url);
            // 创建参数队列
            List<BasicNameValuePair> parameters = new ArrayList<>();
            if (map != null && !map.isEmpty()) {
            	for (Map.Entry<String, String> entry : map.entrySet()) {
            		parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            	}
			}
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(parameters, CHARSET_UTF_8);
            post.setEntity(uefEntity);
            response = client.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, CHARSET_UTF_8);
            }
            return null;
        } catch (Exception e) {
            log.error(EMPTY, e);
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                log.error(EMPTY, e);
            }
        }
    }
    
    public static String getWithMap(String url, Map<String, String> map) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            // 创建默认的httpClient实例.
            client = HttpClients.createDefault();
            // 创建httpget
            String uri = EMPTY;
            if (map == null || map.isEmpty()) {
				uri = url;
			} else {
				StringBuilder sb = new StringBuilder();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
				}
				uri = url + (map == null || map.isEmpty() ? "" : "?" + sb.toString().substring(1));
			}
            HttpGet get = new HttpGet(uri);
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, CHARSET_UTF_8);
            }
            return null;
        } catch (Exception e) {
            log.error(EMPTY, e);
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                log.error(EMPTY, e);
            }
        }
    }
    
    /**queryString 格式 name1=value1&name2=value2*/
    public static String getWithString(String url, String queryString) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            // 创建默认的httpClient实例.
            client = HttpClients.createDefault();
            // 创建httpget
            HttpGet get = new HttpGet(url + "?" + queryString);
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, CHARSET_UTF_8);
            }
            return null;
        } catch (Exception e) {
            log.error(EMPTY, e);
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                log.error(EMPTY, e);
            }
        }
    }

}