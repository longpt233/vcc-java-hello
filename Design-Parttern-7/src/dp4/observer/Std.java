package dp4.observer;

import java.util.Observable;
import java.util.Observer;

public class Std implements Observer{

	public  Std() {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("std nhan dc thon thong bao");
	}
	
	
}
