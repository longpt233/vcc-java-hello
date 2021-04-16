package patterns.es.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import patterns.es.events.Event;

public class EventStore {

    private Map<String, List<Event>> store = new HashMap<>();

    /**
     * add to map event
     * 
     * @param id id user make event 
     * 
     * */
    public void addEvent(String id, Event event) {
        List<Event> events = store.get(id);
        if (events == null) {
            events = new ArrayList<Event>();
            events.add(event);
            store.put(id, events);
        } else {
            events.add(event);
        }
    }

    public List<Event> getEvents(String id) {
        return store.get(id);
    }
    
    public Map<String, List<Event>> getAll() {
		return store;
	}

}
