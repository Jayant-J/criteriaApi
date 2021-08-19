package com.jj.criteriaApiDemo.service;

import com.jj.criteriaApiDemo.entity.Person;
import com.jj.criteriaApiDemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public List<Person> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        query.select(root);
        TypedQuery<Person> typedQueryForAllPerson = em.createQuery(query);
        List<Person> personList = typedQueryForAllPerson.getResultList();
        return personList;
//        OR
//        return personRepository.findAll();
    }

    public List<Person> getAllByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        query.select(root).where(cb.equal(root.get("name"), name));
        TypedQuery<Person> typedQueryForAllPerson = em.createQuery(query);
        List<Person> personList = typedQueryForAllPerson.getResultList();
        return personList;
//        OR
//        return personRepository.findAllByName(name);
    }

    public List<Person> getAllByNameAndAddress(String name, String address) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("name"), name));
        predicates.add(cb.like(root.get("address"), "%" + address + "%"));
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Person> typedQueryForAllPerson = em.createQuery(query);
        List<Person> personList = typedQueryForAllPerson.getResultList();
        return personList;
//        OR
//        return personRepository.findAllByNameAndAddressLike(name, address);
    }

    public List<Person> getAllByNameAndCityWithOrder(String name, String city) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("name"), name));
        Predicate predicateForCity = (cb.equal(root.get("city"), city));
        Predicate predicateForAddress = (cb.like(root.get("address"), "%" + city + "%"));
        Predicate predicateOr = cb.or(predicateForCity, predicateForAddress);
        predicates.add(predicateOr);
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(cb.asc(root.get("address")));
        TypedQuery<Person> typedQueryForAllPerson = em.createQuery(query);
        List<Person> personList = typedQueryForAllPerson.getResultList();
        return personList;
//        OR
//        return personRepository.findAllByNameAndCityOrderByNameAsc(name, city);
    }

    public int getAllCount() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Person> root = query.from(Person.class);
        query.select(cb.count(root));
        TypedQuery<Long> typedQueryForAllPerson = em.createQuery(query);
        Long count = typedQueryForAllPerson.getSingleResult();
        return count.intValue();
//        OR
//        return personRepository.findAll().size();
    }

    public List<Person> getAllUsingPagination() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        query.select(root);
        TypedQuery<Person> typedQueryForAllPerson = em.createQuery(query);
//        Get all results in single page, can change the values as required.
        typedQueryForAllPerson.setFirstResult(1);
        typedQueryForAllPerson.setMaxResults(Integer.MAX_VALUE);
        List<Person> personList = typedQueryForAllPerson.getResultList();
        return personList;
//        OR
//        return (List<Person>) personRepository.findAll(PageRequest.of(1, Integer.MAX_VALUE));
    }
}
