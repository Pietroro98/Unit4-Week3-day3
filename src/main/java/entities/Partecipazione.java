package entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Partecipazione {

    @Id
    @GeneratedValue()
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Event evento;

    @Enumerated(EnumType.STRING)
    private Stato stato;

    public enum Stato {
        CONFERMATA, DA_CONFERMARE
    }

    // Costruttori, getter e setter
    public Partecipazione() {}

    public Partecipazione(Persona persona, Event evento, Stato stato) {
        this.persona = persona;
        this.evento = evento;
        this.stato = stato;
    }

    public UUID getId() {
        return id;
    }



    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Event getEvent() {
        return evento;
    }

    public void setEvent(Event evento) {
        this.evento = evento;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }
}
