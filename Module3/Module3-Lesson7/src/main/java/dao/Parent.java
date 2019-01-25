package dao;

import entities.OneToOneChild;
import entities.OneToOneParent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Parent extends CrudRepository<OneToOneParent, Long> {
    @Query("select c.name from OneToOneParent p join p.child c where p.name=:name")
    String getChild(@Param("name") String name);
    @Query(value = "SELECT c.name FROM OneToOneChild c LEFT JOIN OneToOneParent p ON 1 > 2", nativeQuery = true)
    List<String> getNames();
}
