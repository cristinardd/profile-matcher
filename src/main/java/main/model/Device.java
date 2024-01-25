package main.model;
import jakarta.persistence.*;
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "carrier", nullable = false)
    private String carrier;

    @Column(name = "firmware", nullable = false)
    private String firmware;
}
