package DAO;


import entities.Persona;
import exceptions.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.UUID;

public class PersonaDAO {

    private final EntityManager entityManager;


    public PersonaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void save(Persona newPersona) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            if (findByEmail(newPersona.getEmail()) == null) {
                entityManager.persist(newPersona);
                transaction.commit();
                System.out.println("La persona " + newPersona.getNome() + " è stata creata!");
            } else {
                transaction.rollback();
                System.out.println("Persona con email " + newPersona.getEmail() + " esiste già e non sarà creata di nuovo.");
            }
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    public Persona findByEmail(String email) {
        TypedQuery<Persona> query = entityManager.createQuery(
                "SELECT p FROM Persona p WHERE p.email = :email", Persona.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public Persona findById(String personaId) {
        Persona found = entityManager.find(Persona.class, UUID.fromString(personaId));
        if (found == null) throw new NotFoundException(personaId);
        return found;
    }
}
