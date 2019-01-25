package dao;

import entities.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DAO extends JpaRepository<Person, Long> {
    List<Person> findByAgeLessThan(int age);
    List<Person> findByNameLike(String str);
    Person findByAge(int age);
    Optional<Person> findByAgeAfter(int age);

    @Query("select p from Person p where p.name=?1")
    List<Person> getByName(String name);

    List<Person> findByAge(int age, Pageable pageable);
}
