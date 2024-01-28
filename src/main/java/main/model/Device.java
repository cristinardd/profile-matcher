package main.model;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "carrier", nullable = false)
    private String carrier;

    @Column(name = "firmware", nullable = false)
    private String firmware;

    @ManyToMany(mappedBy = "devices")
    private Set<Player> players;
}
