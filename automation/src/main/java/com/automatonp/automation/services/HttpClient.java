package com.automatonp.automation.services;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.automatonp.automation.entity.FormEntity;
import com.automatonp.automation.entity.status;
import com.google.gson.Gson;


@Service
public class HttpClient {
	
	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
    public void getProxy() throws Exception {

    	HttpClient obj = new HttpClient();
    	System.out.println("i am in the service");
    	 
    	 try {
             System.out.println("Testing 1 - Send Http GET request");
             obj.sendGet();

             System.out.println("Testing 2 - Send Http POST request");
             obj.sendPost();
         } finally {
             obj.close();
         }
    	
}
    
    private void close() throws IOException {
        httpClient.close();
    }

    private void sendGet() throws Exception {

        HttpGet request = new HttpGet("https://www.google.com/search?q=mkyong");

        // add request headers
        request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        }
    }

    private void sendPost() throws Exception {

        HttpPost post = new HttpPost("https://httpbin.org/post");

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", "abc"));
        urlParameters.add(new BasicNameValuePair("password", "123"));
        urlParameters.add(new BasicNameValuePair("custom", "secret"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }
    
    public void createPort( FormEntity formentity )  throws Exception  {
    	
    	System.out.println(formentity.getCity());
    	int port = 24000;
    	
    	
    	do {
    		port++;
    		  
    		}
    		while (createUniquePort(port)==false);
    
    		System.out.println("New port is created port #" + port);
    	
    	 boolean proxyStatus= proxyStatus(port);
    	if(proxyStatus==true) {
    		System.out.println("proxy is ok");
    		
    	}
    	else {
    		System.out.println("Error");
    	}
    }
    
    
    public void deletePort(int port) throws Exception {
   
    	System.out.println(port);
    	  String res = Executor.newInstance()
    	            .execute(Request.Delete("http://127.0.0.1:22999/api/proxies/"+port))
    	            .returnContent().asString();
    	  		System.out.println("below is the response");
    	        System.out.println(res);
    	
    }
    
    
    public boolean proxyStatus(int port) throws Exception{

    Gson g = new Gson();  
    
    String res= Executor.newInstance()
    	            .execute(Request.Get("http://127.0.0.1:22999/api/proxy_status/"+port))
    	            .returnContent().asString();
    	 	
    	 	status resStatus= g.fromJson(res, status.class);
    	 	System.out.println(resStatus.getStatus());
    	 	System.out.println("below is g");
   
    	 	if(resStatus.getStatus().equals("ok")) {
    	 		
    	 		return true;
    	 	}
    	 	else {
    	
    	 		return false;
    	 	}
    	       
    	    }
   
    public boolean createUniquePort(int port) throws Exception{
    	boolean portCreated=false;
    	 try {
    		 
   
   	 String body = "{\"proxy\":{\"port\":"+port+",\"zone\":\"residential\",\"proxy_type\":\"persist\",\"customer\":\"lum-customer-c_4737a82e-zone-residential\",\"password\":\"84n64jf8ut42\",\"country\":\"us\", \"state\":\"tx\", \"city\":\"cypress\"}}";
        String res = Executor.newInstance()
            .execute(Request.Post("http://127.0.0.1:22999/api/proxies")
                .bodyString(body, ContentType.APPLICATION_JSON))
            .returnContent().asString();
        System.out.println("i am in create proxy");
        System.out.println(res);
        System.out.println();
        if (!res.equals("ok")) {
        	System.out.println("i am in 200");
        	portCreated= true;
        	 //  throw new RuntimeException(res);
        	  }
     	 }
    	 catch (Exception e) {
    		 System.out.println("i am in 500");
    		 portCreated=false;
    		//  throw new RuntimeException(e);
    		 }
       return portCreated;
   }
}