package org.ojbc.mondrian.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
/**
 * sdgeo Table Connection
 */
@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "sdgeo", path="/sdgeo")
public interface SdGeoRepository extends PagingAndSortingRepository<SdGeo, String> {

    List<SdGeo> findByName(@Param("name") String name);

    @Query(value = "select sdcode from sdgeo where NAME = ?1", nativeQuery = true)
    List<SdGeo> findByCode(@Param("name") String sdKorName);

    @Query(value = "select * from sdgeo where name = ?1", nativeQuery = true)
    List<SdGeo> findByGeometry(@Param("sdnam") String sdKorName);
}
