package com.ylv.user;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHttpClient {

    @Test
    public void testGetTokenByCode() throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();

        String code = "02E7CCDF18BA9278F9F8FFD97CA2935BDC2365E788620B8FAEA767151ADB65AEBD7CD0DDA59DA02DEAC3E9E3CAD9E2E40F364779DE97B4D5";
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
        System.out.println(result);
    }
}
