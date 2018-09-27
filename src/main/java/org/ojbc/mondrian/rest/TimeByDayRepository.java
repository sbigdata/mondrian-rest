package org.ojbc.mondrian.rest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "time", path="/time")
public interface TimeByDayRepository extends PagingAndSortingRepository<SigGeo, String>{

    SigGeo findById(@Param("id") Integer id);

    List<SigGeo> findBySigEnglishName(@Param("name") String sigEngName);

    @Query(value = "select * from siggeo sig where sig.sd_code = (select sd_code from sdgeo sd where sd.sd_eng_nam = cast( ?1 as TEXT)) and sig_eng_na= cast( ?2 as TEXT)", nativeQuery = true)
    List<SigGeo> filterDimension(@Param("sdnam") String sd_eng_nam, @Param("signam") String sig_eng_na);
}
