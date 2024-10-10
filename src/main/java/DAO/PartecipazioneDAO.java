package DAO;

import entities.Partecipazione;

import exceptions.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class PartecipazioneDAO {
    private final EntityManager entityManager;


    public PartecipazioneDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void save(Partecipazione newPartecipazione) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newPartecipazione);
        transaction.commit();
        System.out.println("La partecipazione è stata creata!");
    }
    public Partecipazione findById(String partecipazioneId) {
        Partecipazione found = entityManager.find(Partecipazione.class, UUID.fromString(partecipazioneId));
        if (found == null) throw new NotFoundException(partecipazioneId);
        return found;
    }
}

