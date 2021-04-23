package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.demo.req.CompletedRequest;
import com.example.demo.req.GetRequest;

@SpringBootApplication
public class BenchmarkApplication {

	static final float MS_PER_SECOND = 1000; // 1 second = 1000 ms

	private static final Logger log = LoggerFactory.getLogger(BenchmarkApplication.class);

	@Value("${restapi.endpoint}")
	private String endpoint;
	@Value("${restapi.benchmark.request.total}")
	private int numConnection;
	@Value("${restapi.benchmark.request.concurrent}")
	private int numConcurrent;
	@Value("${restapi.benchmark.timelimit}")
	private int timelimit;

	// Variables used for log output
	@SuppressWarnings("unused")
	private int totalRequests = 0;
	@SuppressWarnings("unused")
	private long totalElapsedTime = 0;


	public static void main(String args[]) {
		SpringApplication.run(BenchmarkApplication.class, args);
	}

	
	private CloseableHttpClient httpclient=null;

	@Bean
	public CommandLineRunner run() {
		return args -> {
			
			PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
	        pool.setDefaultMaxPerRoute(numConnection);
	        pool.setMaxTotal(numConnection);
	        httpclient = HttpClients.custom().setConnectionManager(pool).build();
	 
	        ArrayList<CompletedRequest> completedRequests = new ArrayList<>();
	        ExecutorService threadpool = Executors.newFixedThreadPool(numConcurrent);
			
	        final String url = "http://localhost:2021/idcard/1";
	        for (int i = 0; i < numConcurrent; i++) {
	            threadpool.execute(new GetRequest(httpclient, url, null ,completedRequests,new Date()));
	        }
	        
	        threadpool.shutdown();
	        Date benchmarkStart = new Date();

			threadpool.awaitTermination(timelimit, TimeUnit.SECONDS);
			threadpool.shutdownNow();
		

			


			long latencyTime = 0;
			for (int i = 0; i < completedRequests.size(); i++) {
				if(i%10000 ==0) {
					System.out.println(completedRequests.get(i).getExe()+" "+ latencyTime);
				}
				if (completedRequests.get(i).getDate().getTime() >= benchmarkStart.getTime()) {
					

					latencyTime += completedRequests.get(i).getExe();
					
					Date firstRequest = completedRequests.get(i).getDate();
					Date lastRequest = completedRequests.get(completedRequests.size() - 1).getDate();
					long elapsedTime = lastRequest.getTime() - firstRequest.getTime();
					totalElapsedTime += elapsedTime;
					int numCompleted = completedRequests.size() - i;
					totalRequests += numCompleted;

					/* Log Status */
					float seconds = elapsedTime / MS_PER_SECOND;
					float currentRate = numCompleted / seconds;
					log.info(String.format(
							"\nCompleted %s  requests in %ss (%s req/s)",
							numCompleted, seconds, currentRate));
					break;
				}

			}
			long avgLatency = latencyTime/completedRequests.size();
			System.out.println("trung binh do tre="+avgLatency+"solong response= "+ completedRequests.size());
		

		};
	}



}
