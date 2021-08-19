package com.jj.criteriaApiDemo.repository;

import com.jj.criteriaApiDemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findAllByName(String name);

    List<Person> findAllByNameAndAddressLike(String name, String address);

    @Query(value = "SELECT * FROM PERSON P WHERE P.NAME=?1 AND (P.CITY=?2 OR P.ADDRESS LIKE CONCAT('%',?2,'%')) ORDER BY P.ADDRESS ASC", nativeQuery = true)
    List<Person> findAllByNameAndCityOrderByNameAsc(String name, String city);
}

