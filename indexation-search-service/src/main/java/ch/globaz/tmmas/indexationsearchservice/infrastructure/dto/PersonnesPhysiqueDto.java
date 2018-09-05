package ch.globaz.tmmas.indexationsearchservice.infrastructure.dto;



import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class PersonnesPhysiqueDto {

	private String nom;
	private String prenom;
	private String identifiant;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateNaissance;

	private String nss;

	private Long id;


	public PersonnesPhysiqueDto(){}


	private PersonnesPhysiqueDto(Long id,String identifiant,String nom, String prenom, String dateNaissance, String nss){

		this.nom = nom;
		this.prenom = prenom;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		this.dateNaissance = LocalDate.parse(dateNaissance,formatter);
		this.nss = nss;
		this.id = id;
		this.identifiant = identifiant;
	}





}
