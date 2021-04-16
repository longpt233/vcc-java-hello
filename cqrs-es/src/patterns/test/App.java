package patterns.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Setter;
import patterns.cqrs.commands.CreateUserCommand;
import patterns.cqrs.commands.UpdateUserCommand;
import patterns.cqrs.projections.UserProjection;
import patterns.cqrs.queries.AddressByRegionQuery;
import patterns.cqrs.repository.UserReadRepository;
import patterns.domain.Address;
import patterns.domain.Contact;
import patterns.es.events.Event;
import patterns.es.repository.EventStore;
import patterns.escqrs.aggregates.UserAggregate;
import patterns.escqrs.projectors.UserProjector;

public class App {
	
	public static void main(String[] args) {
		
		EventStore eventStore= new  EventStore();
		UserAggregate userAggregate= new UserAggregate(eventStore);
		// Create
		CreateUserCommand[] commandCreate=new CreateUserCommand[10];
		for(int i=0;i<10;i++) {
			 commandCreate[i]=new CreateUserCommand(String.valueOf(i), "Phan",	 "Long");
			 userAggregate.handleCreateUserCommand(commandCreate[i]);
		}
		System.out.println("su kien sau khi create\n");
		eventStore.getAll().forEach((key, value) -> System.out.println(key + " " + value));
		
		// update
		Set<Address> setAddresses= new HashSet<>();
		Set<Contact> setContacts= new HashSet<>();
		setAddresses.add(new Address("id-city-1", "Hanoi", "abc123"));
		setContacts.add(new Contact("phone", "12345678"));
		UpdateUserCommand updateUserCommand=new UpdateUserCommand("4", setAddresses, setContacts);
		// List<Event> listEventUpdate= userAggregate.handleUpdateUserCommand(updateUserCommand);
		// 
		userAggregate.handleUpdateUserCommand(updateUserCommand);

		System.out.println("su kien sau khi update\n");
		eventStore.getAll().forEach((key, value) -> System.out.println(key + " " + value));

		// khoi tao repo read 
		UserReadRepository readRepository =new UserReadRepository();
		UserProjection userProjection = new UserProjection(readRepository);
		AddressByRegionQuery addressByRegionQuery= new AddressByRegionQuery("4", "Hanoi");

		System.out.println("query truoc khi update  \n");
		printQueryResponse(userProjection, addressByRegionQuery);
		
		// projector
		UserProjector userProjector =new UserProjector(readRepository);
		// moi lan co mot user thay doi hco neu muon thi goi 
		userProjector.project("4", eventStore.getEvents("4"));

		System.out.println("query sau khi upade  \n");
		printQueryResponse(userProjection, addressByRegionQuery);
		
		
		// update xong call project 
		UpdateUserCommand updateUserCommand2=new UpdateUserCommand("2", setAddresses, setContacts);
		List<Event> returnEvents2 = userAggregate.handleUpdateUserCommand(updateUserCommand2);
		userProjector.project("2", returnEvents2);
		
		
		setAddresses.add(new Address("id-city-2", "Hai Duong", "123@abc"));
		setAddresses.add(new Address("id-city-3", "Hanoi", "12345@abc"));
		UpdateUserCommand updateUserCommand4=new UpdateUserCommand("4", setAddresses, setContacts);
		List<Event> returnEvents4 = userAggregate.handleUpdateUserCommand(updateUserCommand4);
		userProjector.project("4", returnEvents4);

		AddressByRegionQuery addressByRegionQuery2= new AddressByRegionQuery("4", "Hanoi");

		System.out.println("query khi upade bang return \n");
		printQueryResponse(userProjection,addressByRegionQuery2);
		
		
		eventStore.getAll().forEach((key, value) -> System.out.println(key + " " + value));
		
		
		
		
	}
	
	
	public static void printQueryResponse(UserProjection userProjection, AddressByRegionQuery addressByRegionQuery) {
		Set<Address> queryResponse=null;
		
		try {
			queryResponse = userProjection.handle(addressByRegionQuery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(queryResponse==null) {
			System.out.println("null");
		}else {
			queryResponse.forEach(o -> System.out.println(o));
		}
	}

}
