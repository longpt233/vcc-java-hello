package com.vc.app;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


public class App {
	
	
	private static int MAX_VALUE= Integer.MAX_VALUE;
	private static int LENGTH= 200000;
	private static double MUTUAL= 0.1;
	
	private static Set<Integer> set1= new HashSet<>();
	private static Set<Integer> set2= new HashSet<>();
	
	public static void InitSet() {
		
		set1= ThreadLocalRandom.current().ints(0, MAX_VALUE).distinct().limit(LENGTH).boxed().collect(Collectors.toSet());
		
		Object[] set1Array=set1.toArray();
		
		Random random = new Random();
		while (set2.size() < LENGTH) {
			
				if (set2.size()==LENGTH) break;
				
		        int randInt = random.nextInt(MAX_VALUE);
		        if (!set2.contains(randInt)) {   // contains O(1)
		            set2.add(randInt);
		        }
		        
		        if (set2.size()==LENGTH) break;
		        
		        // sac xuat lay ra mot element bat ki cua set 1 de them vao set2 
		        if (Math.random()<MUTUAL) {      // math. rand init betwwen 0..1
		        	int randIndex = random.nextInt(LENGTH);
		        	if (!set2.contains(set1Array[randIndex])) {   
			            set2.add(randInt);
			        }
		        }
		}
	}
	
	public static void main(String[] args) {
		InitSet();
		System.out.println("inited");
//		set1.stream().forEach(elem->System.out.print(elem+" "));
//		System.out.println("length="+set1.size()+"  "+set2.size());
		
		Set<Integer> unique = new HashSet<>(set1);
		Set<Integer> intersec = new HashSet<>(set1);
		
		unique.addAll(set2);
		intersec.retainAll(set2);
		
		for(int i : unique) {
			System.out.println(i+" ");
		}
		for(int i : intersec) {
			System.out.println(i+" ");
		}
		
	
	}

}
