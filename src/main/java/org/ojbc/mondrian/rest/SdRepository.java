package org.ojbc.mondrian.rest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
/**
 * sd Table Connection
 */
@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "sd", path="/sd")
public interface SdRepository extends PagingAndSortingRepository<Sd, Long> {

//    List<Sd> findAllBy();

//    List<Sd> findAllBy

//    @Query(value = "select sdcode from sdgeo where NAME = ?1", nativeQuery = true)
//    List<SdGeo> findByCode(@Param("name") String sdKorName);
//
//    @Query(value = "select * from sdgeo where name = ?1", nativeQuery = true)
//    List<SdGeo> findByGeometry(@Param("sdnam") String sdKorName);
}
