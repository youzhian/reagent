package com.ylv.user;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Test
    public void testList() throws IOException {
        String url = "https://api.test.hntradesp.com/share/polls?ric=AFCMc1&start_date=2022-01-01&end_date=2022-01-31";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);

        CloseableHttpResponse response = client.execute(get);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
