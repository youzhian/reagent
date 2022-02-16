package com.ylv.user;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TestHttpClient2 {

    private static CloseableHttpClient client = HttpClients.createDefault();

    public static void main(String [] args) throws Exception {

        String code = "234234234234234234234234234234";

        String token = getAccessTokenByCode(code);
        System.out.println(token);
        token = "2749f526-33e8-4765-8063-0eee25542c52";
        String result = getUserInfoByAccessToken(token);
        System.out.println(result);
        String r = getUser(token);
        System.out.println(r);
    }

    public static String getAccessTokenByCode(String code) throws IOException {
        String url = "http://api.otgov.com/uaa/oauth/token";
        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParamters = new ArrayList<>();
        //String code = "";
        urlParamters.add(new BasicNameValuePair("client_id", "qdcsdn_index"));
        urlParamters.add(new BasicNameValuePair("client_secret","secuirty"));
        urlParamters.add(new BasicNameValuePair("grant_type","authorization_code"));
        urlParamters.add(new BasicNameValuePair("code",code));
        urlParamters.add(new BasicNameValuePair("scope","admin user third"));

        post.setEntity(new UrlEncodedFormEntity(urlParamters));

        CloseableHttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }

    public static String getUserInfoByAccessToken(String accessToken) throws IOException{
        String url = "http://api.otgov.com/third/currUser";
        HttpPost post = new HttpPost(url);

        post.setHeader("Authorization",accessToken);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).build();
        post.setConfig(config);

        CloseableHttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }

    public static String getUser(String token) throws Exception{
        String url = "http://api.otgov.com/third/currUser";
        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization",token);

        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).build();
        get.setConfig(config);

        CloseableHttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity());

        return result;
    }
}
