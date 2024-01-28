package main.model;
import jakarta.persistence.*;
import lombok.*;
import main.model.util.Gender;
import main.model.util.GenderConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "player")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Player {

    @Id
    @Column(name = "player_id", columnDefinition = "CHAR(36)")
    private String playerId;

    @Column(name = "credential", nullable = false)
    private String credential;

    @Column(name = "created", nullable = false)
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    private LocalDateTime modified;

    @Column(name = "last_session", nullable = false)
    private LocalDateTime lastSession;

    @Column(name = "total_spent", nullable = false)
    private Integer totalSpent;

    @Column(name = "total_refund", nullable = false)
    private Integer totalRefund;

    @Column(name = "total_transactions", nullable = false)
    private Integer totalTransactions;

    @Column(name = "last_purchase", nullable = false)
    private LocalDateTime lastPurchase;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "xp", nullable = false)
    private Integer xp;

    @Column(name = "total_playtime", nullable = false)
    private Integer totalPlaytime;

    @OneToOne
    @JoinColumn(name = "country", referencedColumnName = "country_code")
    private Country country;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "birthdate", nullable = false)
    private LocalDateTime birthdate;

    @Convert(converter = GenderConverter.class)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "_customfield")
    private String customfield;

    @ManyToMany
    @JoinTable(
            name = "player_device",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id")
    )
    private Set<Device> devices;

    @ManyToMany
    @JoinTable(
            name = "player_clan",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "clan_id")
    )
    private Set<Clan> clans;

    @OneToMany(mappedBy = "player")
    private Set<Inventory> inventory;
    @Column(name = "active_campaign")
    private String activeCampaign;
}