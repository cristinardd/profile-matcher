package main.model;
import jakarta.persistence.*;
@Entity
@Table(name = "clan")
public class Clan {
    @Id
    @Column(name = "clanId",updatable = false, nullable = false)
    private String clanId;
    @Column(name = "name", nullable = false)
    private String name;
}