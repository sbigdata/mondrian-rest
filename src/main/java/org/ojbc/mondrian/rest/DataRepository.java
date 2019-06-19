package org.ojbc.mondrian.rest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "데이터", path="/data")
public interface DataRepository extends PagingAndSortingRepository<Data, String>{

    @Query(value = "select DISTINCT 연도, 월, 일 from 데이터", nativeQuery = true)
    List<Data> getTime();
}
