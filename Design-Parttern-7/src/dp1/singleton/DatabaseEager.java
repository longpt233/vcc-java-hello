package dp1.singleton;

public class DatabaseEager {

	private static DatabaseEager database = new DatabaseEager();
	
	private DatabaseEager(){}
	
	public static DatabaseEager getInstance(){
		return database;
	}
	
	public void display(){
		System.out.println("I'm the Static/Eager Singleton"
				+ "It's thread safe\n"
				+ " the class is loaded by the JVM\n"
				+ "What if the object won’t be always needed?, \n"
				+ "	What if the object is very heavy and complex");
	}
}
