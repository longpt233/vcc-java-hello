package com.vc.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class App {

	public static void main(String args[]) throws InterruptedException, ExecutionException {
		File folder = new File("E:\\long\\20202\\intern\\java-core\\Ex3_Count_Parrallel\\folder_parallel");
		File[] listOfFiles = folder.listFiles();
		final Queue<File> fileQueue= new ConcurrentLinkedQueue<>();

		for (File file : listOfFiles) {
			if (file.isFile()) {
//				System.out.println(file.getName());
				fileQueue.add(file);
			}
		}

		ExecutorService eService= Executors.newFixedThreadPool(6);
		// luu ket qua tung luong
		List<Future< Map<String, Integer>>> resPerThread = new ArrayList<>();
		for(int i =0;i<6;i++) {
			resPerThread.add(eService.submit(new ThreadCountQueueFile(fileQueue)));
			
		}
		
		eService.shutdown();
		
        // Wait until all threads are finish
        while (!eService.isTerminated()) {
            // Running ...
        }
        
        Map<String, Integer> _wordCount= new HashMap<>();
 
		for(Future< Map<String, Integer>>resOfOneThread :resPerThread) {
			Map<String, Integer> map=resOfOneThread.get();
			System.out.println(map);
			Set<String> set = map.keySet();
			for (String s :set) {
				if (!_wordCount.containsKey(s)) { 
					int countOfThread = map.get(s);
					_wordCount.put(s, countOfThread);
				} else {
					int count = _wordCount.get(s);
					int countOfThread = map.get(s);
					_wordCount.put(s, count + countOfThread);
				}
			}
			
		}
		
		System.out.println("final="+_wordCount);
		
		_wordCount.entrySet().stream()
	       .sorted(Map.Entry.comparingByValue())
	       .limit(10)
	       .forEach(System.out::println);
		
		_wordCount.entrySet().stream()
	       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
	       .limit(10)
	       .forEach(System.out::println);
		
		
		
	}
	
	static class CountOneFile {
		
		// syn neu muon dong bo ???
		public static Map<String, Integer> count( Map<String, Integer> _wordCount,BufferedReader br) throws IOException {
			String line;
			String[] words = null;
			while ((line = br.readLine()) != null) {
				words = line.split(" "); 
				for (String s : words) {
					if (!_wordCount.containsKey(s)) { 
						_wordCount.put(s, 1);
					} else {
						int count = _wordCount.get(s);
						_wordCount.put(s, count + 1);
					}
				}
			}
			return _wordCount;
		}

		
	}

	public static class ThreadCountQueueFile implements Callable< Map<String, Integer>>{
		private Queue<File> _file;
		private Map<String, Integer> _wordCount= new HashMap<>();

		public ThreadCountQueueFile(Queue<File> file) {
			this._file = file;
			
		}
		
		public  Map<String, Integer> call() throws Exception {
			while (!_file.isEmpty()) {
				try {
					// thi thoang co exception o day co the la do khon con file de doc??
					FileReader fr = new FileReader(_file.poll());
					BufferedReader br = new BufferedReader(fr);
					_wordCount =CountOneFile.count(_wordCount, br);
					fr.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	        return _wordCount;
	    }

	}

}

