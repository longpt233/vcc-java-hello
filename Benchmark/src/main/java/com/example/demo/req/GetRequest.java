package com.example.demo.req;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.demo.entity.IdCard;

public class GetRequest extends Request {

//	public GetRequest(RestTemplate r, String endpoint, String id, ArrayList<CompletedRequest> c) {
//		super(r, endpoint, id, c);
//	}
	public GetRequest(CloseableHttpClient httpclient, String endpoint, String id, ArrayList<CompletedRequest> c,Date timesSubmit) {
		super(httpclient, endpoint, id, c,timesSubmit);
	}

	@Override
	public void run() {
		
		while (new Date().getSeconds()-timeSubmitDate.getSeconds()<30) {

			long start = System.nanoTime();
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = null;
			try {
				response = httpclient.execute(httpGet);
				HttpEntity entity = response.getEntity();
                EntityUtils.consume(entity);
				response.close();
			} catch (ClientProtocolException e) {
			} catch (IOException e) {
			} 

			long stop = System.nanoTime();	
			CompletedRequest completedRequest = new CompletedRequest(stop - start, new Date());
			addCompletedRequest(completedRequest);
		}

//			long start = System.nanoTime();
//			ResponseEntity<IdCard> response = restTemplate.exchange(url, HttpMethod.GET, null, IdCard.class);
//			long stop = System.nanoTime();
//			CompletedRequest completedRequest = new CompletedRequest(stop - start, new Date(),
//					response.getBody().getId());
//			addCompletedRequest(completedRequest);

	}
}
