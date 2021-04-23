package com.example.demo.req;

import java.util.ArrayList;
import java.util.Date;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.web.client.RestTemplate;

public abstract class Request implements Runnable {
//    RestTemplate restTemplate;
	CloseableHttpClient httpclient;
	Date timeSubmitDate;
    String url;
    ArrayList<CompletedRequest> completedRequests;
    
//    public Request(RestTemplate r, String endpoint, String id, ArrayList<CompletedRequest> c) {
//        restTemplate = r;
//        url = id == null ? endpoint : endpoint + '/' + id;
//        completedRequests = c;
//    }
    
    
    public Request(CloseableHttpClient httpclient, String endpoint, String id, ArrayList<CompletedRequest> c,Date timeSubmitDate) {
      this.httpclient=httpclient;
      url = id == null ? endpoint : endpoint + '/' + id;
      completedRequests = c;
      this.timeSubmitDate=timeSubmitDate;
  }
    @Override
    public abstract void run();
    
    void addCompletedRequest(CompletedRequest c) {
        synchronized(completedRequests) {
            completedRequests.add(c);
        }
    }
}
