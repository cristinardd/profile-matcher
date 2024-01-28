package main.model;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "clan")
@Getter
public class Clan {
    @Id
    @Column(name = "clanId",updatable = false, nullable = false)
    private String clanId;
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "clans")
    private Set<Player> players;
}