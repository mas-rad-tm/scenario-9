package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.personne;

import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode
@Getter
public class NSS implements ValueObject<NSS> {

    NSS () {}

    @NotNull(message = "le nss ne peut pas être null")
    private String nss;

    public NSS(@NotNull() String nss){

        this.nss = nss;
    }

    public String nss(){
        return nss;
    }

    @Override
    public boolean sameValueAs(NSS other) {
        return this.equals(other);
    }
}
