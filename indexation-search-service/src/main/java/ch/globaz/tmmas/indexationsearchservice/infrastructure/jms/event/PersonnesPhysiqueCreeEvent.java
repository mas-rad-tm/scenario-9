package ch.globaz.tmmas.indexationsearchservice.infrastructure.jms.event;

import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.ValueObject;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.personne.PersonnePhysique;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class PersonnesPhysiqueCreeEvent implements ValueObject<PersonnesPhysiqueCreeEvent> {

    private String nom;
    private String prenom;

    private String dateNaissance;

    private String nss;
    private String identifiant;
    private String id;

    public PersonnesPhysiqueCreeEvent(String nom, String prenom, String dateNaissance, String nss, String identifiant, String id) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.nss = nss;
        this.identifiant = identifiant;
        this.id = id;
    }

    public PersonnesPhysiqueCreeEvent(){}


    @Override
    public boolean sameValueAs(PersonnesPhysiqueCreeEvent other) {
        return this.equals(other);
    }


    public static PersonnesPhysiqueCreeEvent fromEntity(PersonnePhysique pp) {
        return new PersonnesPhysiqueCreeEvent(pp.nom(),pp.prenom(),pp.dateNaissanceAsString(),
                pp.nss().nss(),pp.identifiant().identifiant(),String.valueOf(pp.id()));
    }
}
