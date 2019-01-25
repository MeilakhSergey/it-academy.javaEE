package dao;

import entities.OneToOneChild;
import entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Child extends CrudRepository<OneToOneChild, Long> {

}
