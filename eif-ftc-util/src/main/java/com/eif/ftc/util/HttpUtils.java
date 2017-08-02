package com.eif.ftc.util;

import com.alibaba.fastjson.JSON;
import com.eif.framework.log.client.httpclient.wrapper.InTraceCloseableHttpClient;
import com.eif.ftc.util.exception.RemoteInvocationException;

import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author jiangweifeng
 */
public class HttpUtils {
	
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private final static String SOCKET_EX = "0181024";

    /**
     * connectionRequestTimeout:从连接池中获取连接的超时时间，超过该时间未拿到可用连接，
     *  会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
     *
     *  connectTimeout:连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
     *
     *  socketTimeout:服务器返回数据(response)的时间，超过该时间抛出read timeout
     */

    private static RequestConfig DEFAULT_REQUEST_CONFIG = RequestConfig.custom()
            .setConnectTimeout(5000).setConnectionRequestTimeout(5000)
            .setSocketTimeout(30000).build();

    /**
     * HttpGet
     * @param url
     * @param headers
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, String> headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(DEFAULT_REQUEST_CONFIG);
        httpGet.setProtocolVersion(HttpVersion.HTTP_1_1);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE,HTTP.CONN_CLOSE);

        httpGet.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
        if (headers != null) {
			Set<String> keys = headers.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				httpGet.addHeader(key, headers.get(key));
			}
		}

    	logger.info("httpGet: " + JSON.toJSONString(httpGet));
    	
        try (CloseableHttpClient httpclient = getCloseableHttpClient();
             CloseableHttpResponse httpResponse = httpclient.execute(httpGet)) {
            return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
        	logger.error("[httpClientException]http请求发生异常!error:{}", e.getMessage(), e);
            throw new RemoteInvocationException("Exception happened when execute httpGet",SOCKET_EX,e,e.getMessage());
        }
    }

    /**
     * HttpPost
     * @param url
     * @param data
     * @param headers
     * @return
     * @throws IOException
     */
    public static String post(String url, String data, Map<String, String> headers)throws IOException{
    	HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(DEFAULT_REQUEST_CONFIG);
        httpPost.setProtocolVersion(HttpVersion.HTTP_1_1);
        
    	httpPost.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
    	httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
    	httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
		if (headers != null) {
			Set<String> keys = headers.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				httpPost.addHeader(key, headers.get(key));
			}
		}
		
		StringEntity entity = new StringEntity(data, "UTF-8");
		entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		httpPost.setEntity(entity);

//    	logger.info("httpPost: " + JSON.toJSONString(httpPost));
//    	logger.info("httpPostData: " + data);
    	
        try (CloseableHttpClient httpclient = getCloseableHttpClient();
             CloseableHttpResponse httpResponse = httpclient.execute(httpPost)) {
            return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
        	logger.error("[httpClientException]http请求发生异常!error:{}", e.getMessage(), e);
            throw new RemoteInvocationException("Exception happened when execute httpPost", SOCKET_EX, e, e.getMessage());
        }
    }
    
	@SuppressWarnings("deprecation")
	public static CloseableHttpClient getCloseableHttpClient() {
    	HttpClientBuilder builder = HttpClients.custom();
    	builder.setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    	
        return InTraceCloseableHttpClient.wrapUp(builder.build());
//    	return InTraceCloseableHttpClient.wrapUp(HttpClients.createDefault());
    }
    
    public static void main(String[] args) {
    	String url = "https://www.baidu.com/";
    	try {
			System.out.println(HttpUtils.get(url, null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
