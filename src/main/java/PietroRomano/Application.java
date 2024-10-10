package PietroRomano;

import DAO.EventsDAO;
import DAO.LocationDAO;
import DAO.PartecipazioneDAO;
import DAO.PersonaDAO;
import entities.Event;
import entities.Location;
import entities.Partecipazione;
import entities.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4-W3-D3");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        //creazione DAO
        EventsDAO ed = new EventsDAO(em);
        PersonaDAO pd = new PersonaDAO(em);
        LocationDAO ld = new LocationDAO(em);
        PartecipazioneDAO partD = new PartecipazioneDAO(em);

        // creo Persona
        Persona persona1 = new Persona("Mario", "Rossi", "mario.rossi@example.com", LocalDate.of(1998, 1, 2), Persona.Sesso.M);
        Persona persona2 = new Persona("Aldo", "Baglio", "aldo.baglio@example.com", LocalDate.of(1998, 2, 3), Persona.Sesso.M);
        Persona persona3 = new Persona("Giuseppina", "Verdi", "giuseppina.verdi@example.com", LocalDate.of(1999, 3, 4), Persona.Sesso.F);
        Persona persona4 = new Persona("Francesca", "Neri", "Francesca.neri@example.com", LocalDate.of(1999, 4, 5), Persona.Sesso.F);
        pd.save(persona1);
        pd.save(persona2);
        pd.save(persona3);
        pd.save(persona4);

        // creo Location
        Location location1 = new Location("Piazza Duomo", "Milano");
        Location location2 = new Location("Centro Congressi", "Palermo");
        Location location3 = new Location("Palazzo Ducale", "Parma");
        Location location4 = new Location("Neue Galerie", "New York");
        ld.save(location1);
        ld.save(location2);
        ld.save(location3);
        ld.save(location4);

        //creo evento
        Event event1 = new Event ("Concerto musicale", LocalDate.of(2024, 6, 6), "Concerto gratuito in piazza", Event.TipoEvento.PUBBLICO, 1000);
        Event event2 = new Event("Fiera del mediterraneo", LocalDate.of(2024, 7, 10), "Fiera del mediterraneo tipica di Palermo", Event.TipoEvento.PUBBLICO,5000);
        Event event3 = new Event("Workshop di cucina", LocalDate.of(2024, 8, 15), "Workshop di cucina italiana", Event.TipoEvento.PRIVATO, 20);
        Event event4 = new Event("Mostra d'arte", LocalDate.of(2024, 9, 5), "Mostra d'arte contemporanea", Event.TipoEvento.PRIVATO, 300);
        ed.save(event1);
        ed.save(event2);
        ed.save(event3);
        ed.save(event4);


        // Associa location agli eventi
        event1.setLocation(location1);
        event2.setLocation(location2);
        event3.setLocation(location3);
        event4.setLocation(location4);
        ed.save(event1);
        ed.save(event2);
        ed.save(event3);
        ed.save(event4);



        // creo partecipazione
        Partecipazione partecipazione1 = new Partecipazione(persona1, event1, Partecipazione.Stato.CONFERMATA);
        Partecipazione partecipazione2 = new Partecipazione(persona2, event2, Partecipazione.Stato.CONFERMATA);
        Partecipazione partecipazione3 = new Partecipazione(persona3, event3, Partecipazione.Stato.DA_CONFERMARE);
        Partecipazione partecipazione4 = new Partecipazione(persona4, event4, Partecipazione.Stato.DA_CONFERMARE);
        partD.save(partecipazione1);
        partD.save(partecipazione2);
        partD.save(partecipazione3);
        partD.save(partecipazione4);

        // Recupero le persone
        List<Persona> persone = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        System.out.println("Persone registrate:");
        persone.forEach(System.out::println);

        // Recupero gli eventi
        List<Event> eventi = em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
        System.out.println("\nEventi registrati:");
        eventi.forEach(System.out::println);

        // Recupero le location
        List<Location> locations = em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
        System.out.println("\nLocation registrate:");
        locations.forEach(System.out::println);

        // Recupero le partecipazioni
        List<Partecipazione> partecipazioni = em.createQuery("SELECT p FROM Partecipazione p", Partecipazione.class).getResultList();
        System.out.println("\nPartecipazioni registrate:");
        partecipazioni.forEach(System.out::println);

        event1.getPartecipazioni().add(partecipazione1);
        persona1.getListaPartecipazioni().add(partecipazione1);



        em.close();
        emf.close();


    }
}
