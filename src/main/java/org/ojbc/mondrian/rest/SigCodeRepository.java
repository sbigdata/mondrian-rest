package org.ojbc.mondrian.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
/**
 * sigcode Table Connection
 */
@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "sig", path="/sig")
public interface SigCodeRepository extends PagingAndSortingRepository <SigCode, Long> {
    List<SigCode> findBySdCode(@Param("sdCode") String sdCode);
}
