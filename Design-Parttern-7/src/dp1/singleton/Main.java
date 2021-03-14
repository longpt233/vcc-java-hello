package dp1.singleton;

public class Main {

	public static void main(String[] args) {
		
		DatabaseSync database = DatabaseSync.getInstance();
		database.display();

	}

}
