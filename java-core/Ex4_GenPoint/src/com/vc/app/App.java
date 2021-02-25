package com.vc.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.vc.ultis.Point;

public class App {

	public static Set<Point> init(Point center, int radius, int amount) {

		Set<Point> setPoint = new HashSet<>();

//		Set<Integer> set_x= new HashSet<>();
//		// set x phai tru di radius nua moi dc 

		// khong dung cah nay duoc vi se bi trung do so luowng diem lown hown ban kinh
//		set_x = ThreadLocalRandom.current().ints(0, 2*radius).distinct().limit(amount).boxed()
//				.collect(Collectors.toSet());
//
//		Object[] set1Array = set_x.toArray();
//
//		Random random = new Random();
//		int r2=radius*radius;
//		for (int  i =0; i<amount;i++ ) {
//
//			int x_i= (int)set1Array[i]-radius;  // chuyen ve co the toa do am 
//			int imposible_y=  (int) Math.sqrt(r2- x_i*x_i) ;
//			int y_rand = random.nextInt(2*imposible_y);
//			int y_i=y_rand-imposible_y;				// chuyen ve co the y am;
//			
//			Point tmpPoint = new Point(x_i+center.getX(), y_i+center.getY());
//			setPoint.add(tmpPoint);
//			System.out.println(tmpPoint);
//			if (setPoint.contains(tmpPoint)){ // contains O(1)
//				System.err.println("co bi trung laij ???");
//			}
//
//		}
//		

		Point tmpPoint;
		Random random = new Random();
		int d = 2 * radius;
		int r2 = radius * radius;
		do {

			int x = random.nextInt(d);

			int x_i = x - radius; // chuyen ve co the toa do am
			int imposible_y = (int) Math.sqrt(r2 - x_i * x_i);
			if (imposible_y == 0)
				continue;
			int y_rand = random.nextInt(2 * imposible_y);
			int y_i = y_rand - imposible_y; // chuyen ve co the y am;

			tmpPoint = new Point(x_i + center.getX(), y_i + center.getY());
			setPoint.add(tmpPoint);
//			System.out.println(tmpPoint);
			if (setPoint.contains(tmpPoint)) { // contains O(1)
//				System.out.println("co bi trung laij ???");
			}

		} while (setPoint.size() < amount);

		return setPoint;
	}

	public static void main(String[] args) {
		Set<Point> setPoint = new HashSet<>();

		setPoint = init(new Point(800, 800), 400, 8000);
		setPoint.addAll(init(new Point(4000, 800), 500, 10000));
		setPoint.addAll(init(new Point(2400, 2400), 600, 12000));
//		System.out.println("end"+setPoint.size());

		List<Point> listPoint = new ArrayList<>(setPoint);

		Collections.shuffle(listPoint);

		File f1;
		FileWriter fWriter;
		try {
			f1 = new File("E:\\long\\20202\\intern\\java-core\\Ex4_GenPoint\\output\\output4.txt");

			fWriter = new FileWriter(f1);

			listPoint.stream().forEach(e -> {
				try {
					fWriter.write(e.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// khong mo stream nen khong phai dong ??
//			if (f1!= null) f1.close();
		}

	}

}
