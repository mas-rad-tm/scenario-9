package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierIndexRepository extends ElasticsearchRepository<DossierDocument, Long> {

    Page<DossierDocument> findByIdentifiant(String no, Pageable pageable);

    List<DossierDocument> findByrequerantId(String title);

}
