package DAO;

import entities.Event;
import exceptions.NotFoundException;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class EventsDAO {
    private final EntityManager entityManager;
    public EventsDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void save(Event newEvent) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newEvent);
        transaction.commit();
        System.out.println("L'evento " + newEvent.getTitolo() + " è stato creato!");
    }
    public Event findById(String eventId) {
        Event found = entityManager.find(Event.class, UUID.fromString(eventId));
        if (found == null) throw new NotFoundException(eventId);
        return found;
    }

//    public void delete(String eventoId) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        Event found = entityManager.find(Event.class, UUID.fromString(eventoId));
//        if (found != null) {
//            entityManager.remove(found);
//            transaction.commit();
//            System.out.println("L'evento con ID " + eventoId + " è stato eliminato!");
//        } else {
//            transaction.commit();
//            throw new NotFoundException(eventoId);
//        }
//    }
}
