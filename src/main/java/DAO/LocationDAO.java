package DAO;

import entities.Location;

import exceptions.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class LocationDAO {

    private final EntityManager entityManager;


    public LocationDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void save(Location newlocation) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newlocation);
        transaction.commit();
        System.out.println("La location " + newlocation.getNome() + " è stata creata!");
    }
    public Location findById(String locationId) {
        Location found = entityManager.find(Location.class, UUID.fromString(locationId));
        if (found == null) throw new NotFoundException(locationId);
        return found;
    }
}
