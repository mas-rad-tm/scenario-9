package ch.globaz.tmmas.indexationsearchservice.infrastructure.dto;


public class DossierEtendueDto {

	private DossierDto rente;
	private PersonnesPhysiqueDto personne;

	public DossierEtendueDto() {}

	public DossierEtendueDto(DossierDto rente, PersonnesPhysiqueDto personne){
		this.rente = rente;
		this.personne = personne;
	}

	public DossierDto getRente () {
		return rente;
	}

	public PersonnesPhysiqueDto getPersonne () {
		return personne;
	}


}
