package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "meeting_participants", joinColumns = {@JoinColumn(name = "meeting_ID")},
                                        inverseJoinColumns = {@JoinColumn(name = "person_ID")})
    private List<Person> people = new LinkedList<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "meeting_noCache", joinColumns = {@JoinColumn(name = "meeting_ID")},
            inverseJoinColumns = {@JoinColumn(name = "noCache_ID")})
    private List<NoCache> noCache = new LinkedList<>();
}
