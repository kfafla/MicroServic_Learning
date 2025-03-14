package top.xmy.requestservice.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ConsumerController {
    private final String SERVER_URL = "http://localhost:8080";
    @GetMapping("/httClientTest")
   public String httClientTest() throws IOException {
         CloseableHttpClient httpClient= HttpClients.createDefault();
         HttpGet httpGet=new HttpGet(SERVER_URL+"/hello");
         CloseableHttpResponse response=null;
         response = httpClient.execute(httpGet);
         if(response.getStatusLine().getStatusCode()==200){
             String result = EntityUtils.toString(response.getEntity());
             System.out.println(result);
         }
         return "请求成功";
    }
}
