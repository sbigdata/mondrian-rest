package org.ojbc.mondrian.rest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
/**
 * Emdgeo Table Connection
 */
@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "emdgeo", path="/emdgeo")
public interface EmdGeoRepository extends PagingAndSortingRepository<EmdGeo, String>{

    List<EmdGeo> findBySdCodeAndSigCodeAndName(@Param("sdcode") String sdCode, @Param("sigcode") String sigCode, @Param("emdname") String emdName);

//    @Query(value = "select * from emdgeo emd where emd.sig_code = (select sig_code from siggeo sig where sig.sig_kor_na = cast( ?1 as TEXT)) and emd_kor_na= cast( ?2 as TEXT)", nativeQuery = true)
//    List<EmdGeo> findEm(@Param("signam") String sig_kor_na, @Param("emdnam") String emd_kor_na);

}
