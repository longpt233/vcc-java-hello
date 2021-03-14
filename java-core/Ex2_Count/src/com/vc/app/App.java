package com.vc.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App {

//	https://www.candidjava.com/tutorial/java-program-to-count-number-of-words-in-a-file/

	public static void main(String[] args) throws IOException {
		try {
			File f1 = new File("D:\\20202\\intern\\java-hello\\java-core\\Ex2_Count\\test.txt");
			String[] words = null;

			Map<String, Integer> map = new HashMap<String, Integer>();

			FileReader fr = new FileReader(f1);
			BufferedReader br = new BufferedReader(fr);
			String line;

			while ((line = br.readLine()) != null) {
				words = line.split(" "); // Split the word using space
				// 1 words is a array split from 1 line

				for (String s : words) {
					if (!map.containsKey(s)) { // first time we've seen this string
						map.put(s, 1);
					} else {
						int count = map.get(s);
						map.put(s, count + 1);
					}
				}
			}
			fr.close();
			map.entrySet().stream().forEach(e-> System.out.println(e));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
