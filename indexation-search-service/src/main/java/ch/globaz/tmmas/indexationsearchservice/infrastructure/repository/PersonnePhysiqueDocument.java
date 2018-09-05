package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository;


import ch.globaz.tmmas.indexationsearchservice.infrastructure.jms.event.PersonnesPhysiqueCreeEvent;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Document(indexName = "personne", type = "avs")
public class PersonnePhysiqueDocument {


	private String identifiant;
	private String nom;
	private String nss;
	private String prenom;
	@Id
	private String id;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateNaissance;


	public PersonnePhysiqueDocument(){}


	public PersonnePhysiqueDocument(String id, String identifiant, String nom, String prenom, String nss, String dateNaissance){

		this.id = id;
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.nss = nss;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		this.dateNaissance = LocalDate.parse(dateNaissance,formatter);
	}



	public static PersonnePhysiqueDocument fromEvent(PersonnesPhysiqueCreeEvent personnesPhysiqueCreeEvent){

		return new PersonnePhysiqueDocument(personnesPhysiqueCreeEvent.getId(),
				personnesPhysiqueCreeEvent.getIdentifiant(),
				personnesPhysiqueCreeEvent.getNom(),
				personnesPhysiqueCreeEvent.getPrenom(),
				personnesPhysiqueCreeEvent.getNss(),
				personnesPhysiqueCreeEvent.getDateNaissance());
	}
}
