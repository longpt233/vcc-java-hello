package patterns.cqrs.projectors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import patterns.cqrs.repository.UserReadRepository;
import patterns.domain.Address;
import patterns.domain.Contact;
import patterns.domain.User;
import patterns.domain.UserAddress;
import patterns.domain.UserContact;

public class UserProjector {

    UserReadRepository readRepository = new UserReadRepository();

    public UserProjector(UserReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    
    /**
     * goi ham nay khi cap nhat 1 user nao do 
     * 
     * */
    public void project(User user) {
    	
    	/**
    	 * cap nhat lai cac contact
    	 * 
    	 * */ 
        UserContact userContact = Optional.ofNullable(readRepository.getUserContact(user.getUserid()))
            .orElse(new UserContact());
        Map<String, Set<Contact>> contactByType = new HashMap<>();
        for (Contact contact : user.getContacts()) {
            Set<Contact> contacts = Optional.ofNullable(contactByType.get(contact.getType()))
                .orElse(new HashSet<>());
            contacts.add(contact);
            contactByType.put(contact.getType(), contacts);
        }
        userContact.setContactByType(contactByType);
        readRepository.addUserContact(user.getUserid(), userContact);

        /* tuong tu cap nhat lai cho address
         * 
         * */
        UserAddress userAddress = Optional.ofNullable(readRepository.getUserAddress(user.getUserid()))
            .orElse(new UserAddress());
        Map<String, Set<Address>> addressByRegion = new HashMap<>();
        for (Address address : user.getAddresses()) {
            Set<Address> addresses = Optional.ofNullable(addressByRegion.get(address.getState()))
                .orElse(new HashSet<>());
            addresses.add(address);
            addressByRegion.put(address.getState(), addresses);
        }
        userAddress.setAddressByRegion(addressByRegion);
        readRepository.addUserAddress(user.getUserid(), userAddress);
    }
}
