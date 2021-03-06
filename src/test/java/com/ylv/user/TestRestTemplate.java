package com.ylv.user;

import com.alibaba.fastjson.JSONObject;
import org.assertj.core.util.Arrays;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRestTemplate {

    public static void main(String []args){

        String url = "http://api.otgov.com/uaa/oauth/token";
        //url = "http://www.baidu.com";
        String code = "12333333333333333333333333333333";

        RestTemplate template = new RestTemplate(getFactory());

        //template.setMessageConverters(getConverts());

        Map<String,Object> param = new HashMap<>();
        MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();
        params.add("client_id","qdcshn_index");
        param.put("client_id", "qdcsdn_index");
        param.put("client_secret","secuirty");
        param.put("grant_type","authorization_code");
        param.put("code",code);
        param.put("scope","admin user third");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        HttpEntity<MultiValueMap<String,Object>> request = new HttpEntity(params,httpHeaders);

        //ResponseEntity<JSONObject> result = template.postForEntity(url,request, JSONObject.class);
        //System.out.println(result);
        String paramStr = "client_id=qdcshn_index&client_secret=secuirty&grant_type=authorization_code&scope=admin user third&code="+code;
        String result = sendPost(url,paramStr);
        System.out.println(result);
    }

    private static SimpleClientHttpRequestFactory getFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10000);
        factory.setReadTimeout(1000);
        return factory;
    }

    private static List<HttpMessageConverter<?>> getConverts() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        // String?????????
        StringHttpMessageConverter stringConvert = new StringHttpMessageConverter();
        List<MediaType> stringMediaTypes = new ArrayList<MediaType>() {{
            //??????????????????????????????????????????401
            add(MediaType.TEXT_PLAIN);
            add(MediaType.TEXT_HTML);
            add(MediaType.APPLICATION_JSON);
            add(MediaType.ALL);
        }};
        stringConvert.setSupportedMediaTypes(stringMediaTypes);
        messageConverters.add(stringConvert);
        return messageConverters;
    }

    /**
     * ????????? URL ??????POST???????????????
     *
     * @param url
     *            ??????????????? URL
     * @param param
     *            ???????????????????????????????????? name1=value1&name2=value2 ????????????
     * @return ????????????????????????????????????
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // ?????????URL???????????????
            URLConnection conn = realUrl.openConnection();
            // ???????????????????????????
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ??????POST??????????????????????????????
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ??????URLConnection????????????????????????
            out = new PrintWriter(conn.getOutputStream());
            // ??????????????????
            out.print(param);
            // flush??????????????????
            out.flush();
            // ??????BufferedReader??????????????????URL?????????
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("?????? POST ?????????????????????"+e);
            e.printStackTrace();
        }
        //??????finally?????????????????????????????????
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}

