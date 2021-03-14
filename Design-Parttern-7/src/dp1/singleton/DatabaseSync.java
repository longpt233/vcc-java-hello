package dp1.singleton;

public class DatabaseSync {

	private static DatabaseSync database = null;
	
	private DatabaseSync(){}
	
	public static synchronized DatabaseSync getInstance(){
		if(database == null){
			database = new DatabaseSync();
		}
		return database;
	}
	
	public void display(){
		System.out.println("I'm the Synchronized Singleton . I take more time");
	}
}
