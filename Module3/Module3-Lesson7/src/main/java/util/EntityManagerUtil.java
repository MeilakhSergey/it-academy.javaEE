package util;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class EntityManagerUtil {
    @PersistenceContext(unitName = "jpa-unit")
    @Getter
    private EntityManager em;

    public void clear() {
        em.clear();
    }

    public void flush() {
        em.flush();
    }
}
