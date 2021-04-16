package patterns.cqrs.projections;

import java.util.Set;

import patterns.cqrs.queries.AddressByRegionQuery;
import patterns.cqrs.queries.ContactByTypeQuery;
import patterns.cqrs.repository.UserReadRepository;
import patterns.domain.Address;
import patterns.domain.Contact;
import patterns.domain.UserAddress;
import patterns.domain.UserContact;

public class UserProjection {

    private UserReadRepository repository;

    public UserProjection(UserReadRepository repository) {
        this.repository = repository;
    }

    public Set<Contact> handle(ContactByTypeQuery query) throws Exception {
        UserContact userContact = repository.getUserContact(query.getUserId());
        if (userContact == null)
            throw new Exception("User does not exist.");
        return userContact.getContactByType()
            .get(query.getContactType());
    }

    public Set<Address> handle(AddressByRegionQuery query) throws Exception {
        UserAddress userAddress = repository.getUserAddress(query.getUserId());
        if (userAddress == null)
            throw new Exception("User does not exist.");
        return userAddress.getAddressByRegion()
            .get(query.getState());
    }

}
