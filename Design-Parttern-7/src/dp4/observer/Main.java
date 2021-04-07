package dp4.observer;

public class Main {

	public static void main(String[] args) {
		
		// Create observable object(Newsletter)
		Newsletter newsletter = new Newsletter();
//		ABC abc = new ABC();
		
		// You probably will get the subscribers data from database
		String subscribers[] = {"Mark", "Peter", "Alex"};
		
		
		// Then create subscriber object(s), and pass newsletter object.
		// Each subscriber object will add itself to array of observers,
		// for the passed newsletter object
		Subscriber subscriber = null;
		
		for (String name : subscribers) {
			subscriber = new Subscriber(name, newsletter);
		}
		
		Std aStd= new Std();
		newsletter.addObserver(aStd);
		// Whenever you create a new newsletter, 
		// Subscribers will get notified
		newsletter.create("This newsletter shall be sent to subscribers");
		
		// You can also delete a subscriber from array of observers
		newsletter.deleteObserver(subscriber);
		
//		
//		Newsletter newsletter2= new Newsletter();
//		
//		newsletter2.addObserver(aStd);
//		
//		newsletter2.create("lettwe 2 create notify");
//		
	}

}
