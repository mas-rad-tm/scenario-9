package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.personne;

import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@EqualsAndHashCode
@Getter
public class PersonnePhysiqueId implements ValueObject<PersonnePhysiqueId> {

    @NotNull(message = "L'identifiant ne peut pas être null")
    private String identifiant;

    public PersonnePhysiqueId(String identifiant) {
        this.identifiant = identifiant;
    }

    public static PersonnePhysiqueId aleatoire () {
        return new PersonnePhysiqueId(UUID.randomUUID().toString());
    }
    public String identifiant() {
        return identifiant;
    }

    @Override
    public boolean sameValueAs(PersonnePhysiqueId other) {
        return this.equals(other);
    }

    PersonnePhysiqueId(){}
}
