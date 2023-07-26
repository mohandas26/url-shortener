package com.urlshorten.repository;

import com.urlshorten.entity.URL;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface URLRepository extends JpaRepository<URL, Integer> {
    Optional<URL> findByFullUrl(String url);
    
    
  //  List<Integer> findIdByFullUrl(String url);
}
