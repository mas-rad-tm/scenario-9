package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnePhysiqueIndexRepository extends ElasticsearchRepository<PersonnePhysiqueDocument, String> {

    Page<PersonnePhysiqueDocument> findByIdentifiant(String no, Pageable pageable);

    List<PersonnePhysiqueDocument> findByIdentifiant(String title);

}
