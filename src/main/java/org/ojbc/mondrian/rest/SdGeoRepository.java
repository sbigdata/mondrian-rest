package org.ojbc.mondrian.rest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "sdgeo", path="/sdgeo")
public interface SdGeoRepository extends PagingAndSortingRepository<SdGeo, String>{

    SdGeo findById(@Param("id") Integer id);

    List<SdGeo> findBySdKorName(@Param("name") String sdKorName);

    @Query(value = "select * from sdgeo where sd_kor_nam = ?1", nativeQuery = true)
    List<SdGeo> findByGeometry(@Param("sdnam") String sdKorName);

}
