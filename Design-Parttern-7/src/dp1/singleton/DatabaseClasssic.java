package dp1.singleton;

public class DatabaseClasssic {

	private static DatabaseClasssic database = null;
	
	private DatabaseClasssic(){}
	
	public static DatabaseClasssic getInstance(){
		if(database == null){
			database = new DatabaseClasssic();
		}
		return database;
	}
	
	public void display(){
		System.out.println("I'm the Classic Singleton");
		System.out.println("Be Caution: I'm NOT thread safe");
	}
}
