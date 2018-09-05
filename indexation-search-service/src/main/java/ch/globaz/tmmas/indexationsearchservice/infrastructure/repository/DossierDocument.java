package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository;


import ch.globaz.tmmas.indexationsearchservice.infrastructure.dto.DossierDto;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.jms.event.DossierCreeEvent;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.dossier.Dossier;
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
@Document(indexName = "rente", type = "avs")
public class DossierDocument {



	private Long requerantId;
	private String identifiant;
	@Id
	private Long id;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;


	public DossierDocument(){}


	public DossierDocument(Long id, String identifiant, Long requerantId, String dateEnregistrement){

		this.id = id;
		this.requerantId = requerantId;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		this.dateEnregistrement = LocalDate.parse(dateEnregistrement,formatter);
		this.identifiant = identifiant;
	}



	public static DossierDocument fromEvent(DossierCreeEvent dossierEvent){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return new DossierDocument(dossierEvent.getId(),
				dossierEvent.getIdentifiant(),
				dossierEvent.getRequerantId(),
				dossierEvent.getDateEnregistrement());
	}
}
